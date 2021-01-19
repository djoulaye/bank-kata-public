package fr.ing.interview.domain;

public class NotAuthorizedOverdraftException extends RuntimeException {
    public NotAuthorizedOverdraftException(String message) {
        super(message);
    }
}
