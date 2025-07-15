package org.example.bicyclerackapi.exception.custom;

public class HookIsOccupiedException extends RuntimeException {
    public HookIsOccupiedException(String message) {
        super(message);
    }
}
