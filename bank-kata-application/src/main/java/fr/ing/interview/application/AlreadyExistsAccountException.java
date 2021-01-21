package fr.ing.interview.application;

public class AlreadyExistsAccountException extends RuntimeException {
    public AlreadyExistsAccountException(String message) {
        super(message);
    }

}
