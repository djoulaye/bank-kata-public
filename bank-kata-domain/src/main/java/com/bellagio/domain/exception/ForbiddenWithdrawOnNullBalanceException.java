package com.bellagio.domain.exception;

public class ForbiddenWithdrawOnNullBalanceException extends RuntimeException {
    public ForbiddenWithdrawOnNullBalanceException(String message) {
        super(message);
    }

}
