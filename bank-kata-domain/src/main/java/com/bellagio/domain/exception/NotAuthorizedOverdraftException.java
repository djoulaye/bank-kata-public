package com.bellagio.domain.exception;

public class NotAuthorizedOverdraftException extends RuntimeException {
    public NotAuthorizedOverdraftException(String message) {
        super(message);
    }
}
