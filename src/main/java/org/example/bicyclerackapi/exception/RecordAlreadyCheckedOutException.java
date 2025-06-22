package org.example.bicyclerackapi.exception;

public class RecordAlreadyCheckedOutException extends RuntimeException {
    public RecordAlreadyCheckedOutException(String message) {
        super(message);
    }
}
