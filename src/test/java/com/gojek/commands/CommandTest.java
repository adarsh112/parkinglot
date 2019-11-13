package com.gojek.commands;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class CommandTest {

    Command command;

    @Test
    public void testCreateParkingCommand(){
        command = Command.CREATE_PARKING_LOT;
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        command.execute("create_parking_lot 6");
        Assert.assertEquals("success\n", outContent.toString());
        outContent.reset();

        command.execute("create_parking_lot xyz");
        Assert.assertEquals("please provide total number of slots\n", outContent.toString());
        outContent.reset();

        command.execute("create_parking_lot");
        Assert.assertEquals("please provide a valid total number of slots\n", outContent.toString());
        outContent.reset();
    }

    @Test
    public void testIssueSlotCommand(){
        command = Command.ISSUE_SLOT;
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

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
    public void testRelieveSlotCommand(){
        command = Command.ISSUE_SLOT;
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        command.execute("leave 4");
        Assert.assertEquals("success\n", outContent.toString());
        outContent.reset();
    }
}
