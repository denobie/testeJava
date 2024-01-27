package com.elotechdenobie.testejava.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.time.ZoneId;

@ControllerAdvice
public class GlobalResourceException {

    private HttpHeaders generateHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Teste-Java-Exception", "true");
        return httpHeaders;
    }
    @ExceptionHandler(RestException.class)
    public ResponseEntity<StandardError> resourceNotFound(RuntimeException e, HttpServletRequest request){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .headers(generateHeaders())
                .body(new StandardError(LocalDateTime.now().atZone(ZoneId.of("America/Sao_Paulo")).toInstant(),
                                        HttpStatus.NOT_FOUND,
                                        e.getMessage(),
                                        request.getRequestURI(),
                                        ""));
    }

    @ExceptionHandler(DataBaseException.class)
    public ResponseEntity<StandardError> dataBaseException(RuntimeException e, HttpServletRequest request){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .headers(generateHeaders())
                .body(new StandardError(LocalDateTime.now().atZone(ZoneId.of("America/Sao_Paulo")).toInstant(),
                                        HttpStatus.BAD_REQUEST,
                                        e.getMessage(),
                                        request.getRequestURI(),
                                        "DataBaseException"));
    }
}
