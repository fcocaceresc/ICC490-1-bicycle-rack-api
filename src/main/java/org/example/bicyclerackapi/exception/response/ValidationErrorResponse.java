package org.example.bicyclerackapi.exception.response;

import java.time.Instant;
import java.util.List;

/**
 * Representa una respuesta de error para errores de anotaciones de jakarta beans validation. A diferencia de ErrorResponse, esta clase esta diseñada para poder devolver múltiples errores de validación en una lista.
 */
public class ValidationErrorResponse {
    /**
     * Fecha y hora en la que ocurrió el error.
     */
    private Instant timestamp;
    /**
     * Código de estado HTTP del error.
     */
    private int status;
    /**
     * Lista de mensajes de error de validación.
     */
    private List<String> error;

    /**
     * Constructor para crear una respuesta de error de validación. El timestamp se establece automáticamente como la fecha y hora actual cuando se crea el objeto.
     *
     * @param status código de estado HTTP del error
     * @param error  lista de mensajes de error de validación
     */
    public ValidationErrorResponse(int status, List<String> error) {
        this.timestamp = Instant.now();
        this.status = status;
        this.error = error;
    }

    /**
     * Obtiene la fecha y hora en la que ocurrió el error.
     *
     * @return fecha y hora del error
     */
    public Instant getTimestamp() {
        return timestamp;
    }

    /**
     * Establece la fecha y hora en la que ocurrió el error.
     *
     * @param timestamp fecha y hora del error
     */
    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Obtiene el código de estado HTTP del error.
     *
     * @return código de estado HTTP del error
     */
    public int getStatus() {
        return status;
    }

    /**
     * Establece el código de estado HTTP del error.
     *
     * @param status código de estado HTTP del error
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Obtiene la lista de mensajes de error de validación.
     *
     * @return lista de mensajes de error de validación
     */
    public List<String> getError() {
        return error;
    }

    /**
     * Establece la lista de mensajes de error de validación.
     *
     * @param error lista de mensajes de error de validación
     */
    public void setError(List<String> error) {
        this.error = error;
    }
}
