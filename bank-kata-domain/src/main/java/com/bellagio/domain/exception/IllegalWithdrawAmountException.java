package com.bellagio.domain.exception;

public class IllegalWithdrawAmountException extends  RuntimeException{
    public IllegalWithdrawAmountException(String message) {
        super(message);
    }
}

