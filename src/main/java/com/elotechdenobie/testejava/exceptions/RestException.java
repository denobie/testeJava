package com.elotechdenobie.testejava.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
@EqualsAndHashCode(callSuper = true)
@Data
public class RestException extends RuntimeException{
    private final HttpStatus httpStatus;
    private final HttpHeaders headers;
    public RestException(HttpStatus status, String message) {
        this(status, message, new HttpHeaders());
    }

    public RestException(HttpStatus status, String message, HttpHeaders headers) {
        super(message);
        this.httpStatus = status;
        this.headers = headers;
    }
}
