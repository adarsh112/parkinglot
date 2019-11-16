package com.gojek;

import com.gojek.commands.Command;
import com.gojek.commands.CommandUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MainClassTest {

    @Mock
    private MainClass mainClass;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void commandUtilsTest(){
        Optional<Command> commandOptional = CommandUtils.getCommand("create_parking_lot 6");
        Assert.assertEquals(Command.CREATE_PARKING_LOT, commandOptional.get());

        commandOptional = CommandUtils.getCommand("park KA-01-HH-1234 White");
        Assert.assertEquals(Command.PARK_SLOT, commandOptional.get());

        commandOptional = CommandUtils.getCommand("leave KA-01-HH-1234");
        Assert.assertEquals(Command.LEAVE_SLOT, commandOptional.get());

        commandOptional = CommandUtils.getCommand("status");
        Assert.assertEquals(Command.STATUS, commandOptional.get());

        commandOptional = CommandUtils.getCommand("registration_numbers_for_cars_with_colour White");
        Assert.assertEquals(Command.REG_NO_FOR_CAR_COLOUR, commandOptional.get());

        commandOptional = CommandUtils.getCommand("slot_numbers_for_cars_with_colour White");
        Assert.assertEquals(Command.SLOT_FOR_CAR_COLOUR, commandOptional.get());

        commandOptional = CommandUtils.getCommand("slot_number_for_registration_number KA-01-HH-1234");
        Assert.assertEquals(Command.SLOT_NO_FOR_REG_NO, commandOptional.get());

    }
}
