package org.example.bicyclerackapi.status;

import java.time.Instant;

/**
 * Clase que representa una respuesta del estado del servidor.
 */
public class StatusResponse {
    /**
     * Fecha y hora de la respuesta.
     */
    private Instant timestamp;
    /**
     * Código de estado HTTP de la respuesta.
     */
    private int status;
    /**
     * Descrición del estado del servidor.
     */
    private String message;

    /**
     * Constructor para crear una respuesta de estado del servidor.
     *
     * @param timestamp fecha y hora de la respuesta
     * @param status código de estado HTTP de la respuesta
     * @param message descrición del estado del servidor
     */
    public StatusResponse(Instant timestamp, int status, String message) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
    }

    /**
     * Obtiene la fecha y hora de la respuesta.
     *
     * @return fecha y hora de la respuesta
     */
    public Instant getTimestamp() {
        return timestamp;
    }

    /**
     * Establece la fecha y hora de la respuesta.
     *
     * @param timestamp fecha y hora de la respuesta
     */
    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Obtiene el código de estado HTTP de la respuesta.
     *
     * @return código de estado HTTP de la respuesta
     */
    public int getStatus() {
        return status;
    }

    /**
     * Establece el código de estado HTTP de la respuesta.
     *
     * @param status código de estado HTTP de la respuesta
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Obtiene la descripción del estado del servidor.
     *
     * @return descripción del estado del servidor
     */
    public String getMessage() {
        return message;
    }

    /**
     * Establece la descripción del estado del servidor.
     *
     * @param message descripción del estado del servidor
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
