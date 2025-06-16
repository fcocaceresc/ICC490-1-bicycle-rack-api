package org.example.bicyclerackapi.record;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {
    private final RecordRepository recordRepository;

    public RecordServiceImpl(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    @Override
    public Record createRecord(CreateRecordRequest request) {
        Record newRecord = new Record(request.getStudentId(), request.getStudentName(), request.getBicycleDescription());
        return recordRepository.save(newRecord);
    }

    @Override
    public List<Record> getAllRecords() {
        return recordRepository.findAll();
    }
}
