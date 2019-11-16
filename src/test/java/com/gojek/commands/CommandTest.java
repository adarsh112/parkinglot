package com.gojek.commands;

import com.gojek.ParkingManager;
import com.gojek.model.Request;
import com.gojek.model.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ParkingManager.class)
public class CommandTest {

    Command command;
    Response response = Mockito.mock(Response.class);


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ParkingManager.init(5);
        Mockito.when(response.getMessage()).thenReturn("success");
        PowerMockito.mockStatic(ParkingManager.class);
    }

    @Test
    public void testIssueSlotCommand(){
        command = Command.PARK_SLOT;
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        PowerMockito.when(ParkingManager.parkCar(Mockito.any()))
                .thenReturn(response);

        command.execute("park KA-01-HH-1234 White");
        Assert.assertEquals("success\n", outContent.toString());
        outContent.reset();

        command.execute("park KA-01-HH-1234 Whitee");
        Assert.assertEquals("please provide valid Car colour in [Red, White, Yellow, Black, Blue, Green, Silver, Orange, Grey]\n", outContent.toString());
        outContent.reset();

        command.execute("park ac White");
        Assert.assertEquals("please provide a valid reg no\n", outContent.toString());
        outContent.reset();
    }

    @Test
    public void testLeaveSlotCommand(){
        command = Command.LEAVE_SLOT;
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        PowerMockito.when(ParkingManager.leaveParking(Mockito.any()))
                .thenReturn(response);

        command.execute("leave 4");
        Assert.assertEquals("success\n", outContent.toString());
        outContent.reset();

        command.execute("leave -4");
        Assert.assertEquals("please provide a valid slot number\n", outContent.toString());
        outContent.reset();

        command.execute("leave asd");
        Assert.assertEquals("please provide a valid slot number\n", outContent.toString());
        outContent.reset();

    }

    @Test
    public void testStatusCommand(){
        command = Command.STATUS;
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        PowerMockito.when(ParkingManager.status(Mockito.any()))
                .thenReturn(response);

        command.execute("status");
        Assert.assertEquals("Slot No.  Registration   No Colour\n", outContent.toString());
        outContent.reset();
    }

    @Test
    public void testRegNoForColourCommand(){
        command = Command.REG_NO_FOR_CAR_COLOUR;
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        PowerMockito.when(ParkingManager.getRegNoForColour(Mockito.any()))
                .thenReturn(Response.builder().setMessage("success").build());

        command.execute("registration_numbers_for_cars_with_colour White");
        Assert.assertEquals("success\n", outContent.toString());
        outContent.reset();

        command.execute("registration_numbers_for_cars_with_colour Whit");
        Assert.assertEquals("please provide valid Car colour in [Red, White, Yellow, Black, Blue, Green, Silver, Orange, Grey]\n", outContent.toString());
        outContent.reset();

        command.execute("registration_numbers_for_cars_with_colour");
        Assert.assertEquals("please provide the colour\n", outContent.toString());
        outContent.reset();
    }


    @Test
    public void testSlotForColourCommand(){
        command = Command.SLOT_FOR_CAR_COLOUR;
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        PowerMockito.when(ParkingManager.getSlotsForColour(Mockito.any()))
                .thenReturn(Response.builder().setMessage("success").build());

        command.execute("slot_numbers_for_cars_with_colour White");
        Assert.assertEquals("success\n", outContent.toString());
        outContent.reset();

        command.execute("slot_numbers_for_cars_with_colour Whit");
        Assert.assertEquals("please provide a valid Car colour in [Red, White, Yellow, Black, Blue, Green, Silver, Orange, Grey]\n", outContent.toString());
        outContent.reset();

        command.execute("slot_numbers_for_cars_with_colour");
        Assert.assertEquals("please provide the colour\n", outContent.toString());
        outContent.reset();
    }


    @Test
    public void testSlotForRegNoCommand(){
        command = Command.SLOT_NO_FOR_REG_NO;
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        PowerMockito.when(ParkingManager.getSlotNoForRegNo(Mockito.any()))
                .thenReturn(Response.builder().setMessage("success").build());

        command.execute("slot_number_for_registration_number KA-01-HH-3141");
        Assert.assertEquals("success\n", outContent.toString());
        outContent.reset();

        command.execute("slot_number_for_registration_number 123");
        Assert.assertEquals("please provide a valid registration number\n", outContent.toString());
        outContent.reset();

        command.execute("slot_number_for_registration_number");
        Assert.assertEquals("please provide the registration number\n", outContent.toString());
        outContent.reset();
    }
}
