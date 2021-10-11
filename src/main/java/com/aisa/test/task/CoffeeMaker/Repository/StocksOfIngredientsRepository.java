package com.aisa.test.task.CoffeeMaker.Repository;

import com.aisa.test.task.CoffeeMaker.Model.Entity.StockOfIngredients;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StocksOfIngredientsRepository extends PagingAndSortingRepository<StockOfIngredients, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM stock_of_ingredients as s ORDER BY s.time_of_last_change DESC LIMIT 1")
    Optional<StockOfIngredients> findLastBalance();


}
