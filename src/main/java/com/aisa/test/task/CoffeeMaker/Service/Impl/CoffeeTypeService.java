package com.aisa.test.task.CoffeeMaker.Service.Impl;

import com.aisa.test.task.CoffeeMaker.Model.Entity.CoffeeType;
import com.aisa.test.task.CoffeeMaker.Repository.CoffeeTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Scope(value = "prototype")
public class CoffeeTypeService {

    private CoffeeTypeRepository coffeeTypeRepository;

    @Autowired
    public void setCoffeeTypeRepository(CoffeeTypeRepository coffeeTypeRepository) {
        this.coffeeTypeRepository = coffeeTypeRepository;
    }


    @Transactional(readOnly = true)
    public List<String> findAllTypes(){

        return coffeeTypeRepository.findALLTypes();
    }


    @Transactional(readOnly = true)
    public Optional<CoffeeType> findByName(String name) {

        return coffeeTypeRepository.findByName(name);
    }
}
