package com.obinna.libraryuser.repository;

import com.obinna.libraryuser.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(@Param("username") String username);

    List<User> findAllByUsernameContainsAndId(String username, Long id, Pageable pageable);

    List<User> findAllByUsernameContains(String username, Pageable pageable);
}
