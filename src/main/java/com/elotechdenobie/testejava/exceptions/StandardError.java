package com.elotechdenobie.testejava.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micrometer.common.util.StringUtils;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.time.Instant;

import static org.springframework.util.StringUtils.hasLength;

@Data
public class StandardError implements Serializable {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private final Instant timeStamp;
    private final Integer status;
    private final String error;
    private final String message;
    private final String path;

    public StandardError(Instant timeStamp, HttpStatus status, String message, String path, String errorMessage) {
        this.timeStamp = timeStamp;
        this.status = status.value();
        this.error = StringUtils.isNotBlank(errorMessage) ? errorMessage : status.getReasonPhrase();
        this.message = message;
        this.path = path;
    }
}
