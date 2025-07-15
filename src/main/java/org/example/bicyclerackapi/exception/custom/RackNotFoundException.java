package org.example.bicyclerackapi.exception.custom;

public class RackNotFoundException extends RuntimeException {
    public RackNotFoundException(String message) {
        super(message);
    }
}
