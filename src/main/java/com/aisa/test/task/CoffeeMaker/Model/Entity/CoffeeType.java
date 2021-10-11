package com.aisa.test.task.CoffeeMaker.Model.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
public class CoffeeType {
    @Id
    @Column(length = 16, unique = true, nullable = false)
    private UUID id = UUID.randomUUID();

    private String name;
    @Min(0)
    private int water;
    @Min(0)
    private int beans;
    @Min(0)
    private int milk;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "type"
    )
    private Set<Coffee> cupsOfCoffee = new HashSet<>();
}
