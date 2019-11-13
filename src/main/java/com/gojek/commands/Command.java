package com.gojek.commands;

import java.util.Arrays;
import java.util.Optional;

public enum Command implements CommandExecutor{

    CREATE_PARKING_LOT("create_parking_lot", (command) -> {

            String[] tokens = ((String) command).split(" ");
            if(tokens.length != 2){
                System.out.println("please provide total number of slots");
                return;
            }

            if(!CommandUtils.isValidInteger(tokens[1])){
                System.out.println("please provide a valid total number of slots");
                return;
            }
            System.out.println("success");
            return;
    }),

    ISSUE_SLOT("park reg_no colour", (command) -> {

        String[] tokens = ((String) command).split(" ");
        if(tokens.length != 3){
            System.out.println("please provide reg no and Car colour");
            return;
        }

        if(!CommandUtils.isValidRegNo(tokens[1])){
            System.out.println("please provide a valid reg no");
            return;
        }

        if(!CommandUtils.isValidColour(tokens[2])){
            System.out.println("please provide valid Car colour in [Red, White, Yellow, Black, Blue, Green, Silver, Orange, Grey]");
            return;
        }

        System.out.println("success");
        return;
    }),

    RELEIVE_SLOT("leave", (command) -> {

        String[] tokens = ((String) command).split(" ");
        if(tokens.length != 2){
            System.out.println("please provide the slot");
            return;
        }

        if(!CommandUtils.isValidInteger(tokens[1])){
            System.out.println("please provide a valid slot number");
            return;
        }
        System.out.println("success");
        return;
    }),

    STATUS("status", (command) -> {

        System.out.println("success");
        return;
    }),

    REG_NO_FOR_CAR_COLOUR("registration_numbers_for_cars_with_colour", (command) -> {

        String[] tokens = ((String) command).split(" ");
        if(tokens.length != 2){
            System.out.println("please provide the colour");
            return;
        }

        if(!CommandUtils.isValidColour(tokens[1])){
            System.out.println("please provide valid Car colour in [Red, White, Yellow, Black, Blue, Green, Silver, Orange, Grey]");
            return;
        }
        System.out.println("success");
        return;
    }),

    SLOT_FOR_CAR_COLOUR("slot_numbers_for_cars_with_colour", (command) -> {

        String[] tokens = ((String) command).split(" ");
        if(tokens.length != 2){
            System.out.println("please provide the colour");
            return;
        }

        if(!CommandUtils.isValidColour(tokens[1])){
            System.out.println("please provide a valid Car colour in [Red, White, Yellow, Black, Blue, Green, Silver, Orange, Grey]");
            return;
        }

        System.out.println("success");
        return;
    }),

    SLOT_NO_FOR_REG_NO("slot_number_for_registration_number", (command) -> {

        String[] tokens = ((String) command).split(" ");
        if(tokens.length != 2){
            System.out.println("please provide slot number");
            return;
        }

        if(!CommandUtils.isValidInteger(tokens[1])){
            System.out.println("please provide a valid slot number");
            return;
        }
        System.out.println("success");
        return;
    });

    private String name;
    private CommandExecutor commandExecutor;

    public String getName() {
        return name;
    }

    Command(String name, final CommandExecutor commandExecutor) {
        this.name = name;
        this.commandExecutor = commandExecutor;
    }

    public void execute(String input) {
        commandExecutor.execute(input);
    }

}
