package com.aisa.test.task.CoffeeMaker.Repository;

import com.aisa.test.task.CoffeeMaker.Model.Entity.CoffeeType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CoffeeTypeRepository extends PagingAndSortingRepository<CoffeeType, Long> {

    Optional<CoffeeType> findByName(String name);

    @Query(value = "SELECT c.name FROM CoffeeType c")
    List<String> findALLTypes();

}
