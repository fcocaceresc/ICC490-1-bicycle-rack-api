package org.example.bicyclerackapi.record;

import java.util.List;

public interface RecordService {
    Record createRecord(CreateRecordRequest request);

    List<Record> getAllRecords();
}
