package org.example.bicyclerackapi.exception;

public class StudentHasANotCheckedOutRecordException extends RuntimeException {
    public StudentHasANotCheckedOutRecordException(String message) {
        super(message);
    }
}
