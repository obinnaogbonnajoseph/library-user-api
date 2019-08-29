package com.obinna.bucketlist.repository;

import com.obinna.bucketlist.entity.BucketListBucketItemMapper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BucketListBucketItemRepository extends JpaRepository<BucketListBucketItemMapper, Integer> {
}
