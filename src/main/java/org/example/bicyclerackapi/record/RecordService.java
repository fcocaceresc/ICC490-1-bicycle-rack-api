package org.example.bicyclerackapi.record;

import org.example.bicyclerackapi.exception.RecordRequest;

import java.util.List;

public interface RecordService {
    Record createRecord(RecordRequest request);

    List<Record> getAllRecords();

    Record getRecordById(Long id);

    Record updateRecord(Long id, RecordRequest request);

    Record checkOutRecord(Long id);
}
