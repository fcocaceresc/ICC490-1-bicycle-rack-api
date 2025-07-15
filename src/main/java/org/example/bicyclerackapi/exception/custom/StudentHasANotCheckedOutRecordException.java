package org.example.bicyclerackapi.exception.custom;

/**
 * Clase de excepción personalizada que se lanza cuando un estudiante intenta guardar una bicicleta cuando ya tiene una guardada.
 */
public class StudentHasANotCheckedOutRecordException extends RuntimeException {
    public StudentHasANotCheckedOutRecordException(String message) {
        super(message);
    }
}
