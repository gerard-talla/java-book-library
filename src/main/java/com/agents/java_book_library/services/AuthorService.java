package com.agents.java_book_library.services;

import com.agents.java_book_library.domains.Author;
import com.agents.java_book_library.exceptions.AuthorNotFound;
import com.agents.java_book_library.mappers.AuthorMapper;
import com.agents.java_book_library.model.AuthorDTO;
import com.agents.java_book_library.repositories.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper mapper;

    public List<AuthorDTO> getAllAuthor() {
        return authorRepository.findAll()
                .stream().map(mapper::toAuthorDTO)
                .collect(Collectors.toList());
    }

    public AuthorDTO getOneAuthor(Long authorId) {
        return mapper.toAuthorDTO(authorRepository.findById(authorId)
                .orElseThrow(() -> new AuthorNotFound(authorId)));
    }

    public AuthorDTO createAuthor(AuthorDTO authorDTO) {
        Author author = mapper.toAuthor(authorDTO);
        return mapper.toAuthorDTO(authorRepository.save(author));
    }

    public AuthorDTO updateAuthor(AuthorDTO authorDTO) {
        AuthorDTO found = getOneAuthor(authorDTO.getAuthorId());
        found.setName(authorDTO.getName());
        found.setDateOfBirth(authorDTO.getDateOfBirth());
        found.setBooks(authorDTO.getBooks());

        Author author = mapper.toAuthor(found);
        // Update the Author
        return mapper.toAuthorDTO(authorRepository.save(author));
    }

    public void deleteAuthor(Long authorId) {
        authorRepository.findById(authorId)
                .orElseThrow(() -> new AuthorNotFound(authorId));
        authorRepository.deleteById(authorId);
    }
}
