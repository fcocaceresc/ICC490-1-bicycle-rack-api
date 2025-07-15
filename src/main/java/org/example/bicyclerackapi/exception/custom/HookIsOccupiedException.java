package org.example.bicyclerackapi.exception.custom;

/**
 * Clase de excepción personalizada que se lanza cuando se intenta ocupar un gancho que ya está ocupado.
 */
public class HookIsOccupiedException extends RuntimeException {
    public HookIsOccupiedException(String message) {
        super(message);
    }
}
