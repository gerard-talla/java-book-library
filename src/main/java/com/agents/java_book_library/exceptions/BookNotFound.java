package com.agents.java_book_library.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.text.MessageFormat;

@RequiredArgsConstructor
@Getter
@ToString
public class BookNotFound extends BusinessException {

    private final Long bookId;

    @Override
    public String getMessage() {
        return MessageFormat.format("Book {0} not found.", bookId);
    }

    @Override
    public HttpStatus status() {
        return HttpStatus.NOT_FOUND;
    }
}
