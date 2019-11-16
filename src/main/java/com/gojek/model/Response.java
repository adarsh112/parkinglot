package com.gojek.model;

import com.gojek.car.Car;

import java.util.List;
import java.util.Map;

public class Response {
    private String message;
    private Map<Integer, Car> statusMap;
    private List<String> regNumbers;
    private List<Integer> slotNumbers;
    private Integer slotNumberForRegNo;

    public Response(String message, Map<Integer, Car> statusMap) {
        this.message = message;
        this.statusMap = statusMap;
    }

    public Boolean hasMessage() {
        return this.message != null && this.message.length() > 0;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<Integer, Car> getStatusMap() {
        return statusMap;
    }

    public void setStatusMap(Map<Integer, Car> statusMap) {
        this.statusMap = statusMap;
    }

    public List<String> getRegNumbers() {
        return regNumbers;
    }

    public void setRegNumbers(List<String> regNumbers) {
        this.regNumbers = regNumbers;
    }

    public List<Integer> getSlotNumbers() {
        return slotNumbers;
    }

    public void setSlotNumbers(List<Integer> slotNumbers) {
        this.slotNumbers = slotNumbers;
    }

    public Integer getSlotNumberForRegNo() {
        return slotNumberForRegNo;
    }

    public void setSlotNumberForRegNo(Integer slotNumberForRegNo) {
        this.slotNumberForRegNo = slotNumberForRegNo;
    }

    public static class ResponseBuilder {
        private String message;
        private Map<Integer, Car> statusMap;
        private List<String> regNumbers;
        private List<Integer> slotNumbers;
        private Integer slotNumberForRegNo;

        public ResponseBuilder setMessage(String message) {
            this.message = message;
            return this;
        }

        public ResponseBuilder setStatusMap(Map<Integer, Car> statusMap) {
            this.statusMap = statusMap;
            return this;
        }

        public ResponseBuilder setRegNumbers(List<String> regNumbers) {
            this.regNumbers = regNumbers;
            return this;
        }

        public ResponseBuilder setSlotNumbers(List<Integer> slotNumbers) {
            this.slotNumbers = slotNumbers;
            return this;
        }

        public ResponseBuilder setSlotNumberForRegNo(Integer slotNumberForRegNo) {
            this.slotNumberForRegNo = slotNumberForRegNo;
            return this;
        }

        public Response build() {
            Response response = new Response(this.message, this.statusMap);
            response.setSlotNumberForRegNo(this.slotNumberForRegNo);
            response.setRegNumbers(this.regNumbers);
            response.setSlotNumbers(this.slotNumbers);
            return response;
        }
    }

    public static ResponseBuilder builder() {
        return new ResponseBuilder();
    }
}
