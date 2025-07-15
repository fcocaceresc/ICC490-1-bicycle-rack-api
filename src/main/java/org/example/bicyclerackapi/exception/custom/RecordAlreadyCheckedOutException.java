package org.example.bicyclerackapi.exception.custom;

/**
 * Clase de excepción personalizada que se lanza cuando se intenta marcar una bicicleta como retirada cuando ya ha sido retirada.
 */
public class RecordAlreadyCheckedOutException extends RuntimeException {
    public RecordAlreadyCheckedOutException(String message) {
        super(message);
    }
}
