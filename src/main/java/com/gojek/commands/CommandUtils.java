package com.gojek.commands;

import com.gojek.car.Colour;

import java.util.Arrays;
import java.util.Optional;

public class CommandUtils {

    public static boolean isValidSlotNumber(String s) {
        try {
            Integer i = Integer.parseInt(s);

            if (i <= 0) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static boolean isValidRegNo(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        String[] tokens = s.split("-");
        if (tokens.length != 4) {
            return false;
        }
        return true;
    }

    public static boolean isValidColour(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }

        Optional<Colour> optionalColour = Colour.getColourByName(s);
        return optionalColour.isPresent() ? true : false;
    }


    public static Optional<Command> getCommand(String s) {
        if (s == null) {
            return Optional.empty();
        }
        String[] tokens = s.split(" ");
        return Arrays.stream(Command.values()).filter(c -> c.getName().equals(tokens[0])).findFirst();
    }
}
