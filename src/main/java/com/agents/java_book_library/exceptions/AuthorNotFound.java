package com.agents.java_book_library.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.text.MessageFormat;

@RequiredArgsConstructor
@Getter
@ToString
public class AuthorNotFound extends BusinessException {

    private final Long authorId;

    @Override
    public String getMessage() {
        return MessageFormat.format("Author {0} not found.", authorId);
    }

    @Override
    public HttpStatus status() {
        return HttpStatus.NOT_FOUND;
    }
}
