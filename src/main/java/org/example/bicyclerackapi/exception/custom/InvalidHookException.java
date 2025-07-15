package org.example.bicyclerackapi.exception.custom;

public class InvalidHookException extends RuntimeException {
    public InvalidHookException(String message) {
        super(message);
    }
}
