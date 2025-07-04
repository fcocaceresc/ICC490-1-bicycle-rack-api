package org.example.bicyclerackapi.record.service;

import org.example.bicyclerackapi.exception.custom.RecordNotFoundException;
import org.example.bicyclerackapi.record.model.Record;
import org.example.bicyclerackapi.record.model.RecordRequest;
import org.example.bicyclerackapi.record.repository.RecordRepository;
import org.example.bicyclerackapi.record.validator.RecordValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {
    private final RecordRepository recordRepository;
    private final RecordValidator recordValidator;
    @Value("${bicycleRack.capacity}")
    private long bicycleRackCapacity;

    public RecordServiceImpl(RecordRepository recordRepository, RecordValidator recordValidator) {
        this.recordRepository = recordRepository;
        this.recordValidator = recordValidator;
    }

    @Override
    public Record createRecord(RecordRequest request) {
        long currentActiveRecordsCount = recordRepository.countByCheckOutIsNull();
        recordValidator.validateBicycleRackCapacity(bicycleRackCapacity, currentActiveRecordsCount);

        boolean studentHasCheckedOutRecord = recordRepository.existsByStudentIdAndCheckOutIsNull(request.getStudentId());
        recordValidator.validateStudentHasANotCheckedOutRecord(studentHasCheckedOutRecord);

        Record newRecord = new Record(request.getStudentId(), request.getStudentName(), request.getBicycleDescription());

        return recordRepository.save(newRecord);
    }

    @Override
    public List<Record> getAllRecords() {
        return recordRepository.findAll();
    }

    @Override
    public Record getRecordById(Long id) {
        return recordRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Record not found"));
    }

    @Override
    public Record updateRecord(Long id, RecordRequest request) {
        Record existingRecord = getRecordById(id);
        existingRecord.setStudentId(request.getStudentId());
        existingRecord.setStudentName(request.getStudentName());
        existingRecord.setBicycleDescription(request.getBicycleDescription());
        return recordRepository.save(existingRecord);
    }

    @Override
    public Record checkOutRecord(Long id) {
        Record existingRecord = getRecordById(id);

        recordValidator.validateRecordIsNotAlreadyCheckedOut(existingRecord);

        existingRecord.setCheckOut(Instant.now());
        return recordRepository.save(existingRecord);
    }
}
