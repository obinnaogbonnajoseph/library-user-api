package com.obinna.bucketlist.repository;

import com.obinna.bucketlist.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
