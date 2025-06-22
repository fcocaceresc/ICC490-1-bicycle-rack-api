package org.example.bicyclerackapi.exception.custom;

public class StudentHasANotCheckedOutRecordException extends RuntimeException {
    public StudentHasANotCheckedOutRecordException(String message) {
        super(message);
    }
}
