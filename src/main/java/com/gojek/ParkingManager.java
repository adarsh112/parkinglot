package com.gojek;

import com.gojek.car.Car;
import com.gojek.car.Colour;
import com.gojek.model.Request;
import com.gojek.model.Response;

import java.util.*;
import java.util.stream.Collectors;

public final class ParkingManager {

    private Map<Integer, Car> allotedMap;
    private Queue<Integer> availableSlots;
    private int totalSlots;

    private static ParkingManager instance;

    private ParkingManager(Integer totalSlots) {
        this.allotedMap = new HashMap<>();
        this.availableSlots = new PriorityQueue<>(totalSlots);
        for(int i=1; i<=totalSlots; i++){
            availableSlots.add(i);
        }
        this.totalSlots = totalSlots;
    }

    public static ParkingManager init(Integer totalSlots) {
        if(instance == null){
            synchronized (ParkingManager.class){
                if(instance == null){
                    instance = new ParkingManager(totalSlots);
                }
            }
        }
        return instance;
    }

    public int getAvailableSlots(){
        return this.availableSlots.size();
    }
    public Response parkCar(Request request){
        if(availableSlots.isEmpty()){
            return Response.builder().setMessage("Sorry, parking lot is full").build();
        }
        Integer slot = availableSlots.poll();
        allotedMap.put(slot, new Car(request.getRegNo(), request.getColour()));
        return Response.builder().setMessage("Allocated slot number: "+ slot).build();
    }

    public Response status(Request request){
        return Response.builder().setStatusMap(allotedMap).build();
    }

    public Response leaveParking(Request request){
        Integer slot = request.getSlotNumber();
        if(!allotedMap.containsKey(slot)){
            return Response.builder().setMessage("The slot number is not allotted to any car").build();
        }
        allotedMap.remove(slot);
        return Response.builder().setMessage("Slot number "+ slot + " is free").build();
    }

    public Response getRegNoForColour(Request request){
        Colour colour = request.getColour();
        List<String> regNumbers = allotedMap.values().stream()
                .filter(c -> colour == c.getColour())
                .map(c -> c.getRegNo())
                .collect(Collectors.toList());

        if(regNumbers.isEmpty()){
            return Response.builder().setMessage("Not found").build();
        }
        return Response.builder().setRegNumbers(regNumbers).build();
    }

    public Response getSlotsForColour(Request request){
        Colour colour = request.getColour();
        List<Integer> slotNumbers = allotedMap.entrySet().stream()
                .filter(e -> colour == e.getValue().getColour())
                .map(e -> e.getKey())
                .collect(Collectors.toList());

        if(slotNumbers.isEmpty()){
            return Response.builder().setMessage("Not found").build();
        }
        return Response.builder().setSlotNumbers(slotNumbers).build();
    }

    public Response getSlotNoForRegNo(Request request){
        String regNo = request.getRegNo();
        Optional<Map.Entry<Integer, Car>> entryOptional = allotedMap.entrySet().stream()
                .filter(e -> regNo.equals(e.getValue().getRegNo())).findFirst();

        if(!entryOptional.isPresent()){
            return Response.builder().setMessage("Not found").build();
        }
        return Response.builder().setSlotNumberForRegNo(entryOptional.get().getKey()).build();
    }

}
