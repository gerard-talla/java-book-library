package com.agents.java_book_library.controllers;

import com.agents.java_book_library.api.BookApi;
import com.agents.java_book_library.model.BookDTO;
import com.agents.java_book_library.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookController implements BookApi {

    private final BookService bookService;

    @Override
    public ResponseEntity<BookDTO> addBook(BookDTO bookDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bookService.createBook(bookDTO));
    }

    @Override
    public ResponseEntity<Void> deleteBook(Long bookId) {
        bookService.deleteBook(bookId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @Override
    public ResponseEntity<List<BookDTO>> findAllBook() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @Override
    public ResponseEntity<BookDTO> getBookById(Long bookId) {
        return ResponseEntity.ok(bookService.getOneBook(bookId));
    }

    @Override
    public ResponseEntity<BookDTO> updateBook(BookDTO bookDTO) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(bookService.updateBook(bookDTO));
    }
}
