package org.example.bicyclerackapi.rack.service;

import org.example.bicyclerackapi.rack.model.Rack;

/**
 * Interfaz que define los métodos para manejar los bicicleteros.
 */
public interface RackService {
    /**
     * Obtiene un bicicletero por su ID.
     *
     * @param id ID del bicicletero a obtener.
     * @return el bicicletero con el ID especificado.
     */
    Rack getRackById(Long id);
}
