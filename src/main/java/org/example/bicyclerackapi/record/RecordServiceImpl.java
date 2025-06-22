package org.example.bicyclerackapi.record;

import org.example.bicyclerackapi.exception.RecordNotFoundException;
import org.example.bicyclerackapi.exception.RecordRequest;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {
    private final RecordRepository recordRepository;

    public RecordServiceImpl(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    @Override
    public Record createRecord(RecordRequest request) {
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
        existingRecord.setCheckOut(Instant.now());
        return recordRepository.save(existingRecord);
    }
}
