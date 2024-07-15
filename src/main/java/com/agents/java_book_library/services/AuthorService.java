package com.agents.java_book_library.services;

import com.agents.java_book_library.domains.Author;
import com.agents.java_book_library.exceptions.AuthorNotFound;
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

    public Author getOneAuthor(Long authorId) {
        return authorRepository.findById(authorId)
                .orElseThrow(() -> new AuthorNotFound(authorId));
    }

    public Author createAuthor(Author author) {
        Author save = authorRepository.save(author);
        return save;
    }

    public Author updateAuthor(Author author) {
        Author found = getOneAuthor(author.getAuthorId());

        found = Author.builder()
                .name(author.getName())
                .dateOfBirth(author.getDateOfBirth())
                .books(author.getBooks())
                .build();

        // Update the Author
        return authorRepository.save(found);
    }

    public void deleteAuthor(Long authorId) {
        authorRepository.findById(authorId)
                .orElseThrow(() -> new AuthorNotFound(authorId));
        authorRepository.deleteById(authorId);
    }


}
