package com.obinna.libraryuser.controller;

import com.obinna.libraryuser.dto.BookDto;
import com.obinna.libraryuser.model.Book;
import com.obinna.libraryuser.repository.BookRepository;
import com.obinna.libraryuser.service.BookService;
import com.obinna.libraryuser.utils.NotCreatedException;
import com.obinna.libraryuser.utils.ResourceNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    BookService bookService;

    @Autowired
    BookRepository bookRepository;

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

        List<Book> books;
        int limitFetch = limit.map(integer -> (integer > 100 ? 100 : integer)).orElse(20); // place a minimum restriction of 20, max restriction of 100
        Pageable pagination = PageRequest.of(page.orElse(0), limitFetch,
                Sort.Direction.ASC, "name");

        String searchName = bookName.orElse("");
        String searchAuthor = author.orElse("");
        books = bookRepository.findBooksByNameAndAuthor(searchName, searchAuthor, pagination);
        return ResponseEntity.ok(books);
    }

    @ApiOperation(value = "fetch single book", response = Book.class)
    @GetMapping("{id}")
    public ResponseEntity<Book> getBucketList(@ApiParam(value = "bucket list id", required = true)
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
