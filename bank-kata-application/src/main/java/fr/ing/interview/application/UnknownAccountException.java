package fr.ing.interview.application;

public class UnknownAccountException extends RuntimeException {
    public UnknownAccountException(String message) {
        super(message);
    }
}
