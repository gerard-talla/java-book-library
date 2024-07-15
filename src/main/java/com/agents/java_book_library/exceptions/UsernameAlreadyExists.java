package com.agents.java_book_library.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.text.MessageFormat;

@RequiredArgsConstructor
@Getter
@ToString
public class UsernameAlreadyExists extends BusinessException {

    private final String username;

    @Override
    public HttpStatus status() {
        return HttpStatus.CONFLICT;
    }

    @Override
    public String getMessage() {
        return MessageFormat.format("Another member with username {0} already exists.", username);
    }
}
