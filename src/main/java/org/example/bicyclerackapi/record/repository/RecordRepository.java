package org.example.bicyclerackapi.record.repository;

import org.example.bicyclerackapi.record.model.Record;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<Record, Long> {
    boolean existsByStudentIdAndCheckOutIsNull(String studentId);

    long countByCheckOutIsNull();
}
