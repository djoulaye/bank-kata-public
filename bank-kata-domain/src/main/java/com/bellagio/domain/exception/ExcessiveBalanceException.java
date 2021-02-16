package com.bellagio.domain.exception;

public class ExcessiveBalanceException extends RuntimeException {
    public ExcessiveBalanceException(String message) {
        super(message);
    }
}
