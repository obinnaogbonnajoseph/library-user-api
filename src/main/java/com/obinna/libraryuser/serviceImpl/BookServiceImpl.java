package com.obinna.libraryuser.serviceImpl;

import com.obinna.libraryuser.dto.BookDto;
import com.obinna.libraryuser.model.Book;
import com.obinna.libraryuser.repository.BookRepository;
import com.obinna.libraryuser.service.BookService;
import com.obinna.libraryuser.utils.NotCreatedException;
import com.obinna.libraryuser.utils.ResourceNotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book createBook(BookDto dto) throws NotCreatedException, ResourceNotFoundException {
        Book book = new Book();
        book.setDateCreated(dto.getDateCreated());
        book.setName(dto.getName());
        bookRepository.save(book);
        if (book.getId() >= 0) {
            return book;
        } else throw new NotCreatedException("Could not create book");
    }

    @Override
    public Book updateBook(BookDto dto) throws ResourceNotFoundException {
        Book book = bookRepository.findById(dto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        if(StringUtils.isNotBlank(dto.getName())) {
            book.setName(dto.getName());
        }
        if(StringUtils.isNotBlank(dto.getAuthor())) {
            book.setAuthor(dto.getAuthor());
        }
        if(StringUtils.isNotBlank(dto.getLibNumber())) {
            book.setLibNumber(dto.getLibNumber());
        }
        if(StringUtils.isNotBlank(dto.getStatus())) {
            book.setStatus(dto.getStatus());
        }
        bookRepository.save(book);
        return book;
    }

    @Override
    public void deleteBook(Long id) throws ResourceNotFoundException {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        bookRepository.delete(book);
    }
}
