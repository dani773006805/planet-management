package com.planet.management.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "planets")
@Builder
public class Planet extends BaseEntity {
    @Positive
    private Long population;
    @NotBlank
    @Size(min = 3)
    private String name;

    @JsonBackReference
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE})
    @JoinColumn(name = "manager_id")
    private Manager manager;

}
