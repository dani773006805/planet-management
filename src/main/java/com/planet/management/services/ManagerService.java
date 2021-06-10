package com.planet.management.services;

import com.planet.management.model.Manager;

import java.util.List;

public interface ManagerService {
    Manager add(Manager manager);

    Manager findById(Long id);

    List<Manager> findAllFreeManagers();

    List<Manager> findTop10YoungManagers();

    Manager update(Manager manager);
}
