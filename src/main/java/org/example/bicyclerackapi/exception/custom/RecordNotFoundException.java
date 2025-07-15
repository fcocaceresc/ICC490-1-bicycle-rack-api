package org.example.bicyclerackapi.exception.custom;

/**
 * Clase de excepción personalizada que se lanza cuando no se encuentra un registro con el ID proporcionado.
 */
public class RecordNotFoundException extends RuntimeException {
    public RecordNotFoundException(String message) {
        super(message);
    }
}
