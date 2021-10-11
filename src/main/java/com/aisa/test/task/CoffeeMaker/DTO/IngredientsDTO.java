package com.aisa.test.task.CoffeeMaker.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.Min;


@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class IngredientsDTO {
    @Min(0)
    private int cups;
    @Min(0)
    private int sugar;
    @Min(0)
    private int milk;
    @Min(0)
    private int water;
    @Min(0)
    private int beans;
}
