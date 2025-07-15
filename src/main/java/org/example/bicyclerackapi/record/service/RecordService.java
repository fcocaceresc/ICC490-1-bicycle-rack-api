package org.example.bicyclerackapi.record.service;

import org.example.bicyclerackapi.record.model.Record;
import org.example.bicyclerackapi.record.model.RecordPageResponse;
import org.example.bicyclerackapi.record.model.RecordRequest;

/**
 * Interfaz que define los métodos para manejar los registros de bicicletas.
 */
public interface RecordService {
    /**
     * Crea un nuevo registro.
     *
     * @param request contiene la información del registro a crear.
     * @return
     */
    Record createRecord(RecordRequest request);

    /**
     * Obtiene una página de registros.
     *
     * @param pageToken   token de la página (null para la primera página).
     * @param maxPageSize tamaño máximo de la página.
     * @param filter      filtro para aplicar a los registros.
     * @return una página de registros.
     */
    RecordPageResponse getRecords(String pageToken, int maxPageSize, String filter);

    /**
     * Obtiene un registro por su ID.
     *
     * @param id ID del registro a obtener.
     * @return el registro con el ID especificado.
     */
    Record getRecordById(Long id);

    /**
     * Actualiza un registro existente.
     *
     * @param id      ID del registro a actualizar.
     * @param request contiene la información actualizada del registro.
     * @return el registro actualizado.
     */
    Record updateRecord(Long id, RecordRequest request);

    /**
     * Marca un registro como retirado, estableciendo la fecha y hora de salida.
     *
     * @param id ID del registro a marcar como retirado.
     * @return el registro actualizado con la fecha y hora de salida.
     */
    Record checkOutRecord(Long id);
}
