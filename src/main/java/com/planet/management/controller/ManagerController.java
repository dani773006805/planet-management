package com.planet.management.controller;

import com.planet.management.dto.ManagerDto;
import com.planet.management.model.Manager;
import com.planet.management.services.ManagerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/managers")
public class ManagerController {
    private final ManagerService managerService;

    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody @Valid Manager manager) {
        var saved = managerService.add(manager);
        var dto = ManagerDto.toDto(saved);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @GetMapping("/free")
    public ResponseEntity<?> findFreeManagers() {
        var freeManagersDto = managerService.findAllFreeManagers().stream()
                .map(ManagerDto::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(freeManagersDto);
    }

    @GetMapping("/young10")
    public ResponseEntity<?> findTop10Managers() {
        var freeManagersDto = managerService.findTop10YoungManagers().stream()
                .map(ManagerDto::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(freeManagersDto);
    }


}
