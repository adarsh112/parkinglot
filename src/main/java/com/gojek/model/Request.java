package com.gojek.model;

import com.gojek.car.Colour;

public class Request {
    private String regNo;
    private Colour colour;
    private Integer slotNumber;

    public Request(String regNo, Colour colour, Integer slotNumber) {
        this.regNo = regNo;
        this.colour = colour;
        this.slotNumber = slotNumber;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public Colour getColour() {
        return colour;
    }

    public void setColour(Colour colour) {
        this.colour = colour;
    }

    public Integer getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(Integer slotNumber) {
        this.slotNumber = slotNumber;
    }

    public static class RequestBuilder{
        private String regNo;
        private Colour colour;
        private Integer slotNumber;

        public RequestBuilder setRegNo(String regNo) {
            this.regNo = regNo;
            return this;
        }

        public RequestBuilder setColour(Colour colour) {
            this.colour = colour;
            return this;
        }

        public RequestBuilder setSlotNumber(Integer slotNumber) {
            this.slotNumber = slotNumber;
            return this;
        }

        public Request build(){
            return new Request(this.regNo, this.colour, this.slotNumber);
        }
    }

    public static RequestBuilder builder(){
        return new RequestBuilder();
    }
}
