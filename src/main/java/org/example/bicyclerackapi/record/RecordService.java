package org.example.bicyclerackapi.record;

import java.util.List;

public interface RecordService {
    Record createRecord(Record record);

    List<Record> getAllRecords();
}
