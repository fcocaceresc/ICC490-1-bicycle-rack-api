package org.example.bicyclerackapi.exception.custom;

public class RecordAlreadyCheckedOutException extends RuntimeException {
    public RecordAlreadyCheckedOutException(String message) {
        super(message);
    }
}
