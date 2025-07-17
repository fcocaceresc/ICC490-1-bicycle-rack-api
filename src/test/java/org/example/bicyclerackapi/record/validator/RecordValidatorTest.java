package org.example.bicyclerackapi.record.validator;

import org.example.bicyclerackapi.exception.custom.HookIsOccupiedException;
import org.example.bicyclerackapi.exception.custom.InvalidHookException;
import org.example.bicyclerackapi.exception.custom.RecordAlreadyCheckedOutException;
import org.example.bicyclerackapi.exception.custom.StudentHasANotCheckedOutRecordException;
import org.example.bicyclerackapi.rack.model.Rack;
import org.example.bicyclerackapi.record.model.Record;
import org.example.bicyclerackapi.record.utils.RecordValidator;
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
    void validateHookExists_HookExists() {
        Rack rack = new Rack(1L, 2L, 2L, 4L);
        assertDoesNotThrow(() -> {
            recordValidator.validateHookExists(rack, 1L);
        });
    }

    @Test
    void validateHookExists_HookDoesNotExist() {
        Rack rack = new Rack(1L, 2L, 2L, 4L);
        Exception exception = assertThrows(InvalidHookException.class, () -> {
            recordValidator.validateHookExists(rack, 5L);
        });
        assertEquals("The hook does not exist in the rack", exception.getMessage());
    }

    @Test
    void validateHookIsNotOccupied_HookIsNotOccupied() {
        assertDoesNotThrow(() -> {
            recordValidator.validateHookIsNotOccupied(false);
        });
    }

    @Test
    void validateHookIsNotOccupied_HookIsOccupied() {
        Exception exception = assertThrows(HookIsOccupiedException.class, () -> {
            recordValidator.validateHookIsNotOccupied(true);
        });
        assertEquals("The hook is already occupied", exception.getMessage());
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
        Rack rack = new Rack(1L, 2L, 2L, 4L);
        Record record = new Record("123456789-25", "amadeus", "oxford", rack, 1L);
        record.setCheckOut(null);
        assertDoesNotThrow(() -> {
            recordValidator.validateRecordIsNotAlreadyCheckedOut(record);
        });
    }

    @Test
    void validateRecordIsNotAlreadyCheckedOut_RecordIsAlreadyCheckedOut() {
        Rack rack = new Rack(1L, 2L, 2L, 4L);
        Record record = new Record("123456789-25", "amadeus", "oxford", rack, 1L);
        record.setCheckOut(Instant.now());
        Exception exception = assertThrows(RecordAlreadyCheckedOutException.class, () -> {
            recordValidator.validateRecordIsNotAlreadyCheckedOut(record);
        });
        assertEquals("The record is already checked out", exception.getMessage());
    }
}