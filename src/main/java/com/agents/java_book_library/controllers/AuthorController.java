package com.agents.java_book_library.controllers;

import com.agents.java_book_library.model.AuthorDTO;
import com.agents.java_book_library.services.AuthorService;
import com.agents.java_book_library.api.AuthorApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthorController implements AuthorApi {

    private final AuthorService authorService;

    @Override
    public ResponseEntity<AuthorDTO> addAuthor(AuthorDTO authorDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(authorService.createAuthor(authorDTO));
    }

    @Override
    public ResponseEntity<Void> deleteAuthor(Long authorId) {
        authorService.deleteAuthor(authorId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @Override
    public ResponseEntity<List<AuthorDTO>> findAllAuthor() {
        return ResponseEntity.ok(authorService.getAllAuthor());
    }

    @Override
    public ResponseEntity<AuthorDTO> getAuthorById(Long authorId) {
        return ResponseEntity.ok(authorService.getOneAuthor(authorId));
    }

    @Override
    public ResponseEntity<AuthorDTO> updateAuthor(AuthorDTO authorDTO) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(authorService.updateAuthor(authorDTO));
    }
}
