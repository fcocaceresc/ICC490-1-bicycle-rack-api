package org.example.bicyclerackapi.exception;

public class BicycleRackIsFullException extends RuntimeException {
    public BicycleRackIsFullException(String message) {
        super(message);
    }
}
