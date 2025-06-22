package org.example.bicyclerackapi.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleRecordNotFoundException(RecordNotFoundException exception) {
        ErrorResponse errorResponse = new ErrorResponse(404, exception.getMessage());
        return ResponseEntity.status(404).body(errorResponse);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleCannotCreateTransactionException(CannotCreateTransactionException exception) {
        ErrorResponse errorResponse = new ErrorResponse(503, "The database is currently unavailable. Unable to process the request.");
        return ResponseEntity.status(503).body(errorResponse);
    }
}
