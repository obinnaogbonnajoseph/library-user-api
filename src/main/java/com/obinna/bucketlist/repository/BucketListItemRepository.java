package com.obinna.bucketlist.repository;

import com.obinna.bucketlist.model.BucketListItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BucketListItemRepository extends JpaRepository<BucketListItem, Integer> {
}
