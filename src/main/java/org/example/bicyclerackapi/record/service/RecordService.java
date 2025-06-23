package org.example.bicyclerackapi.record.service;

import org.example.bicyclerackapi.record.model.RecordRequest;
import org.example.bicyclerackapi.record.model.Record;

import java.util.List;

public interface RecordService {
    Record createRecord(RecordRequest request);

    List<Record> getAllRecords();

    Record getRecordById(Long id);

    Record updateRecord(Long id, RecordRequest request);

    Record checkOutRecord(Long id);
}
