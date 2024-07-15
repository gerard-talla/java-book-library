package com.agents.java_book_library.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

import java.text.MessageFormat;

@RequiredArgsConstructor
@Getter
@ToString
public class MemberNotFound extends BusinessException {

    private final String username;
    private final Long memberId;

    @Override
    public String getMessage() {
        return MessageFormat.format("Member {0} not found.", StringUtils.isNotBlank(username) ? username : memberId);
    }

    @Override
    public HttpStatus status() {
        return HttpStatus.NOT_FOUND;
    }
}
