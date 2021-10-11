package com.aisa.test.task.CoffeeMaker.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.Min;

@AllArgsConstructor
@Getter
@Setter
public class RecipeOfCoffee {
    private   String name;
    @Min(0)
    private int water;
    @Min(0)
    private int beans;
    @Min(0)
    private   int milk;

}
