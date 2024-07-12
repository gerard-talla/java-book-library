package com.agents.java_book_library.services;

import com.agents.java_book_library.domains.Book;
import com.agents.java_book_library.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getOneBook(Long bookId) {
        // TODO: Add custom exception
        return bookRepository.findById(bookId).orElseThrow(() -> null);
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(Book book) {
        Book found = getOneBook(book.getBookId());

        found = Book.builder()
                .title(book.getTitle())
                .genre(book.getGenre())
                .price(book.getPrice())
                .author(book.getAuthor())
                .build();
        return bookRepository.save(found);
    }

    public void deleteBook(Long bookId) {
        bookRepository.findById(bookId).orElseThrow(() -> null);
        bookRepository.deleteById(bookId);
    }
}
