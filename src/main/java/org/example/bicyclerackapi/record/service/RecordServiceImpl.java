package org.example.bicyclerackapi.record.service;

import org.example.bicyclerackapi.exception.custom.InvalidPageTokenException;
import org.example.bicyclerackapi.exception.custom.RecordNotFoundException;
import org.example.bicyclerackapi.rack.model.Rack;
import org.example.bicyclerackapi.rack.repository.RackRepository;
import org.example.bicyclerackapi.record.model.Record;
import org.example.bicyclerackapi.record.model.RecordPageResponse;
import org.example.bicyclerackapi.record.model.RecordRequest;
import org.example.bicyclerackapi.record.repository.RecordRepository;
import org.example.bicyclerackapi.record.utils.PageTokenUtils;
import org.example.bicyclerackapi.record.utils.RecordValidator;
import org.example.bicyclerackapi.record.utils.SpecificationBuilder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

/**
 * Implementación de RecordService que valida datos y aplica la lógica de negocio a las operaciones de los registros.
 */
@Service
public class RecordServiceImpl implements RecordService {
    private final RecordRepository recordRepository;
    private final RackRepository rackRepository;
    private final RecordValidator recordValidator;
    private final SpecificationBuilder filterBuilder;

    public RecordServiceImpl(RecordRepository recordRepository, RackRepository rackRepository, RecordValidator recordValidator, SpecificationBuilder filterBuilder) {
        this.recordRepository = recordRepository;
        this.rackRepository = rackRepository;
        this.recordValidator = recordValidator;
        this.filterBuilder = filterBuilder;
    }

    /**
     * Crea un nuevo registro. Comprueba que el estudiante no tenga ya una bicicleta estacionada, que el gancho en el que colgó su bicicleta existe, y que no este ocupado.
     *
     * @param request contiene la información del registro a crear
     * @return el registro creado
     */
    @Override
    public Record createRecord(RecordRequest request) {
        boolean studentHasCheckedOutRecord = recordRepository.existsByStudentIdAndCheckOutIsNull(request.getStudentId());
        recordValidator.validateStudentHasANotCheckedOutRecord(studentHasCheckedOutRecord);
        Rack rack = rackRepository.getRackById(request.getRackId());
        recordValidator.validateHookExists(rack, request.getHook());
        boolean hookIsOccupied = recordRepository.existsByRackIdAndHookAndCheckOutIsNull(request.getRackId(), request.getHook());
        recordValidator.validateHookIsNotOccupied(hookIsOccupied);
        Record newRecord = new Record(request.getStudentId(), request.getStudentName(), request.getBicycleDescription(), rack, request.getHook());
        return recordRepository.save(newRecord);
    }

    /**
     * Obtiene una página de registros. Utiliza un token de página para determinar la página a devolver y un tamaño máximo de página para limitar el número de registros devueltos. También permite aplicar un filtro a los registros.
     *
     * @param pageToken   token de la página (null para la primera página)
     * @param maxPageSize número máximo de registros por página
     * @param filter      filtro para aplicar a los registros
     * @return una página de registros y un token codificado en base64 que indica la siguiente página
     */
    @Override
    public RecordPageResponse getRecords(String pageToken, int maxPageSize, String filter) {
        int pageNumber = PageTokenUtils.getPageNumberFromToken(pageToken);
        Pageable pageable = PageRequest.of(pageNumber, maxPageSize, Sort.by("id").ascending());

        Specification<Record> specification = filterBuilder.buildSpecification(filter);

        Slice<Record> recordSlice = specification != null ? recordRepository.findAll(specification, pageable) : recordRepository.findAll(pageable);

        validatePageExists(pageNumber, recordSlice);
        List<Record> records = recordSlice.getContent();
        String nextPageToken = PageTokenUtils.generateNextPageToken(recordSlice);
        return new RecordPageResponse(records, nextPageToken);
    }

    /**
     * Valida si el número de página obtenido del token de página existe.
     *
     * @param pageNumber el número de la página a validar
     * @param recordSlice la lista de registros obtenida de la base de datos
     * @throws InvalidPageTokenException si la página no existe
     */
    public void validatePageExists(int pageNumber, Slice<Record> recordSlice) {
        if (pageNumber > 0 && recordSlice.getContent().isEmpty()) {
            throw new InvalidPageTokenException("Invalid page token.");
        }
    }

    /**
     * Obtiene un registro por su ID.
     *
     * @param id el ID del registro a obtener
     * @return el registro con el ID especificado
     * @throws RecordNotFoundException si no existe un registro con el ID especificado
     */
    @Override
    public Record getRecordById(Long id) {
        return recordRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Record not found"));
    }

    /**
     * Actualiza un registro existente.
     *
     * @param id      el ID del registro a actualizar
     * @param request contiene la información actualizada del registro
     * @return el registro actualizado
     * @throws RecordNotFoundException si no existe un registro con el ID especificado
     */
    @Override
    public Record updateRecord(Long id, RecordRequest request) {
        Record existingRecord = getRecordById(id);
        existingRecord.setStudentId(request.getStudentId());
        existingRecord.setStudentName(request.getStudentName());
        existingRecord.setBicycleDescription(request.getBicycleDescription());
        return recordRepository.save(existingRecord);
    }

    /**
     * Marca un registro como retirado, estableciendo su checkOut como la fecha y hora actual.
     *
     * @param id ID del registro a marcar como retirado.
     * @return el registro actualizado con la fecha y hora de salida.
     * @throws RecordNotFoundException si no existe un registro con el ID especificado.
     */
    @Override
    public Record checkOutRecord(Long id) {
        Record existingRecord = getRecordById(id);

        recordValidator.validateRecordIsNotAlreadyCheckedOut(existingRecord);

        existingRecord.setCheckOut(Instant.now());
        return recordRepository.save(existingRecord);
    }
}
