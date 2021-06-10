package com.planet.management.dto;

import com.planet.management.model.Manager;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ManagerDto {
    private Long id;
    private String name;
    private Integer age;
    private Integer planetSum;

    public static ManagerDto toDto(Manager manager) {
        return ManagerDto.builder()
                .id(manager.getId())
                .age(manager.getAge())
                .name(manager.getName())
                .planetSum(manager.getPlanetSum()).build();
    }
}
