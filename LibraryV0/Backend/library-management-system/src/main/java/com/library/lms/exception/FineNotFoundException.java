package com.library.lms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FineNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L; // Add this line

    public FineNotFoundException(String message) {
        super(message);
    }

    public FineNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
