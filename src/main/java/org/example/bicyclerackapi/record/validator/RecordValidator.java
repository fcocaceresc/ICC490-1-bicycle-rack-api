package org.example.bicyclerackapi.record.validator;

import org.example.bicyclerackapi.exception.custom.BicycleRackIsFullException;
import org.example.bicyclerackapi.exception.custom.RecordAlreadyCheckedOutException;
import org.example.bicyclerackapi.exception.custom.StudentHasANotCheckedOutRecordException;
import org.example.bicyclerackapi.record.model.Record;
import org.springframework.stereotype.Component;

@Component
public class RecordValidator {
    public void validateBicycleRackCapacity(long capacity, long currentNotCheckedOutRecordsCount) {
        if (capacity <= currentNotCheckedOutRecordsCount) {
            throw new BicycleRackIsFullException("The bicycle rack is full");
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
