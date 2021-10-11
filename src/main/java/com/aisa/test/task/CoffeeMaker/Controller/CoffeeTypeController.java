package com.aisa.test.task.CoffeeMaker.Controller;

import com.aisa.test.task.CoffeeMaker.DTO.CoffeeDTO;
import com.aisa.test.task.CoffeeMaker.DTO.RecipeOfCoffee;
import com.aisa.test.task.CoffeeMaker.DTO.TypeDTO;
import com.aisa.test.task.CoffeeMaker.Model.Entity.CoffeeType;
import com.aisa.test.task.CoffeeMaker.Service.Impl.CoffeeTypeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/types")
public class CoffeeTypeController {

    CoffeeTypeService coffeeTypeService;

    @Autowired
    public void setCoffeeTypeService(CoffeeTypeService coffeeTypeService) {
        this.coffeeTypeService = coffeeTypeService;
    }

    @ApiOperation(value = "Get types of presented coffee ", response = TypeDTO.class)
    @GetMapping(path = "/all", produces = "application/json")
    public TypeDTO getInfoAboutIngredients() {
        return new TypeDTO(coffeeTypeService.findAllTypes());
    }

    @ApiOperation(value = "Get recipe of coffee by name", response = TypeDTO.class)
    @PatchMapping(path = "/recipe", produces = "application/json")
    public RecipeOfCoffee getRecipe(@Valid @RequestBody CoffeeDTO coffeeDTO) {

        CoffeeType coffeeType = coffeeTypeService.findByName(coffeeDTO.getName()).get();
        return new RecipeOfCoffee(
                coffeeType.getName(),
                coffeeType.getWater(),
                coffeeType.getBeans(),
                coffeeType.getMilk()
        );
    }

}
