package com.planet.management.repositories;

import com.planet.management.model.Manager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
    Page<Manager> findAllByPlanetSum(Integer planetSum, Pageable pageable);
}
