package com.gojek.car;

import java.util.Optional;

public enum Colour {
    WHITE("White"),
    BLACK("Black"),
    RED("Red"),
    GREEN("Green"),
    BLUE("Blue"),
    ORANGE("Orange"),
    YELLOW("Yellow"),
    GREY("Grey"),
    SILVER("Silver");

    private String name;

    Colour(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Optional<Colour> getColourByName(String name){
        for(Colour colour : Colour.values()){
            if(colour.getName().equals(name)){
                return Optional.of(colour);
            }
        }
        return Optional.empty();
    }
}
