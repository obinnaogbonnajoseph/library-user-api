package com.obinna.libraryuser.service;

import com.obinna.libraryuser.dto.BookDto;
import com.obinna.libraryuser.model.Book;
import com.obinna.libraryuser.utils.NotCreatedException;
import com.obinna.libraryuser.utils.ResourceNotFoundException;

public interface BookService {

    Book createBook(BookDto dto) throws NotCreatedException, ResourceNotFoundException;

    Book updateBook(BookDto dto) throws ResourceNotFoundException;

    void deleteBook(Long id) throws ResourceNotFoundException;


}
