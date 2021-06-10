package com.planet.management.services;

import com.planet.management.model.Planet;

public interface PlanetService {
    Planet add(Planet planet);

    void deleteById(Long id);

    Planet setManager(Long managerId, Long planetId);

    Planet findById(Long id);


}
