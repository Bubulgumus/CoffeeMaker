package com.aisa.test.task.CoffeeMaker.Repository;

import com.aisa.test.task.CoffeeMaker.Model.Entity.Coffee;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoffeeRepository extends PagingAndSortingRepository<Coffee, Long> {


}
