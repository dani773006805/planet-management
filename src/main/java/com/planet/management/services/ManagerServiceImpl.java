package com.planet.management.services;

import com.planet.management.model.Manager;
import com.planet.management.repositories.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerService {
    private ManagerService managerService;
    private ManagerRepository managerRepository;

    public ManagerServiceImpl(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    @Autowired
    public void setManagerService(ManagerService managerService) {
        this.managerService = managerService;
    }

    @Override
    public Manager add(Manager manager) {
        manager.setPlanetSum(0);
        return managerRepository.save(manager);
    }

    @Override
    public Manager findById(Long id) {
        return managerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Manager with id %d doesn't exist", id)));
    }

    @Override
    public List<Manager> findAllFreeManagers() {
        return managerRepository
                .findAllByPlanetSum(0, PageRequest.of(0, 1000000)).getContent();
    }

    @Override
    public List<Manager> findTop10YoungManagers() {
        var sort=Sort.by("age");
        Pageable pageable = PageRequest.of(0, 10, sort);
        var list = managerRepository.findAll(pageable);
        return list.getContent();
    }

    @Override
    public Manager update(Manager manager) {
        return managerRepository.save(manager);
    }
}
