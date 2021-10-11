package com.aisa.test.task.CoffeeMaker.Service.Impl;


import com.aisa.test.task.CoffeeMaker.DTO.IngredientsDTO;
import com.aisa.test.task.CoffeeMaker.Model.Entity.StockOfIngredients;
import com.aisa.test.task.CoffeeMaker.Repository.StocksOfIngredientsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@Scope(value = "prototype")
public class StockOfIngredientsService {

    private StocksOfIngredientsRepository stocksOfIngredientsRepository;


    @Autowired
    public void setStocksOfIngredientsRepository(StocksOfIngredientsRepository stocksOfIngredientsRepository) {
        this.stocksOfIngredientsRepository = stocksOfIngredientsRepository;
    }

    @Transactional
    public void save(StockOfIngredients stockOfIngredients) {

        stocksOfIngredientsRepository.save(stockOfIngredients);
    }

    @Transactional
    public boolean restocking(IngredientsDTO ingredientsDTO) {

        IngredientsDTO ingredients = getIngredients().orElse(
                new IngredientsDTO(
                        0, 0,
                        0, 0, 0
                )
        );

        stocksOfIngredientsRepository.save(
                new StockOfIngredients(
                        UUID.randomUUID(),
                        LocalDateTime.now(),
                        ingredients.getCups() + ingredientsDTO.getCups(),
                        ingredients.getMilk() + ingredientsDTO.getMilk(),
                        ingredients.getSugar() + ingredientsDTO.getBeans(),
                        ingredients.getWater() + ingredientsDTO.getWater(),
                        ingredients.getSugar() + ingredientsDTO.getSugar())
        );
        ingredients = null;
        log.info("Restocking has done");
        return true;
    }


    @Transactional(readOnly = true)
    public Optional<IngredientsDTO> getIngredients() {
        log.info("Reading from a database, method getIngredients");
        Optional<StockOfIngredients> opt =
                stocksOfIngredientsRepository.findLastBalance();

        if (opt.isPresent()) {
            StockOfIngredients ingredients = opt.get();
            opt = Optional.empty();
            return Optional.of(
                    new IngredientsDTO(
                            ingredients.getCups(),
                            ingredients.getSugar(),
                            ingredients.getMilk(),
                            ingredients.getWater(),
                            ingredients.getBeans()
                    ));
        }

        return Optional.empty();
    }

}
