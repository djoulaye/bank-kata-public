package fr.ing.interview.domain.exception;

public class IllegalWithdrawAmountException extends  RuntimeException{
    public IllegalWithdrawAmountException(String message) {
        super(message);
    }
}

