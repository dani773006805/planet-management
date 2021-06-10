package com.planet.management.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "managers")
@Builder
public class Manager extends BaseEntity {
    @NotBlank
    @Size(min = 3)
    private String name;

    @Positive
    private Integer age;

    private Integer planetSum;
    @JsonManagedReference
    @OneToMany(mappedBy = "manager", cascade = {CascadeType.PERSIST, CascadeType.REFRESH,
            CascadeType.DETACH, CascadeType.MERGE})
    private Set<Planet> planets = new HashSet<>();

}
