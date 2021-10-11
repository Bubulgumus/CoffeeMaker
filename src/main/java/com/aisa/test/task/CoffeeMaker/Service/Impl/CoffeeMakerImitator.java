package com.aisa.test.task.CoffeeMaker.Service.Impl;


import com.aisa.test.task.CoffeeMaker.DTO.CoffeeDTO;
import com.aisa.test.task.CoffeeMaker.DTO.IngredientsDTO;
import com.aisa.test.task.CoffeeMaker.DTO.StatesDTO;
import com.aisa.test.task.CoffeeMaker.Model.Entity.Coffee;
import com.aisa.test.task.CoffeeMaker.Model.Entity.CoffeeType;
import com.aisa.test.task.CoffeeMaker.Model.Entity.StockOfIngredients;
import com.aisa.test.task.CoffeeMaker.Repository.CoffeeRepository;
import com.aisa.test.task.CoffeeMaker.Service.Enum.CoffeeMakerStates;
import com.aisa.test.task.CoffeeMaker.Service.States.State;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;


@Service
@AllArgsConstructor
@Slf4j
public class CoffeeMakerImitator {

    private State state;

    private CoffeeRepository coffeeRepository;

    private StockOfIngredientsService stockOfIngredientsService;

    @Autowired
    public void setStockOfIngredientsService(StockOfIngredientsService stockOfIngredientsService) {
        this.stockOfIngredientsService = stockOfIngredientsService;
    }

    private CoffeeTypeService coffeeTypeService;

    @Autowired
    public CoffeeRepository getCoffeeRepository() {
        return coffeeRepository;
    }

    @Autowired
    public void setState(State state) {

        this.state = state;
    }

    @Autowired
    public void setCoffeeTypeService(CoffeeTypeService coffeeTypeService) {
        this.coffeeTypeService = coffeeTypeService;
    }


    @Autowired
    public void setCoffeeRepository(CoffeeRepository coffeeRepository) {

        this.coffeeRepository = coffeeRepository;
    }


    public boolean turnOn() {
        if (state.getState().equals(CoffeeMakerStates.OFF.toString())) {
            log.info("The coffee maker is turning on");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                log.error("An exception occurred when simulating the operation of a coffee maker, method turnOn", e);
            }
            state.setState(CoffeeMakerStates.ON.toString());
            log.info("The coffee maker is turned on");
            return true;
        }
        return false;
    }


    public boolean turnOff() {
        if (!state.getState().equals(CoffeeMakerStates.OFF.toString())) {
            log.info("The coffee maker is turning off");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                log.error("An exception occurred when simulating the operation of a coffee maker, method turnOff", e);
            }
            state.setState(CoffeeMakerStates.OFF.toString());
            log.info("The coffee maker  turned off");
            return true;
        }
        return false;
    }


    public boolean clean() {
        if (!state.getState().equals(CoffeeMakerStates.MAKING.toString())) {
            log.info("The coffee maker is cleaning");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                log.error("An exception occurred when simulating the operation of a coffee maker, method clean", e);
            }
            state.setBlock(false);
            log.info("The coffee maker  cleaned");
            return true;
        }
        return false;
    }


    public StatesDTO getState() {
        return new StatesDTO(state);
    }


    @Transactional
    public boolean makeCoffee(CoffeeDTO coffeeDTO) {
        if (state.getState().equals(CoffeeMakerStates.ON.toString()) && !state.isBlock()) {

            state.setState(CoffeeMakerStates.MAKING.toString());
            LocalDateTime start = LocalDateTime.now();
            Optional<CoffeeType> optType = coffeeTypeService.findByName(coffeeDTO.getName());
            if (!optType.isPresent()) {
                state.setState(CoffeeMakerStates.ON.toString());
                return false;
            }
            saveStocksOfIngredientsMinusOneCupOfCoffee(coffeeDTO.getSugar(), optType);

            makeImitation();
            LocalDateTime end = LocalDateTime.now();
            coffeeRepository.save(new Coffee(
                    coffeeDTO.getSugar(),
                    start,
                    end,
                    optType.get()
            ));
            log.info("Added new cup of coffee in DB");
            state.setState(CoffeeMakerStates.ON.toString());
            return true;
        }
        log.info("The command was not executed because the coffee maker cannot execute it. "
                + "The coffee maker must be switched on and not blocked"
        );
        return false;
    }

    @Transactional
    public boolean saveStocksOfIngredientsMinusOneCupOfCoffee(int sugar, Optional<CoffeeType> optType) {
        Optional<IngredientsDTO> optIngredients = stockOfIngredientsService.getIngredients();

        if (!optIngredients.isPresent() && !optType.isPresent()) {
            state.setState(CoffeeMakerStates.ON.toString());
            return false;
        }
        IngredientsDTO ingredients = optIngredients.get();
        CoffeeType type = optType.get();

        if (
                ingredients.getCups() < 1
                        && ingredients.getMilk() < type.getMilk()
                        && ingredients.getBeans() < type.getBeans()
                        && ingredients.getWater() < type.getWater()
                        && ingredients.getSugar() < sugar
        ) {

            log.warn("No have ingredients to make cup of " + type.getName());
            return false;
        }

        stockOfIngredientsService.save(new StockOfIngredients(
                UUID.randomUUID(),
                LocalDateTime.now(),
                ingredients.getCups() - 1,
                ingredients.getMilk() - type.getMilk(),
                ingredients.getBeans() - type.getBeans(),
                ingredients.getWater() - type.getWater(),
                ingredients.getSugar() - sugar

        ));
        log.info("InsertNewBalanceOfIngredientsInDB");
        return true;
    }

    private void makeImitation() {
        log.info("Start Making coffee");
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            log.error("An exception occurred when simulating the operation of a coffee maker, method makeImitation", e);
        }
        state.setBlock(true);
        log.info("The coffee maker ended make coffee");
    }

}
