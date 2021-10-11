package com.aisa.test.task.CoffeeMaker.Controller;

import com.aisa.test.task.CoffeeMaker.DTO.Info;
import com.aisa.test.task.CoffeeMaker.DTO.IngredientsDTO;
import com.aisa.test.task.CoffeeMaker.Service.Impl.StockOfIngredientsService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/Stock")
@Slf4j
public class StockOfIngredientsController {

    private StockOfIngredientsService stockOfIngredientsService;

    @Autowired
    public void setStockOfIngredientsService(StockOfIngredientsService stockOfIngredientsService) {
        this.stockOfIngredientsService = stockOfIngredientsService;
    }

    @ApiOperation(value = "Get ingredients in stocks", response = IngredientsDTO.class)
    @GetMapping(path = "/ingredients", produces = "application/json")
    public IngredientsDTO getInfoAboutIngredients() {

        try {
            IngredientsDTO dto = stockOfIngredientsService.getIngredients().get();
            return dto;
        } catch (NullPointerException e) {
            log.error("NotFoundBalance", e);
        }
        return new IngredientsDTO();
    }

    @ApiOperation(value = "Restocking the coffee maker", response = IngredientsDTO.class)
    @PutMapping(path = "/ingredients", produces = "application/json")
    public Info restocking(@Valid @RequestBody IngredientsDTO dto) {
        if (stockOfIngredientsService.restocking(dto)) {
            return new Info("Restocking is done");
        }
        return new Info("Something went wrong");
    }

}
