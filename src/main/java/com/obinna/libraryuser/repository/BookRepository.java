package com.obinna.libraryuser.repository;

import com.obinna.libraryuser.model.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT book from Book book WHERE book.name in ?1 or book.author in ?2")
    List<Book> findBooksByNameAndAuthor(String searchName, String searchAuthor, Pageable pageable);
}
