package com.obinna.bucketlist.serviceImpl;

import com.obinna.bucketlist.dto.BucketListDto;
import com.obinna.bucketlist.dto.BucketListItemDto;
import com.obinna.bucketlist.model.BucketList;
import com.obinna.bucketlist.model.BucketListItem;
import com.obinna.bucketlist.model.User;
import com.obinna.bucketlist.repository.BucketListItemRepository;
import com.obinna.bucketlist.repository.BucketListRepository;
import com.obinna.bucketlist.repository.UserRepository;
import com.obinna.bucketlist.service.BucketListItemService;
import com.obinna.bucketlist.service.BucketListService;
import com.obinna.bucketlist.utils.NotCreatedException;
import com.obinna.bucketlist.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class BucketListServiceImpl implements BucketListService {

    @Autowired
    private BucketListRepository bucketListRepository;

    @Autowired
    private BucketListItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BucketListItemService itemService;

    @Override
    public BucketList createBucketList(BucketListDto dto) throws NotCreatedException, ResourceNotFoundException {
        BucketList bucketList = new BucketList();
        User user = userRepository.findById(dto.getCreatedBy())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Bucketlist cannot be created. The user does not exist"));
        bucketList.setCreatedBy(user);
        bucketList.setDateCreated(dto.getDateCreated());
        bucketList.setDateModified(new Date());
        bucketList.setName(dto.getName());
        List<BucketListItem> itemList = new ArrayList<>();
        dto.getItems().forEach(item -> {
            // create bucket list item
            // if successful, save
            BucketListItem bucketItem = null;
            try {
                bucketItem = itemService.createItem(item);
            } catch (NotCreatedException e) {
                e.printStackTrace();
            }
            if(bucketItem.getId() >= 0) {
                itemList.add(bucketItem);
            }
        });
        bucketList.setItems(itemList);
        bucketListRepository.save(bucketList);
        if (bucketList.getId() >= 0) {
            return bucketList;
        } else throw new NotCreatedException("Could not create bucket list");
    }

    @Override
    public BucketList updateBucketList(BucketListDto dto) throws ResourceNotFoundException {
        BucketList bucketList = bucketListRepository.findById(dto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Bucketlist not found"));
        if(dto.getName() != null) {
            bucketList.setName(dto.getName());
        }
        if(dto.getItems() != null && dto.getItems().size() > 0) {
//             first filter item dtos by their ids, and then find the corresponding items with their ids
            List<Integer> itemList = dto.getItems().stream()
                    .filter(it -> it.getId() >= 0) // filter only items with id
                    .map(BucketListItemDto::getId) // get their ids
                    .filter(id -> itemRepository.findById(id).isPresent()) // check if the ids are valid in db
                    .collect(Collectors.toList());

            List<BucketListItem> items = new ArrayList<>();
            itemList.forEach(item -> items.add(itemRepository.findById(item).orElse(null))); // add bucket items to a list
            items.removeAll(Collections.singleton(null)); // remove all null values from the collection
            bucketList.setItems(items.stream().filter(Objects::nonNull).collect(Collectors.toList()));
        }
        dto.setDateModified(new Timestamp(new Date().getTime()));
        bucketListRepository.save(bucketList);
        return bucketList;
    }

    @Override
    public void deleteBucketList(int id) throws ResourceNotFoundException {
        BucketList bucketList = bucketListRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bucketlist not found"));
        bucketListRepository.delete(bucketList);
    }
}
