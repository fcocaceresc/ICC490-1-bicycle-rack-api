package org.example.bicyclerackapi.exception.custom;

/**
 * Clase de excepción personalizada que se lanza cuando un gancho no existe.
 */
public class InvalidHookException extends RuntimeException {
    public InvalidHookException(String message) {
        super(message);
    }
}
