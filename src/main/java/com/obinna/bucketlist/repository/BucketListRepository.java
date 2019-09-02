package com.obinna.bucketlist.repository;

import com.obinna.bucketlist.model.BucketList;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BucketListRepository extends JpaRepository<BucketList, Integer> {

    List<BucketList> findAllByNameContaining(@Param("name") String name, Pageable pageable);
}
