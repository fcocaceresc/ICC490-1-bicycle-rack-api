package org.example.bicyclerackapi.rack.service;

import org.example.bicyclerackapi.exception.custom.RackNotFoundException;
import org.example.bicyclerackapi.rack.model.Rack;
import org.example.bicyclerackapi.rack.repository.RackRepository;
import org.springframework.stereotype.Service;

@Service
public class RackServiceImpl implements RackService {
    private final RackRepository rackRepository;

    public RackServiceImpl(RackRepository rackRepository) {
        this.rackRepository = rackRepository;
    }

    @Override
    public Rack getRackById(Long id) {
        return rackRepository.findById(id).orElseThrow(() -> new RackNotFoundException("Rack not found"));
    }
}
