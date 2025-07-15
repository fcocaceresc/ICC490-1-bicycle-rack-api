package org.example.bicyclerackapi.record.controller;

import jakarta.validation.Valid;
import org.example.bicyclerackapi.record.model.Record;
import org.example.bicyclerackapi.record.model.RecordPageResponse;
import org.example.bicyclerackapi.record.model.RecordRequest;
import org.example.bicyclerackapi.record.service.RecordService;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador rest que recibe las peticiones relacionadas con los registros de bicicletas, le entrega la petición al servicio, le solicita que realice las operaciones necesarias y luego devuelve una respuesta al cliente.
 */
@RestController
@RequestMapping("/records")
public class RecordController {
    private final RecordService recordService;

    public RecordController(RecordService recordService) {
        this.recordService = recordService;
    }

    /**
     * Endpoint para crear un nuevo registro.
     *
     * @param request contiene la información del registro a crear.
     * @return el registro creado.
     */
    @PostMapping
    public Record createRecord(@Valid @RequestBody RecordRequest request) {
        return recordService.createRecord(request);
    }

    /**
     * Endpoint para obtener una página de registros.
     *
     * @param pageToken   token que indica qué página se solicita (null para la primera página).
     * @param maxPageSize tamaño máximo de registros por página.
     * @param filter      filtro para aplicar a los registros.
     * @return una página de registros.
     */
    @GetMapping
    public RecordPageResponse getRecords(@RequestParam(required = false) String pageToken, @RequestParam(defaultValue = "10") int maxPageSize, @RequestParam(required = false) String filter) {
        return recordService.getRecords(pageToken, maxPageSize, filter);
    }

    /**
     * Endpoint para actualizar un registro existente.
     *
     * @param id      ID del registro a actualizar.
     * @param request contiene la información actualizada del registro.
     * @return el registro actualizado.
     */
    @PatchMapping("/{id}")
    public Record updateRecord(@PathVariable Long id, @Valid @RequestBody RecordRequest request) {
        return recordService.updateRecord(id, request);
    }

    /**
     * Endpoint para marcar un registro como retirado, estableciendo la fecha y hora de salida.
     *
     * @param id ID del registro a marcar como retirado.
     * @return el registro con la fecha y hora de salida actualizadas.
     */
    @PatchMapping("/{id}/checkout")
    public Record checkOutRecord(@PathVariable Long id) {
        return recordService.checkOutRecord(id);
    }
}
