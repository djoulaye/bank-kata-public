package fr.ing.interview.application.exception;

public class AlreadyExistsAccountException extends RuntimeException {
    public AlreadyExistsAccountException(String message) {
        super(message);
    }

}
