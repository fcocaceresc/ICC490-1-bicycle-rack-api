package org.example.bicyclerackapi.record.controller;

import jakarta.validation.Valid;
import org.example.bicyclerackapi.record.model.Record;
import org.example.bicyclerackapi.record.model.RecordPageResponse;
import org.example.bicyclerackapi.record.model.RecordRequest;
import org.example.bicyclerackapi.record.service.RecordService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/records")
public class RecordController {
    private final RecordService recordService;

    public RecordController(RecordService recordService) {
        this.recordService = recordService;
    }

    @PostMapping
    public Record createRecord(@Valid @RequestBody RecordRequest request) {
        return recordService.createRecord(request);
    }

    @GetMapping
    public RecordPageResponse getRecords(@RequestParam(required = false) String pageToken, @RequestParam(defaultValue = "10") int maxPageSize, @RequestParam(required = false) String filter) {
        return recordService.getRecords(pageToken, maxPageSize, filter);
    }

    @PatchMapping("/{id}")
    public Record updateRecord(@PathVariable Long id, @Valid @RequestBody RecordRequest request) {
        return recordService.updateRecord(id, request);
    }

    @PatchMapping("/{id}/checkout")
    public Record checkOutRecord(@PathVariable Long id) {
        return recordService.checkOutRecord(id);
    }
}
