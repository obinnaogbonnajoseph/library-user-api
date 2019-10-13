package com.obinna.libraryuser.controller;

import com.obinna.libraryuser.dao.AppRepository;
import com.obinna.libraryuser.dto.BookDto;
import com.obinna.libraryuser.model.Book;
import com.obinna.libraryuser.model.QBook;
import com.obinna.libraryuser.repository.BookRepository;
import com.obinna.libraryuser.service.BookService;
import com.obinna.libraryuser.utils.NotCreatedException;
import com.obinna.libraryuser.utils.ResourceNotFoundException;
import com.querydsl.jpa.impl.JPAQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Api(value = "Librarian User API")
@RestController
@RequestMapping("librarian")
public class LibrarianController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AppRepository appRepository;

    @ApiOperation(value = "create a new book", response = Book.class)
    @PostMapping()
    public ResponseEntity<Book> createBook(@ApiParam(value = "book body", required = true)
                                                            @NotNull @Valid @RequestBody BookDto dto) throws
            NotCreatedException, ResourceNotFoundException {
        return ResponseEntity.ok(bookService.createBook(dto));
    }

    @ApiOperation(value = "fetch books", response = List.class)
    @GetMapping()
    public ResponseEntity<List<Book>> getBucketLists(@ApiParam(value = "book name")
                                                               @RequestParam("name") Optional<String> bookName,
                                                     @ApiParam(value = "author") @RequestParam("author") Optional<String> author,
                                                     @ApiParam(value = "page") @RequestParam("page") Optional<Integer> page,
                                                     @ApiParam(value = "limit") @RequestParam("limit") Optional<Integer> limit) {
        QBook book = QBook.book;
        JPAQuery<Book> bookJPAQuery = appRepository.startJPAQuery(book);
        bookName.ifPresent(nameOfBook -> bookJPAQuery.where(book.name.containsIgnoreCase(nameOfBook)));
        author.ifPresent(searchAuthor -> bookJPAQuery.where(book.author.containsIgnoreCase(searchAuthor)));
        bookJPAQuery.limit(limit.orElse(10));
        bookJPAQuery.offset(page.orElse(0));
        return ResponseEntity.ok(appRepository.fetchResultList(bookJPAQuery));
    }

    @ApiOperation(value = "fetch single book", response = Book.class)
    @GetMapping("{id}")
    public ResponseEntity<Book> getBucketList(@ApiParam(value = "book id", required = true)
                                                        @PathVariable("id") Long bookId) throws ResourceNotFoundException {
        return ResponseEntity.ok(bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book does not exist")));
    }

    @ApiOperation(value = "update book", response = Book.class)
    @PutMapping("{id}")
    public ResponseEntity<Book> updateBucketList(@ApiParam(value = "book id", required = true)
                                                           @PathVariable("id") Long bookId,
                                                 @ApiParam(value = "book request body", required = true)
                                                            @Valid @RequestBody BookDto dto) throws ResourceNotFoundException {
        dto.setId(bookId);
        return ResponseEntity.ok(bookService.updateBook(dto));
    }

    @ApiOperation(value = "delete book", response = String.class)
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteBucketList(@ApiParam(value = "book id", required = true)
                                                  @PathVariable("id") Long bookId) throws ResourceNotFoundException {
        bookService.deleteBook(bookId);
        return ResponseEntity.status(HttpStatus.OK).body(String.format("Book with id %d deleted", bookId));
    }
}
