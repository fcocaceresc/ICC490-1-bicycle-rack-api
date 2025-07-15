package org.example.bicyclerackapi.rack.service;

import org.example.bicyclerackapi.exception.custom.RackNotFoundException;
import org.example.bicyclerackapi.rack.model.Rack;
import org.example.bicyclerackapi.rack.repository.RackRepository;
import org.springframework.stereotype.Service;

/**
 * Implementación de RackService que valida y maneja las operaciones relacionadas con los bicicleteros.
 */
@Service
public class RackServiceImpl implements RackService {
    private final RackRepository rackRepository;

    public RackServiceImpl(RackRepository rackRepository) {
        this.rackRepository = rackRepository;
    }

    /**
     * Obtiene un bicicletero por su ID.
     *
     * @param id ID del bicicletero a obtener.
     * @return el bicicletero con el ID especificado.
     * @throws RackNotFoundException si no se encuentra un bicicletero con el ID especificado.
     */
    @Override
    public Rack getRackById(Long id) {
        return rackRepository.findById(id).orElseThrow(() -> new RackNotFoundException("Rack not found"));
    }
}
