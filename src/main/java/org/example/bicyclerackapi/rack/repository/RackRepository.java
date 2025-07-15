package org.example.bicyclerackapi.rack.repository;

import org.example.bicyclerackapi.rack.model.Rack;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz que define los métodos de acceso a los datos de la tabla Rack de la base de datos.
 */
public interface RackRepository extends JpaRepository<Rack, Long> {
    /**
     * Consulta personalizada que busca un bicicletero por su ID.
     *
     * @param id ID del bicicletero a obtener.
     * @return el bicicletero con el ID especificado, o null si no existe.
     */
    Rack getRackById(Long id);
}
