package org.example.bicyclerackapi.rack.config;

import jakarta.annotation.PostConstruct;
import org.example.bicyclerackapi.rack.model.Rack;
import org.example.bicyclerackapi.rack.repository.RackRepository;
import org.springframework.stereotype.Component;

@Component
public class Seeder {
    private final RackRepository rackRepository;

    public Seeder(RackRepository rackRepository) {
        this.rackRepository = rackRepository;
    }

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
