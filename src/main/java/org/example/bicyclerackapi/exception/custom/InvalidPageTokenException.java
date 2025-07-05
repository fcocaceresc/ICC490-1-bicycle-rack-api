package org.example.bicyclerackapi.exception.custom;

public class InvalidPageTokenException extends RuntimeException {
    public InvalidPageTokenException(String message) {
        super(message);
    }
}
