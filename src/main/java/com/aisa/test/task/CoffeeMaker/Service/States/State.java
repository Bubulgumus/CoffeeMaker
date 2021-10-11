package com.aisa.test.task.CoffeeMaker.Service.States;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public  class State {

    private boolean block = false;

    private  String state = "OFF";

}
