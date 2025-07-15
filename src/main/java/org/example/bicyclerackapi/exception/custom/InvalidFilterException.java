package org.example.bicyclerackapi.exception.custom;

/**
 * Clase de excepción personalizada que se lanza cuando un filtro es inválido.
 */
public class InvalidFilterException extends RuntimeException {
  public InvalidFilterException(String message) {
    super(message);
  }
}
