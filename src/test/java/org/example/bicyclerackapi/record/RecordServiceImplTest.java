package org.example.bicyclerackapi.record;

import org.example.bicyclerackapi.exception.custom.BicycleRackIsFullException;
import org.example.bicyclerackapi.exception.custom.RecordAlreadyCheckedOutException;
import org.example.bicyclerackapi.exception.custom.RecordNotFoundException;
import org.example.bicyclerackapi.exception.custom.StudentHasANotCheckedOutRecordException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RecordServiceImplTest {
    @Mock
    private RecordRepository recordRepository;
    @InjectMocks
    private RecordServiceImpl recordService;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(recordService, "bicycleRackCapacity", 4);
    }

    @Test
    void createRecordBicycleRackIsFull() {
        RecordRequest request = new RecordRequest("123456789-25", "amadeus", "oxford");
        when(recordRepository.countByCheckOutIsNull()).thenReturn(4L);
        Exception exception = assertThrows(BicycleRackIsFullException.class, () -> {
            recordService.createRecord(request);
        });
        assertEquals("The bicycle rack is full", exception.getMessage());
        verify(recordRepository, never()).save(any(Record.class));
    }

    @Test
    void createRecordStudentHasNotCheckedOutRecord() {
        RecordRequest request = new RecordRequest("123456789-25", "amadeus", "oxford");
        when(recordRepository.existsByStudentIdAndCheckOutIsNull(request.getStudentId())).thenReturn(true);
        Exception exception = assertThrows(StudentHasANotCheckedOutRecordException.class, () -> {
            recordService.createRecord(request);
        });
        assertEquals("The student has a not checked out record", exception.getMessage());
        verify(recordRepository, never()).save(any(Record.class));
    }

    @Test
    void updateRecordNotFound() {
        RecordRequest request = new RecordRequest("123456789-25", "amadeus", "oxford");
        when(recordRepository.findById(1L)).thenReturn(java.util.Optional.empty());
        Exception exception = assertThrows(RecordNotFoundException.class, () -> {
            recordService.updateRecord(1L, request);
        });
        assertEquals("Record not found", exception.getMessage());
        verify(recordRepository, never()).save(any(Record.class));
    }

    @Test
    void checkOutRecordAlreadyCheckedOut() {
        Record existingRecord = new Record("123456789-25", "amadeus", "oxford");
        existingRecord.setId(1L);
        existingRecord.setCheckOut(Instant.now());
        when(recordRepository.findById(1L)).thenReturn(Optional.of(existingRecord));
        Exception exception = assertThrows(RecordAlreadyCheckedOutException.class, () -> {
            recordService.checkOutRecord(1L);
        });
        assertEquals("The record is already checked out", exception.getMessage());
        verify(recordRepository, never()).save(any(Record.class));
    }
}
