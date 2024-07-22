package com.agents.java_book_library.mappers;

import com.agents.java_book_library.domains.Book;
import com.agents.java_book_library.model.BookDTO;
import org.mapstruct.Mapper;

@Mapper
public interface BookMapper {

    Book toBook(BookDTO bookDTO);
    BookDTO toBookDTO(Book book);
}
