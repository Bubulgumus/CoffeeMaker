package com.aisa.test.task.CoffeeMaker.Model.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockOfIngredients {

    @Id
    @Column(length = 16, unique = true, nullable = false)
    private UUID id = UUID.randomUUID();
    @NonNull
    LocalDateTime timeOfLastChange;

    private int cups;

    private int milk;
    private int beans;
    private int water;
    private int sugar;


}
