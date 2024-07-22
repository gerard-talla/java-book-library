package com.agents.java_book_library.mappers;

import com.agents.java_book_library.domains.Author;
import com.agents.java_book_library.model.AuthorDTO;
import org.mapstruct.Mapper;

@Mapper
public interface AuthorMapper {

    AuthorDTO toAuthorDTO(Author author);
    Author toAuthor(AuthorDTO authorDTO);
}
