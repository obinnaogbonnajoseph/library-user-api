package com.obinna.bucketlist.serviceImpl;

import com.obinna.bucketlist.dto.BucketListItemDto;
import com.obinna.bucketlist.model.BucketListItem;
import com.obinna.bucketlist.repository.BucketListItemRepository;
import com.obinna.bucketlist.service.BucketListItemService;
import com.obinna.bucketlist.utils.NotCreatedException;
import com.obinna.bucketlist.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;

@Service
@Transactional
public class BucketListItemServiceImpl implements BucketListItemService {

    @Autowired
    private BucketListItemRepository itemRepository;

    @Override
    public BucketListItem createItem(BucketListItemDto dto) throws NotCreatedException {
        BucketListItem item = new BucketListItem();
        item.setName(dto.getName());
        item.setDateCreated(dto.getDateCreated());
        item.setDone(false);
        item.setDateModified(new Date());
        itemRepository.save(item);
        if(item.getId() >= 0) {
            return item;
        }
        throw new NotCreatedException("Bucket List Item could not be created");
    }

    @Override
    public BucketListItem updateItem(BucketListItemDto dto) throws ResourceNotFoundException {
        BucketListItem item = itemRepository.findById(dto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Bucket List Item does not exist"));
        item.setDone(dto.isDone());
        item.setName(dto.getName());
        item.setDateModified(new Timestamp(new Date().getTime()));
        itemRepository.save(item);
        return item;
    }

    @Override
    public void deleteItem(int id) throws ResourceNotFoundException {
        BucketListItem item = itemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bucket List Item does not exist"));
        itemRepository.delete(item);
    }
}
