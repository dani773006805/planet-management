package com.planet.management.services;

import com.planet.management.model.Planet;
import com.planet.management.repositories.PlanetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlanetServiceImpl implements PlanetService {
    private PlanetRepository planetRepository;
    private ManagerService managerService;

    public PlanetServiceImpl(PlanetRepository planetRepository) {
        this.planetRepository = planetRepository;
    }
@Autowired
    public void setManagerService(ManagerService managerService) {
        this.managerService = managerService;
    }

    @Override
    public Planet add(Planet planet) {
        return planetRepository.save(planet);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        var planet = findById(id);
        if (null != planet.getManager()) {
            var manager = managerService.findById(planet.getManager().getId());
            manager.setPlanetSum(manager.getPlanetSum() - 1);
            managerService.update(manager);
        }
        planetRepository.deleteById(id);

    }

    @Override
    public Planet setManager(Long managerId, Long planetId) {
        var manager = managerService.findById(managerId);
        manager.setPlanetSum(manager.getPlanetSum() + 1);
        var updated = managerService.update(manager);
        var planet = findById(planetId);
        if(planet.getManager()!=null){
            var prev=planet.getManager();
            prev.setPlanetSum(prev.getPlanetSum()-1);
            managerService.update(prev);
        }
        planet.setManager(updated);
        return planetRepository.save(planet);
    }

    @Override
    public Planet findById(Long id) {
        return planetRepository.findById(id).orElseThrow(() ->
                new RuntimeException(String.format("Planet with id %d doesn't exist", id)));
    }
}
