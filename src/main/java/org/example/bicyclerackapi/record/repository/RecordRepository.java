package org.example.bicyclerackapi.record.repository;

import org.example.bicyclerackapi.record.model.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RecordRepository extends JpaRepository<Record, Long>, JpaSpecificationExecutor<Record> {
    boolean existsByStudentIdAndCheckOutIsNull(String studentId);

    boolean existsByRackIdAndHookAndCheckOutIsNull(Long rackId, Long hook);
}
