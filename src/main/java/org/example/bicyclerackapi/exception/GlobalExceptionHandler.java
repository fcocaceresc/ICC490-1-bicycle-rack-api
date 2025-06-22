package org.example.bicyclerackapi.exception;

import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ValidationErrorResponse> handleValidationExceptions(MethodArgumentNotValidException exception) {
        List<String> errorMessages = exception.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();
        ValidationErrorResponse errorResponse = new ValidationErrorResponse(400, errorMessages);
        return ResponseEntity.status(400).body(errorResponse);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleStudentHasANotCheckedOutRecordException(StudentHasANotCheckedOutRecordException exception) {
        ErrorResponse errorResponse = new ErrorResponse(409, exception.getMessage());
        return ResponseEntity.status(409).body(errorResponse);
    }

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

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleInvalidDataAccessResourceUsageException(InvalidDataAccessResourceUsageException exception) {
        ErrorResponse errorResponse = new ErrorResponse(503, "The database schema is invalid. Unable to process the request.");
        return ResponseEntity.status(503).body(errorResponse);
    }
}
