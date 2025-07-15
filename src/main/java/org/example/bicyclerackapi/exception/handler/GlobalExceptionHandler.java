package org.example.bicyclerackapi.exception.handler;

import org.example.bicyclerackapi.exception.custom.*;
import org.example.bicyclerackapi.exception.response.ErrorResponse;
import org.example.bicyclerackapi.exception.response.ValidationErrorResponse;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

/**
 * Clase que se encarga de capturar y manejar las excepciones de la aplicación.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Maneja las excepciones de las anotaciones de validación de jakarta bean validation.
     *
     * @param exception
     * @return
     */
    @ExceptionHandler
    public ResponseEntity<ValidationErrorResponse> handleValidationExceptions(MethodArgumentNotValidException exception) {
        List<String> errorMessages = exception.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();
        ValidationErrorResponse errorResponse = new ValidationErrorResponse(400, errorMessages);
        return ResponseEntity.status(400).body(errorResponse);
    }

    /**
     * Maneja las excepciones de tipo StudentHasANotCheckedOutRecordException, que se lanza cuando un estudiante intenta estacionar una bicicleta cuando ya tiene una estacionada.
     *
     * @param exception
     * @return
     */
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleStudentHasANotCheckedOutRecordException(StudentHasANotCheckedOutRecordException exception) {
        ErrorResponse errorResponse = new ErrorResponse(409, exception.getMessage());
        return ResponseEntity.status(409).body(errorResponse);
    }

    /**
     * Maneja las excepciones de tipo MethodArgumentTypeMismatchException como cuando un parámetro de url no es del tipo esperado.
     *
     * @param exception
     * @return
     */
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {
        ErrorResponse errorResponse = new ErrorResponse(400, "The parameter " + exception.getName() + " should be of type " + exception.getRequiredType());
        return ResponseEntity.status(400).body(errorResponse);
    }

    /**
     * Maneja las excepciones de tipo RecordNotFoundException, que se lanza cuando no se encuentra un registro.
     *
     * @param exception
     * @return
     */
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleRecordNotFoundException(RecordNotFoundException exception) {
        ErrorResponse errorResponse = new ErrorResponse(404, exception.getMessage());
        return ResponseEntity.status(404).body(errorResponse);
    }

    /**
     * Maneja las excepciones de tipo RecordAlreadyCheckedOutException, que se lanza cuando un registro ya está marcado como retirado.
     *
     * @param exception
     * @return
     */
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleRecordAlreadyCheckedOutException(RecordAlreadyCheckedOutException exception) {
        ErrorResponse errorResponse = new ErrorResponse(409, exception.getMessage());
        return ResponseEntity.status(409).body(errorResponse);
    }

    /**
     * Maneja las excepciones de tipo CannotCreateTransactionException, que se lanza cuando ocurre un timeout.
     *
     * @param exception
     * @return
     */
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleCannotCreateTransactionException(CannotCreateTransactionException exception) {
        ErrorResponse errorResponse = new ErrorResponse(503, "The database is currently unavailable. Unable to process the request.");
        return ResponseEntity.status(503).body(errorResponse);
    }

    /**
     * Maneja las excepciones de tipo InvalidDataAccessResourceUsageException, que se lanza cuando el esquema no coincide con las entidades del modelo.
     *
     * @param exception
     * @return
     */
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleInvalidDataAccessResourceUsageException(InvalidDataAccessResourceUsageException exception) {
        ErrorResponse errorResponse = new ErrorResponse(503, "The database schema is invalid. Unable to process the request.");
        return ResponseEntity.status(503).body(errorResponse);
    }

    /**
     * Maneja las excepciones de tipo InvalidPageTokenException, que se lanza cuando el token de paginación es inválido.
     *
     * @param exception
     * @return
     */
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleInvalidPageTokenException(InvalidPageTokenException exception) {
        ErrorResponse errorResponse = new ErrorResponse(400, exception.getMessage());
        return ResponseEntity.status(400).body(errorResponse);
    }

    /**
     * Maneja las excepciones de tipo InvalidFilterException, que se lanza cuando el filtro es inválido.
     *
     * @param exception
     * @return
     */
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleInvalidFilterException(InvalidFilterException exception) {
        ErrorResponse errorResponse = new ErrorResponse(400, exception.getMessage());
        return ResponseEntity.status(400).body(errorResponse);
    }

    /**
     * Maneja las excepciones de tipo RackNotFoundException, que se lanza cuando no se encuentra un bicicletero.
     *
     * @param exception
     * @return
     */
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleRackNotFoundException(RackNotFoundException exception) {
        ErrorResponse errorResponse = new ErrorResponse(404, exception.getMessage());
        return ResponseEntity.status(404).body(errorResponse);
    }

    /**
     * Maneja las excepciones de tipo InvalidHookException, que se lanza cuando un gancho no existe.
     *
     * @param exception
     * @return
     */
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleInvalidHookException(InvalidHookException exception) {
        ErrorResponse errorResponse = new ErrorResponse(400, exception.getMessage());
        return ResponseEntity.status(400).body(errorResponse);
    }

    /**
     * Maneja las excepciones de tipo HookIsOccupiedException, que se lanza cuando un gancho ya está ocupado.
     *
     * @param exception
     * @return
     */
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleHookIsOccupiedException(HookIsOccupiedException exception) {
        ErrorResponse errorResponse = new ErrorResponse(409, exception.getMessage());
        return ResponseEntity.status(409).body(errorResponse);
    }

    /**
     * Maneja las excepciones de tipo HttpMessageNotReadableException, que se lanza cuando el body del request no es válido o el tipo de dato de uno de sus campos no es el esperado.
     *
     * @param exception
     * @return
     */
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
        ErrorResponse errorResponse = new ErrorResponse(400, "Invalid request body.");
        return ResponseEntity.status(400).body(errorResponse);
    }
}
