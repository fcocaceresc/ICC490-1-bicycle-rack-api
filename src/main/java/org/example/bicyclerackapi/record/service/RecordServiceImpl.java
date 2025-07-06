package org.example.bicyclerackapi.record.service;

import org.example.bicyclerackapi.exception.custom.InvalidPageTokenException;
import org.example.bicyclerackapi.exception.custom.RecordNotFoundException;
import org.example.bicyclerackapi.record.model.Record;
import org.example.bicyclerackapi.record.model.RecordPageResponse;
import org.example.bicyclerackapi.record.model.RecordRequest;
import org.example.bicyclerackapi.record.repository.RecordRepository;
import org.example.bicyclerackapi.record.utils.PageTokenUtils;
import org.example.bicyclerackapi.record.utils.SpecificationBuilder;
import org.example.bicyclerackapi.record.validator.RecordValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {
    private final RecordRepository recordRepository;
    private final RecordValidator recordValidator;
    private final SpecificationBuilder filterBuilder;
    @Value("${bicycleRack.capacity}")
    private long bicycleRackCapacity;

    public RecordServiceImpl(RecordRepository recordRepository, RecordValidator recordValidator, SpecificationBuilder filterBuilder) {
        this.recordRepository = recordRepository;
        this.recordValidator = recordValidator;
        this.filterBuilder = filterBuilder;
    }

    @Override
    public Record createRecord(RecordRequest request) {
        long currentActiveRecordsCount = recordRepository.countByCheckOutIsNull();
        recordValidator.validateBicycleRackCapacity(bicycleRackCapacity, currentActiveRecordsCount);

        boolean studentHasCheckedOutRecord = recordRepository.existsByStudentIdAndCheckOutIsNull(request.getStudentId());
        recordValidator.validateStudentHasANotCheckedOutRecord(studentHasCheckedOutRecord);

        Record newRecord = new Record(request.getStudentId(), request.getStudentName(), request.getBicycleDescription());

        return recordRepository.save(newRecord);
    }

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

    public void validatePageExists(int pageNumber, Slice<Record> recordSlice) {
        if (pageNumber > 0 && recordSlice.getContent().isEmpty()) {
            throw new InvalidPageTokenException("Invalid page token.");
        }
    }

    @Override
    public Record getRecordById(Long id) {
        return recordRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Record not found"));
    }

    @Override
    public Record updateRecord(Long id, RecordRequest request) {
        Record existingRecord = getRecordById(id);
        existingRecord.setStudentId(request.getStudentId());
        existingRecord.setStudentName(request.getStudentName());
        existingRecord.setBicycleDescription(request.getBicycleDescription());
        return recordRepository.save(existingRecord);
    }

    @Override
    public Record checkOutRecord(Long id) {
        Record existingRecord = getRecordById(id);

        recordValidator.validateRecordIsNotAlreadyCheckedOut(existingRecord);

        existingRecord.setCheckOut(Instant.now());
        return recordRepository.save(existingRecord);
    }
}
