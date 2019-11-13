package com.gojek.car;

public class Car {
    private String regNo;
    private Colour colour;

    public Car(String regNo, Colour colour) {
        this.regNo = regNo;
        this.colour = colour;
    }

    public String getRegNo() {
        return regNo;
    }

    public Colour getColour() {
        return colour;
    }
}
