package com.agents.java_book_library.services;

import com.agents.java_book_library.domains.Book;
import com.agents.java_book_library.exceptions.BookNotFound;
import com.agents.java_book_library.mappers.BookMapper;
import com.agents.java_book_library.model.BookDTO;
import com.agents.java_book_library.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper mapper;

    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(mapper::toBookDTO)
                .collect(Collectors.toList());
    }

    public List<BookDTO> getAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable).stream()
                .map(mapper::toBookDTO)
                .collect(Collectors.toList());
    }

    public BookDTO getOneBook(Long bookId) {
        return mapper.toBookDTO(bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFound(bookId)));
    }

    public BookDTO createBook(BookDTO bookDTO) {
        Book book = mapper.toBook(bookDTO);
        return mapper.toBookDTO(bookRepository.save(book));
    }

    public BookDTO updateBook(BookDTO bookDTO) {
        BookDTO found = getOneBook(bookDTO.getBookId());
        found.setTitle(bookDTO.getTitle());
        found.setGenre(bookDTO.getGenre());
        found.setPrice(bookDTO.getPrice());
        found.setAuthor(bookDTO.getAuthor());

        Book book = mapper.toBook(found);

        return mapper.toBookDTO(bookRepository.save(book));
    }

    public void deleteBook(Long bookId) {
        bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFound(bookId));
        bookRepository.deleteById(bookId);
    }
}
