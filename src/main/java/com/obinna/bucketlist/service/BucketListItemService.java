package com.obinna.bucketlist.service;

import com.obinna.bucketlist.dto.BucketListItemDto;
import com.obinna.bucketlist.model.BucketListItem;
import com.obinna.bucketlist.utils.NotCreatedException;
import com.obinna.bucketlist.utils.ResourceNotFoundException;

public interface BucketListItemService {

    BucketListItem createItem(BucketListItemDto dto) throws NotCreatedException;

    BucketListItem updateItem(BucketListItemDto dto) throws ResourceNotFoundException;

    void deleteItem(int id) throws ResourceNotFoundException;
}
