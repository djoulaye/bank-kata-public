package com.bellagio.domain.exception;

public class TooManyWithdrawalException extends RuntimeException {
    public TooManyWithdrawalException(String message) {
        super(message);
    }
}
