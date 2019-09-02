package com.obinna.bucketlist.service;

import com.obinna.bucketlist.dto.BucketListDto;
import com.obinna.bucketlist.model.BucketList;
import com.obinna.bucketlist.utils.NotCreatedException;
import com.obinna.bucketlist.utils.ResourceNotFoundException;

public interface BucketListService {

    BucketList createBucketList(BucketListDto dto) throws NotCreatedException, ResourceNotFoundException;

    BucketList updateBucketList(BucketListDto dto) throws ResourceNotFoundException;

    void deleteBucketList(int id) throws ResourceNotFoundException;


}
