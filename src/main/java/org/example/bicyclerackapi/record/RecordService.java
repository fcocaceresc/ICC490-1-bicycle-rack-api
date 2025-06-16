package org.example.bicyclerackapi.record;

import java.util.List;

public interface RecordService {
    Record createRecord(RecordRequest request);

    List<Record> getAllRecords();

    Record getRecordById(Long id);

    Record updateRecord(Long id, RecordRequest request);
}
