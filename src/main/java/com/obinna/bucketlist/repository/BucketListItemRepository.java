package com.obinna.bucketlist.repository;

import com.obinna.bucketlist.entity.BucketListItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BucketListItemRepository extends JpaRepository<BucketListItem, Integer> {
}
