package org.example.bicyclerackapi.exception.custom;

public class BicycleRackIsFullException extends RuntimeException {
    public BicycleRackIsFullException(String message) {
        super(message);
    }
}
