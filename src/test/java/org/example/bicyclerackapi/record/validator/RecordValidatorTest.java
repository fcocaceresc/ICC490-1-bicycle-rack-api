package org.example.bicyclerackapi.record.validator;

import org.example.bicyclerackapi.exception.custom.BicycleRackIsFullException;
import org.example.bicyclerackapi.exception.custom.RecordAlreadyCheckedOutException;
import org.example.bicyclerackapi.exception.custom.StudentHasANotCheckedOutRecordException;
import org.example.bicyclerackapi.record.model.Record;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class RecordValidatorTest {
    private RecordValidator recordValidator;

    @BeforeEach
    void setUp() {
        recordValidator = new RecordValidator();
    }

    @Test
    void validateBicycleRackCapacity_BicycleRackIsNotFull() {
        assertDoesNotThrow(() -> {
            recordValidator.validateBicycleRackCapacity(10, 5);
        });
    }

    @Test
    void validateBicycleRackCapacity_BicycleRackIsFull() {
        Exception exception = assertThrows(BicycleRackIsFullException.class, () -> {
            recordValidator.validateBicycleRackCapacity(10, 10);
        });
        assertEquals("The bicycle rack is full", exception.getMessage());
    }

    @Test
    void validateStudentHasANotCheckedOutRecord_StudentHasNoNotCheckedOutRecord() {
        assertDoesNotThrow(() -> {
            recordValidator.validateStudentHasANotCheckedOutRecord(false);
        });
    }

    @Test
    void validateStudentHasANotCheckedOutRecord_StudentHasANotCheckedOutRecord() {
        Exception exception = assertThrows(StudentHasANotCheckedOutRecordException.class, () -> {
            recordValidator.validateStudentHasANotCheckedOutRecord(true);
        });
        assertEquals("The student has a not checked out record", exception.getMessage());
    }

    @Test
    void validateRecordIsNotAlreadyCheckedOut_RecordIsNotCheckedOut() {
        Record record = new Record("123456789-25", "amadeus", "oxford");
        record.setCheckOut(null);
        assertDoesNotThrow(() -> {
            recordValidator.validateRecordIsNotAlreadyCheckedOut(record);
        });
    }

    @Test
    void validateRecordIsNotAlreadyCheckedOut_RecordIsAlreadyCheckedOut() {
        Record record = new Record("123456789-25", "amadeus", "oxford");
        record.setCheckOut(Instant.now());
        Exception exception = assertThrows(RecordAlreadyCheckedOutException.class, () -> {
            recordValidator.validateRecordIsNotAlreadyCheckedOut(record);
        });
        assertEquals("The record is already checked out", exception.getMessage());
    }
}