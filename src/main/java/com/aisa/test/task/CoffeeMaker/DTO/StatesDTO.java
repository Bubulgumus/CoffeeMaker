package com.aisa.test.task.CoffeeMaker.DTO;

import com.aisa.test.task.CoffeeMaker.Service.States.State;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StatesDTO {

    private String state;
    private String block;


    public StatesDTO(State state) {

        switch (state.getState()) {
            case "OFF":
                this.state = "The coffee maker is off";
                if (state.isBlock()) {
                    this.block = "The coffee maker is blocked, clean it.";
                } else {
                    this.block = "Turn on the coffee maker to make coffee";
                }
                break;
            case "ON":
                this.state = "The coffee maker is on";
                if (state.isBlock()) {
                    this.block = "The coffee maker is blocked, clean it.";
                } else {
                    this.block = "The coffee maker ready to make coffee";
                }
                break;
            case "MAKING":
                this.state = "The coffee maker makes coffee";
                this.block = "the coffee maker is blocked for obvious reasons";
                break;
        }

    }
}

