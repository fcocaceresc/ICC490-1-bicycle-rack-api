package org.example.bicyclerackapi.exception;

import java.time.Instant;
import java.util.List;

public class ValidationErrorResponse {
    private Instant timestamp;
    private int status;
    private List<String> error;

    public ValidationErrorResponse(int status, List<String> error) {
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

    public List<String> getError() {
        return error;
    }

    public void setError(List<String> error) {
        this.error = error;
    }
}
