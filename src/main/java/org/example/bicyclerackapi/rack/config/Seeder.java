package org.example.bicyclerackapi.rack.config;

import jakarta.annotation.PostConstruct;
import org.example.bicyclerackapi.rack.model.Rack;
import org.example.bicyclerackapi.rack.repository.RackRepository;
import org.springframework.stereotype.Component;

/**
 * Clase que se encarga de crear un bicicletero por defecto
 */
@Component
public class Seeder {
    /**
     * Repositorio de bicicleteros que se utiliza para acceder y realizar operaciones sobre la tabla Rack de la base de datos.
     */
    private final RackRepository rackRepository;

    public Seeder(RackRepository rackRepository) {
        this.rackRepository = rackRepository;
    }

    /**
     * Crea un bicicletero por defecto si no existe ninguno.
     */
    @PostConstruct
    public void seedRack() {
        if (rackRepository.count() == 0) {
            Rack defaultRack = new Rack();
            defaultRack.setRows(2L);
            defaultRack.setColumns(2L);
            defaultRack.setTotalHooks(4L);
            rackRepository.save(defaultRack);
        }
    }
}
