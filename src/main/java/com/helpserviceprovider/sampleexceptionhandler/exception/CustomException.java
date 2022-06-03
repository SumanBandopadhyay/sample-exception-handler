package com.helpserviceprovider.sampleexceptionhandler.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CustomException extends ResponseStatusException {

    public CustomException(HttpStatus status, String message, Throwable e) {
        super(status, message, e);
    }
}
