package fr.ing.interview.application.exception;

public class UnknownAccountException extends RuntimeException {
    public UnknownAccountException(String message) {
        super(message);
    }
}
