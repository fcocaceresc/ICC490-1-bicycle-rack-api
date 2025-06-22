package org.example.bicyclerackapi.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<RecordErrorResponse> handleException(RecordNotFoundException exception) {
        RecordErrorResponse errorResponse = new RecordErrorResponse(404, exception.getMessage());
        return ResponseEntity.status(404).body(errorResponse);
    }
}
