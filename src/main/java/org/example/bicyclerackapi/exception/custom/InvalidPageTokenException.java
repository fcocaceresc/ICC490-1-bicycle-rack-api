package org.example.bicyclerackapi.exception.custom;

/**
 * Clase de excepción personalizada que se lanza cuando el token de página proporcionado es inválido.
 */
public class InvalidPageTokenException extends RuntimeException {
    public InvalidPageTokenException(String message) {
        super(message);
    }
}
