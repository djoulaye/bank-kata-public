package fr.ing.interview.domain.exception;

public class NotAuthorizedOverdraftException extends RuntimeException {
    public NotAuthorizedOverdraftException(String message) {
        super(message);
    }
}
