package com.agents.java_book_library.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.text.MessageFormat;

@RequiredArgsConstructor
@Getter
@ToString
public class LoanBookNotFound extends BusinessException {

    private final Long loanId;

    @Override
    public String getMessage() {
        return MessageFormat.format("Loan {0} not found.", loanId);
    }

    @Override
    public HttpStatus status() {
        return HttpStatus.NOT_FOUND;
    }
}
