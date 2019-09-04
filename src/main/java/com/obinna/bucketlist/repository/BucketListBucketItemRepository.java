package com.obinna.bucketlist.repository;

import com.obinna.bucketlist.model.BucketListBucketItemMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BucketListBucketItemRepository extends JpaRepository<BucketListBucketItemMapper, Long> {
}
