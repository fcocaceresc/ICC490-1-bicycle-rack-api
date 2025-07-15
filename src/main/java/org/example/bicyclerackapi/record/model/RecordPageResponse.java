package org.example.bicyclerackapi.record.model;

import java.util.List;

/**
 * Clase que representa una respuesta de una página de registros.
 */
public class RecordPageResponse {
    /**
     * La lista de registros de la página.
     */
    private List<Record> records;
    /**
     * Un token que indica la página siguiente.
     */
    private String nextPageToken;

    /**
     * Constructor para crear una página de registros.
     *
     * @param records       la lista de registros de la página.
     * @param nextPageToken el token que indica la siguiente página.
     */
    public RecordPageResponse(List<Record> records, String nextPageToken) {
        this.records = records;
        this.nextPageToken = nextPageToken;
    }

    /**
     * Obtiene la lista de registros de la página.
     *
     * @return la lista de registros de la página.
     */
    public List<Record> getRecords() {
        return records;
    }

    /**
     * Establece la lista de registros de la página.
     *
     * @param records la lista de registros de la página.
     */
    public void setRecords(List<Record> records) {
        this.records = records;
    }

    /**
     * Obtiene el token que indica la página siguiente.
     *
     * @return el token que indica la página siguiente.
     */
    public String getNextPageToken() {
        return nextPageToken;
    }

    /**
     * Establece el token que indica la página siguiente.
     *
     * @param nextPageToken el token que indica la página siguiente.
     */
    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }
}
