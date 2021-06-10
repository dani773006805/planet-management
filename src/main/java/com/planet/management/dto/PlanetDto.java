package com.planet.management.dto;

import com.planet.management.model.Planet;
import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanetDto {
    private Long id;
    private String name;
    private Long managerId;

    public static PlanetDto toDto(Planet planet) {
        if (null == planet.getManager()) {
            return PlanetDto.builder()
                    .id(planet.getId())
                    .name(planet.getName())
                    .build();
        } else {
            return PlanetDto.builder()
                    .id(planet.getId())
                    .name(planet.getName())
                    .managerId(planet.getManager().getId())
                    .build();
        }
    }
}
