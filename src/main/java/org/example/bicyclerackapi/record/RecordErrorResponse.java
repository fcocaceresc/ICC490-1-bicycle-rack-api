package org.example.bicyclerackapi.record;

import java.time.Instant;

public class RecordErrorResponse {
    private Instant timestamp;
    private int status;
    private String error;

    public RecordErrorResponse(int status, String error) {
        this.timestamp = Instant.now();
        this.status = status;
        this.error = error;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
