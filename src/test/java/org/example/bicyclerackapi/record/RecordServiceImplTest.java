package org.example.bicyclerackapi.record;

import org.example.bicyclerackapi.exception.custom.RecordAlreadyCheckedOutException;
import org.example.bicyclerackapi.exception.custom.RecordNotFoundException;
import org.example.bicyclerackapi.rack.model.Rack;
import org.example.bicyclerackapi.rack.repository.RackRepository;
import org.example.bicyclerackapi.record.model.Record;
import org.example.bicyclerackapi.record.model.RecordRequest;
import org.example.bicyclerackapi.record.repository.RecordRepository;
import org.example.bicyclerackapi.record.service.RecordServiceImpl;
import org.example.bicyclerackapi.record.utils.RecordValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RecordServiceImplTest {
    @Mock
    private RackRepository rackRepository;
    @Mock
    private RecordRepository recordRepository;
    @Mock
    private RecordValidator recordValidator;
    @InjectMocks
    private RecordServiceImpl recordService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void createRecord() {
        Rack rack = new Rack(1L, 2L, 2L, 4L);
        RecordRequest request = new RecordRequest("123456789-25", "amadeus", "oxford", 1L, 1L);
        Record expectedRecord = new Record(request.getStudentId(), request.getStudentName(), request.getBicycleDescription(), rack, request.getHook());
        when(recordRepository.existsByStudentIdAndCheckOutIsNull(request.getStudentId())).thenReturn(false);
        when(rackRepository.getRackById(request.getRackId())).thenReturn(rack);
        when(recordRepository.existsByRackIdAndHookAndCheckOutIsNull(request.getRackId(), request.getHook())).thenReturn(false);
        when(recordRepository.save(any(Record.class))).thenReturn(expectedRecord);
        Record result = recordService.createRecord(request);
        assertEquals(expectedRecord, result);
        verify(recordRepository).save(any(Record.class));
    }

    @Test
    void updateRecord() {
        RecordRequest request = new RecordRequest("123456789-25", "amadeus", "oxford", 1L, 1L);
        Record existingRecord = new Record();
        existingRecord.setId(1L);
        when(recordRepository.findById(1L)).thenReturn(Optional.of(existingRecord));
        when(recordRepository.save(existingRecord)).thenReturn(existingRecord);
        Record result = recordService.updateRecord(1L, request);
        assertEquals(request.getStudentId(), result.getStudentId());
        assertEquals(request.getStudentName(), result.getStudentName());
        assertEquals(request.getBicycleDescription(), result.getBicycleDescription());
        verify(recordRepository).save(existingRecord);
    }

    @Test
    void updateRecord_RecordNotFound() {
        RecordRequest request = new RecordRequest("123456789-25", "amadeus", "oxford", 1L, 1L);
        when(recordRepository.findById(1L)).thenReturn(Optional.empty());
        Exception exception = assertThrows(RecordNotFoundException.class, () -> {
            recordService.updateRecord(1L, request);
        });
        assertEquals("Record not found", exception.getMessage());
    }

    @Test
    void checkOutRecord() {
        Record existingRecord = new Record();
        existingRecord.setId(1L);
        existingRecord.setCheckOut(null);
        when(recordRepository.findById(1L)).thenReturn(Optional.of(existingRecord));
        when(recordRepository.save(existingRecord)).thenReturn(existingRecord);
        Record result = recordService.checkOutRecord(1L);
        assertNotNull(result.getCheckOut());
        verify(recordRepository).save(existingRecord);
    }

    @Test
    void checkOutRecord_RecordNotFound() {
        when(recordRepository.findById(1L)).thenReturn(Optional.empty());
        Exception exception = assertThrows(RecordNotFoundException.class, () -> {
            recordService.checkOutRecord(1L);
        });
        assertEquals("Record not found", exception.getMessage());
    }

    @Test
    void checkOutRecord_RecordAlreadyCheckedOut() {
        Record existingRecord = new Record();
        existingRecord.setId(1L);
        existingRecord.setCheckOut(Instant.now());
        when(recordRepository.findById(1L)).thenReturn(Optional.of(existingRecord));
        doThrow(new RecordAlreadyCheckedOutException("The record is already checked out")).when(recordValidator).validateRecordIsNotAlreadyCheckedOut(existingRecord);
        Exception exception = assertThrows(RecordAlreadyCheckedOutException.class, () -> {
            recordService.checkOutRecord(1L);
        });
        assertEquals("The record is already checked out", exception.getMessage());
    }
}
