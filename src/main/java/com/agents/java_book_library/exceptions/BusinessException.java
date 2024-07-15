package com.agents.java_book_library.exceptions;

import org.springframework.http.HttpStatus;

public abstract class BusinessException extends RuntimeException {

    public abstract HttpStatus status();
}
