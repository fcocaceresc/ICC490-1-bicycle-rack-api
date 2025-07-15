package org.example.bicyclerackapi.exception.response;

import java.time.Instant;

/**
 * Clase que representa un respuesta de error estandarizada para la API.
 */
public class ErrorResponse {
    /**
     * La fecha y hora en la que ocurrió el error.
     */
    private Instant timestamp;
    /**
     * El código de estado HTTP del error.
     */
    private int status;
    /**
     * La descripción del error.
     */
    private String error;

    /**
     * Constructor para crear una respuesta de error. El timestamp se establece automáticamente como la fecha y hora actual cuando se crea el objeto.
     *
     * @param status el código de estado HTTP del error
     * @param error  la descripción del error
     */
    public ErrorResponse(int status, String error) {
        this.timestamp = Instant.now();
        this.status = status;
        this.error = error;
    }

    /**
     * Obtiene la fecha y hora del error.
     *
     * @return la fecha y hora del error
     */
    public Instant getTimestamp() {
        return timestamp;
    }

    /**
     * Establece la fecha y hora del error.
     *
     * @param timestamp la fecha y hora del error
     */
    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Obtiene el código de estado HTTP del error.
     *
     * @return el código de estado HTTP del error
     */
    public int getStatus() {
        return status;
    }

    /**
     * Establece el código de estado HTTP del error.
     *
     * @param status el código de estado HTTP del error
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Obtiene la descripción del error.
     *
     * @return la descripción del error
     */
    public String getError() {
        return error;
    }

    /**
     * Establece la descripción del error.
     *
     * @param error la descripción del error
     */
    public void setError(String error) {
        this.error = error;
    }
}
