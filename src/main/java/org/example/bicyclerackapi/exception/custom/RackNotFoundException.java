package org.example.bicyclerackapi.exception.custom;

/**
 * Clase de excepción personalizada que se lanza cuando no se encuentra un bicicletero.
 */
public class RackNotFoundException extends RuntimeException {
    public RackNotFoundException(String message) {
        super(message);
    }
}
