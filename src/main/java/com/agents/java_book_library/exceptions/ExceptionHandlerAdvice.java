package com.agents.java_book_library.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.JDBCException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<ErrorMessage> handleException(BusinessException e) {
        log.info("{}: {}", e.getClass().getSimpleName(), e.getMessage());
        // Error Type will be built here and return with the reponse
        ErrorMessage error = createMessage(e.status().value(), e.getMessage());
        return ResponseEntity.status(e.status()).body(error);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> handleException(Exception exc) {
        ErrorMessage error = createMessage(HttpStatus.BAD_REQUEST.value(), exc.getMessage());
        return ResponseEntity.status(error.getStatus()).body(error);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> handleException(JDBCException exc) {
        ErrorMessage error = createMessage(HttpStatus.UNPROCESSABLE_ENTITY.value(), exc.getMessage());
        return ResponseEntity.status(error.getStatus()).body(error);
    }

    private ErrorMessage createMessage(int status, String message) {
        return ErrorMessage.builder()
                .status(status)
                .message(message)
                .timeStamp(System.currentTimeMillis())
                .build();
    }
}
