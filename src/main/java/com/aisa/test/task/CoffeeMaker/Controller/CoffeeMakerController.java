package com.aisa.test.task.CoffeeMaker.Controller;

import com.aisa.test.task.CoffeeMaker.DTO.CoffeeDTO;
import com.aisa.test.task.CoffeeMaker.DTO.Info;
import com.aisa.test.task.CoffeeMaker.DTO.IngredientsDTO;
import com.aisa.test.task.CoffeeMaker.DTO.StatesDTO;
import com.aisa.test.task.CoffeeMaker.Service.Impl.CoffeeMakerImitator;
import com.aisa.test.task.CoffeeMaker.Service.Impl.StockOfIngredientsService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/coffee-machine")
@Slf4j
public class CoffeeMakerController {

    private CoffeeMakerImitator coffeeMakerService;


    @Autowired
    public void setCoffeeMakerService(CoffeeMakerImitator coffeeMakerService) {
        this.coffeeMakerService = coffeeMakerService;
    }


    @ApiOperation(value = "Get State of the coffee maker", response = StatesDTO.class)
    @GetMapping(path = "/state", produces = "application/json")
    public StatesDTO getInfoAboutState() {
        return coffeeMakerService.getState();
    }


    @ApiOperation(value = "Start making coffee", response = Info.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Coffee brewed successfully"),
            @ApiResponse(code = 400, message = "The command was not executed because the coffee maker cannot execute it."
                    + "The coffee maker must be switched on and not blocked")
    }
    )
    @PatchMapping(path = "/make", produces = "application/json")
    public Info makeCoffee(@Valid @RequestBody CoffeeDTO coffeeDTO) {
        if (coffeeMakerService.makeCoffee(coffeeDTO)) {
            return new Info("Coffee brewed successfully");
        }
        return new Info("The command was not executed because the coffee maker cannot execute it. "
                + "The coffee maker must be switched on and not blocked or the coffee maker don't have ingredients."
        );
    }


    @ApiOperation(value = "Turn on the coffee maker", response = Info.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The coffee maker turning on"),
            @ApiResponse(code = 400, message = "The coffee maker already is on")
    }
    )
    @PatchMapping(path = "/on", produces = "application/json")
    public Info turnOnCoffeeMaker() {
        if (coffeeMakerService.turnOn()) {
            return new Info("The coffee maker turning on");
        }
        return new Info("The coffee maker already is on");
    }


    @ApiOperation(value = "Turn off the coffee maker", response = Info.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The coffee maker turning off"),
            @ApiResponse(code = 400, message = "The coffee maker already is off")
    }
    )


    @PatchMapping(path = "/off", produces = "application/json")
    public Info turnOffCoffeeMaker() {
        if (coffeeMakerService.turnOff()) {
            return new Info("The coffee maker turning off");
        }
        return new Info("The coffee maker already is off");
    }


    @ApiOperation(value = "clean the coffee maker", response = Info.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The coffee maker cleaned successfully"),
            @ApiResponse(code = 400, message = "The coffee maker in processing, clean it later")
    }
    )
    @PatchMapping(path = "/clean", produces = "application/json")
    public Info cleanCoffeeMaker() {
        if (coffeeMakerService.clean()) {
            return new Info("The coffee maker cleaned successfully");
        }
        return new Info("The coffee maker in processing, clean it later");
    }


}
