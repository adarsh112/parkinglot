package com.gojek.commands;

import com.gojek.ParkingManager;
import com.gojek.car.Car;
import com.gojek.car.Colour;
import com.gojek.model.Request;
import com.gojek.model.Response;

import java.util.Map;
import java.util.stream.Collectors;

public enum Command implements CommandExecutor {

    CREATE_PARKING_LOT("create_parking_lot", (command) -> {

        String[] args = ((String) command).split(" ");
        if (args.length != 2) {
            System.out.println("please provide total number of slots");
            return;
        }

        if (!CommandUtils.isValidSlotNumber(args[1])) {
            System.out.println("please provide a valid total number of slots");
            return;
        }
        Response response = ParkingManager.init(Integer.parseInt(args[1]));

        System.out.println(response.getMessage());
    }),

    PARK_SLOT("park", (command) -> {

        String[] args = ((String) command).split(" ");
        if (args.length != 3) {
            System.out.println("please provide reg no and Car colour");
            return;
        }

        if (!CommandUtils.isValidRegNo(args[1])) {
            System.out.println("please provide a valid reg no");
            return;
        }

        if (!CommandUtils.isValidColour(args[2])) {
            System.out.println("please provide valid Car colour in [Red, White, Yellow, Black, Blue, Green, Silver, Orange, Grey]");
            return;
        }

        Response response = ParkingManager.parkCar(Request.builder()
                .setRegNo(args[1])
                .setColour(Colour.getColourByName(args[2]).get())
                .build());

        System.out.println(response.getMessage());
    }),

    LEAVE_SLOT("leave", (command) -> {

        String[] args = ((String) command).split(" ");
        if (args.length != 2) {
            System.out.println("please provide the slot");
            return;
        }

        if (!CommandUtils.isValidSlotNumber(args[1])) {
            System.out.println("please provide a valid slot number");
            return;
        }

        Response response = ParkingManager.leaveParking(Request.builder()
                .setSlotNumber(Integer.parseInt(args[1]))
                .build());

        System.out.println(response.getMessage());
    }),

    STATUS("status", (command) -> {

        Response response = ParkingManager.status(Request.builder()
                .build());

        Map<Integer, Car> statusMap = response.getStatusMap();
        System.out.println("Slot No.  Registration   No Colour");

        if (statusMap != null) {
            for (Integer slot : statusMap.keySet()) {
                Car car = statusMap.get(slot);
                System.out.print(slot);
                System.out.print("         ");
                System.out.print(car.getRegNo());
                System.out.print("  ");
                System.out.println(car.getColour().getName());
            }
        }
    }),

    REG_NO_FOR_CAR_COLOUR("registration_numbers_for_cars_with_colour", (command) -> {

        String[] args = ((String) command).split(" ");
        if (args.length != 2) {
            System.out.println("please provide the colour");
            return;
        }

        if (!CommandUtils.isValidColour(args[1])) {
            System.out.println("please provide valid Car colour in [Red, White, Yellow, Black, Blue, Green, Silver, Orange, Grey]");
            return;
        }

        Response response = ParkingManager.getRegNoForColour(Request.builder()
                .setColour(Colour.getColourByName(args[1]).get())
                .build());

        if (response.hasMessage()) {
            System.out.println(response.getMessage());
        } else {
            System.out.println(String.join(", ", response.getRegNumbers()));
        }

    }),

    SLOT_FOR_CAR_COLOUR("slot_numbers_for_cars_with_colour", (command) -> {

        String[] args = ((String) command).split(" ");
        if (args.length != 2) {
            System.out.println("please provide the colour");
            return;
        }

        if (!CommandUtils.isValidColour(args[1])) {
            System.out.println("please provide a valid Car colour in [Red, White, Yellow, Black, Blue, Green, Silver, Orange, Grey]");
            return;
        }

        Response response = ParkingManager.getSlotsForColour(Request.builder()
                .setColour(Colour.getColourByName(args[1]).get())
                .build());

        if (response.hasMessage()) {
            System.out.println(response.getMessage());
        } else {
            String slots = response.getSlotNumbers().stream()
                    .map(s -> String.valueOf(s)).collect(Collectors.joining(", "));
            System.out.println(slots);
        }
    }),

    SLOT_NO_FOR_REG_NO("slot_number_for_registration_number", (command) -> {

        String[] args = ((String) command).split(" ");
        if (args.length != 2) {
            System.out.println("please provide the registration number");
            return;
        }

        if (!CommandUtils.isValidRegNo(args[1])) {
            System.out.println("please provide a valid registration number");
            return;
        }
        Response response = ParkingManager.getSlotNoForRegNo(Request.builder()
                .setRegNo(args[1])
                .build());

        if (response.hasMessage()) {
            System.out.println(response.getMessage());
        } else {
            System.out.println(response.getSlotNumberForRegNo());
        }
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
