package com.agents.java_book_library.services;

import com.agents.java_book_library.domains.Author;
import com.agents.java_book_library.repositories.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AuthorService {
    @Autowired
    private  AuthorRepository authorRepository;

    public List<Author> getAllAuthor() {
        return authorRepository.findAll();
    }

    public Author getOneAuthor(Integer authorId) {
        // TODO: Add custom exception
        return authorRepository.findById(authorId).orElseThrow(null);
    }

    public Author createAuthor(Author author) {
        Author save = authorRepository.save(author);
        return save;
    }

    public Author updateAuthor(Author author) {
        // TODO: Add custom exception
        Author found = authorRepository.findById(author.getAuthorId()).orElseThrow(null);

        found = Author.builder()
                .name(author.getName())
                .dateOfBirth(author.getDateOfBirth())
                .books(author.getBooks())
                .build();

        // Update the Author
        return authorRepository.save(found);
    }

    public void deleteAuthor(Integer authorId) {
        // TODO: Add custom exception
        authorRepository.findById(authorId).orElseThrow(() -> null);
        authorRepository.deleteById(authorId);
    }


}
