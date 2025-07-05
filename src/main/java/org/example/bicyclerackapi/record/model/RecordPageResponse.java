package org.example.bicyclerackapi.record.model;

import java.util.List;

public class RecordPageResponse {
    private List<Record> records;
    private String nextPageToken;

    public RecordPageResponse(List<Record> records, String nextPageToken) {
        this.records = records;
        this.nextPageToken = nextPageToken;
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }
}
