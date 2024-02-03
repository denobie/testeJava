package com.elotechdenobie.testejava.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpHeaders;

@EqualsAndHashCode(callSuper = true)
@Data
public class ValidationException extends RuntimeException{
    private final HttpHeaders headers;
    public ValidationException(String message) {
        this(message, new HttpHeaders());
    }

    public ValidationException(String message, HttpHeaders headers) {
        super(message);
        this.headers = headers;
    }
}
