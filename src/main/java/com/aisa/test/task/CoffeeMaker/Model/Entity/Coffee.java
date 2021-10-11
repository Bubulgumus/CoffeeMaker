package com.aisa.test.task.CoffeeMaker.Model.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Data
public class Coffee {

    @Id
    @Column(length = 16, unique = true, nullable = false)
    private UUID id = UUID.randomUUID();


    public Coffee(long sugar, @NonNull LocalDateTime startTimeOfCoffeePreparation, @NonNull LocalDateTime endTimeOfCoffeePreparation, @NonNull CoffeeType type) {
        this.sugar = sugar;
        this.startTimeOfCoffeePreparation = startTimeOfCoffeePreparation;
        this.endTimeOfCoffeePreparation = endTimeOfCoffeePreparation;
        this.type = type;
    }

    @Min(0)
    private long sugar;

    @NonNull
    private LocalDateTime startTimeOfCoffeePreparation;

    @NonNull
    private LocalDateTime endTimeOfCoffeePreparation;

    @ManyToOne
    @JoinColumn(columnDefinition = "coffee type_id")
    private CoffeeType type = new CoffeeType();


}
