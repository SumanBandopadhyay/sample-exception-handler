package com.helpserviceprovider.sampleexceptionhandler.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CustomException extends ResponseStatusException {

    private String message;

    public CustomException(HttpStatus status, String message, Throwable e) {
        super(status, message, e);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
