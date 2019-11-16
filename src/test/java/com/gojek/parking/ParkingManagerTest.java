package com.gojek.parking;

import com.gojek.MainClass;
import com.gojek.ParkingManager;
import com.gojek.car.Colour;
import com.gojek.model.Request;
import com.gojek.model.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ParkingManagerTest {

    @Mock
    private MainClass mainClass;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void initTest(){
        Response response = ParkingManager.init(5);
        Assert.assertEquals("Created a parking lot with 5 slots", response.getMessage());
        Assert.assertEquals(5, ParkingManager.getAvailableSlots());
    }

    @Test
    public void parkTest(){
        ParkingManager.init(2);

        Request r = Request.builder().setColour(Colour.BLACK).setRegNo("Ka-03-mh-9999").build();
        Response response = ParkingManager.parkCar(r);
        Assert.assertEquals("Allocated slot number: 1", response.getMessage());

        response = ParkingManager.parkCar(r);
        Assert.assertEquals("Allocated slot number: 2", response.getMessage());

        response = ParkingManager.parkCar(r);
        Assert.assertEquals("Sorry, parking lot is full", response.getMessage());

    }

    @Test
    public void leaveTest(){
        ParkingManager.init(2);

        Request r = Request.builder().setColour(Colour.BLACK).setRegNo("Ka-03-mh-9999").build();
        Response response = ParkingManager.parkCar(r);

        r = Request.builder().setSlotNumber(1).build();
        response = ParkingManager.leaveParking(r);
        Assert.assertEquals("Slot number 1 is free", response.getMessage());

        r = Request.builder().setSlotNumber(3).build();
        response = ParkingManager.leaveParking(r);
        Assert.assertEquals("The slot number is not allotted to any car", response.getMessage());

    }

    @Test
    public void regNoForColourTest(){
        ParkingManager.init(2);

        Request r = Request.builder().setColour(Colour.BLACK).setRegNo("Ka-03-mh-9999").build();
        Response response = ParkingManager.parkCar(r);

        r = Request.builder().setColour(Colour.BLACK).build();
        response = ParkingManager.getRegNoForColour(r);
        Assert.assertEquals("Ka-03-mh-9999", response.getRegNumbers().get(0));

        r = Request.builder().setColour(Colour.BLACK).setRegNo("Ka-04-mh-9999").build();
        response = ParkingManager.parkCar(r);

        r = Request.builder().setColour(Colour.BLACK).build();
        response = ParkingManager.getRegNoForColour(r);
        String [] expectArray = {"Ka-03-mh-9999", "Ka-04-mh-9999"};
        Assert.assertArrayEquals(expectArray, response.getRegNumbers().toArray(new String[2]));

        r = Request.builder().setColour(Colour.WHITE).build();
        response = ParkingManager.getRegNoForColour(r);
        Assert.assertEquals("Not found", response.getMessage());

    }

    @Test
    public void slotsForColourTest(){
        ParkingManager.init(2);

        Request r = Request.builder().setColour(Colour.BLACK).setRegNo("Ka-03-mh-9999").build();
        ParkingManager.parkCar(r);

        Response response;

        r = Request.builder().setColour(Colour.BLACK).build();
        response = ParkingManager.getSlotsForColour(r);
        Assert.assertEquals(new Integer(1), response.getSlotNumbers().get(0));

        r = Request.builder().setColour(Colour.BLACK).setRegNo("Ka-04-mh-9999").build();
        response = ParkingManager.parkCar(r);

        r = Request.builder().setColour(Colour.BLACK).build();
        response = ParkingManager.getSlotsForColour(r);
        Integer [] expectArray = {1, 2};
        Assert.assertArrayEquals(expectArray, response.getSlotNumbers().toArray(new Integer[2]));

        r = Request.builder().setColour(Colour.WHITE).build();
        response = ParkingManager.getSlotsForColour(r);
        Assert.assertEquals("Not found", response.getMessage());

    }

    @Test
    public void slotNoForRegNoTest(){
        ParkingManager.init(2);

        Request r = Request.builder().setColour(Colour.BLACK).setRegNo("Ka-03-mh-9999").build();
        Response response = ParkingManager.parkCar(r);

        r = Request.builder().setRegNo("Ka-03-mh-9999").build();
        response = ParkingManager.getSlotNoForRegNo(r);
        Assert.assertEquals(new Integer(1), response.getSlotNumberForRegNo());

        r = Request.builder().setRegNo("Ka-04-mh-9999").build();
        response = ParkingManager.getSlotNoForRegNo(r);
        Assert.assertEquals("Not found", response.getMessage());
    }


    @Test
    public void priorityTest(){
        ParkingManager.init(3);

        Request r = Request.builder().setColour(Colour.BLACK).setRegNo("Ka-03-mh-9999").build();
        Response response = ParkingManager.parkCar(r);

        r = Request.builder().setColour(Colour.WHITE).setRegNo("Ka-04-mh-9999").build();
        response = ParkingManager.parkCar(r);

        r = Request.builder().setSlotNumber(1).build();
        response = ParkingManager.leaveParking(r);

        r = Request.builder().setColour(Colour.GREEN).setRegNo("Ka-05-mh-9999").build();
        response = ParkingManager.parkCar(r);
        Assert.assertEquals("Allocated slot number: 1", response.getMessage());

    }
}
