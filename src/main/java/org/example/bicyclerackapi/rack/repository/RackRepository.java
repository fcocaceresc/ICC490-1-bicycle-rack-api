package org.example.bicyclerackapi.rack.repository;

import org.example.bicyclerackapi.rack.model.Rack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RackRepository extends JpaRepository<Rack, Long> {
    Rack getRackById(Long id);
}
