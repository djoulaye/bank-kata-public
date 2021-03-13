package com.bellagio.domain.exception;

public class TooManyDepositException extends RuntimeException {
    public TooManyDepositException(String message) {
        super(message);
    }
}
