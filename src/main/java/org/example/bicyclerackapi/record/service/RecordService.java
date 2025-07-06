package org.example.bicyclerackapi.record.service;

import org.example.bicyclerackapi.record.model.Record;
import org.example.bicyclerackapi.record.model.RecordPageResponse;
import org.example.bicyclerackapi.record.model.RecordRequest;

public interface RecordService {
    Record createRecord(RecordRequest request);

    RecordPageResponse getRecords(String pageToken, int maxPageSize, String filter);

    Record getRecordById(Long id);

    Record updateRecord(Long id, RecordRequest request);

    Record checkOutRecord(Long id);
}
