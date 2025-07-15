package org.example.bicyclerackapi.record.utils;

import org.example.bicyclerackapi.exception.custom.HookIsOccupiedException;
import org.example.bicyclerackapi.exception.custom.InvalidHookException;
import org.example.bicyclerackapi.exception.custom.RecordAlreadyCheckedOutException;
import org.example.bicyclerackapi.exception.custom.StudentHasANotCheckedOutRecordException;
import org.example.bicyclerackapi.rack.model.Rack;
import org.example.bicyclerackapi.record.model.Record;
import org.springframework.stereotype.Component;

@Component
public class RecordValidator {
    public void validateHookExists(Rack rack, Long hook) {
        if (hook <= 0 || hook > rack.getTotalHooks()) {
            throw new InvalidHookException("The hook does not exist in the rack");
        }
    }

    public void validateHookIsNotOccupied(boolean hookIsOccupied) {
        if (hookIsOccupied) {
            throw new HookIsOccupiedException("The hook is already occupied");
        }
    }

    public void validateStudentHasANotCheckedOutRecord(boolean studentHasCheckedOutRecord) {
        if (studentHasCheckedOutRecord) {
            throw new StudentHasANotCheckedOutRecordException("The student has a not checked out record");
        }
    }

    public void validateRecordIsNotAlreadyCheckedOut(Record existingRecord) {
        if (existingRecord.getCheckOut() != null) {
            throw new RecordAlreadyCheckedOutException("The record is already checked out");
        }
    }
}
