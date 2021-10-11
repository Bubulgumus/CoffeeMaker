package com.aisa.test.task.CoffeeMaker.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class CoffeeDTO {
    @Size(max = 254, min = 2)
    private String name;
    @Min(0)
    private int sugar;

}

