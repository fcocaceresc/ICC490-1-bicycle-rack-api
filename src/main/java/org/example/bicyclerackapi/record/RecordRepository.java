package org.example.bicyclerackapi.record;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<Record, Long> {
    boolean existsByStudentIdAndCheckOutIsNull(String studentId);

    long countByCheckOutIsNull();
}
