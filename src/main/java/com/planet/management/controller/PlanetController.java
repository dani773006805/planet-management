package com.planet.management.controller;

import com.planet.management.dto.ManagerDto;
import com.planet.management.dto.PlanetDto;
import com.planet.management.model.Manager;
import com.planet.management.model.Planet;
import com.planet.management.services.PlanetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/planets")
public class PlanetController {
    private PlanetService planetService;

    public PlanetController(PlanetService planetService) {
        this.planetService = planetService;
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody @Valid Planet planet) {
        var saved = planetService.add(planet);
        var dto = PlanetDto.toDto(planet);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        planetService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{planetId}/managers/{managerId}")
    public ResponseEntity<?> setManager(@PathVariable Long planetId, @PathVariable Long managerId) {
        var updated = planetService.setManager(managerId, planetId);
        var dto = PlanetDto.toDto(updated);
        return ResponseEntity.ok(dto);
    }
}
