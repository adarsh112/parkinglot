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

    ParkingManager parkingManager;
    @Mock
    private MainClass mainClass;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void initTest(){
        ParkingManager parkingManager = ParkingManager.init(5);
        ParkingManager parkingManager2 = ParkingManager.init(4);
        Assert.assertEquals(parkingManager, parkingManager2);
        Assert.assertEquals(5, parkingManager.getAvailableSlots());
        Assert.assertEquals(5, parkingManager2.getAvailableSlots());
    }

    @Test
    public void parkTest(){
        ParkingManager parkingManager = ParkingManager.init(2);

        Request r = Request.builder().setColour(Colour.BLACK).setRegNo("Ka-03-mh-9999").build();
        Response resp = parkingManager.parkCar(r);
        Assert.assertEquals("Allocated slot number: 1", resp.getMessage());

        resp = parkingManager.parkCar(r);
        Assert.assertEquals("Allocated slot number: 2", resp.getMessage());

        resp = parkingManager.parkCar(r);
        Assert.assertEquals("Sorry, parking lot is full", resp.getMessage());

    }

    @Test
    public void leaveTest(){
        ParkingManager parkingManager = ParkingManager.init(2);

        Request r = Request.builder().setColour(Colour.BLACK).setRegNo("Ka-03-mh-9999").build();
        Response resp = parkingManager.parkCar(r);

        r = Request.builder().setSlotNumber(1).build();
        resp = parkingManager.leaveParking(r);
        Assert.assertEquals("Slot number 1 is free", resp.getMessage());

        r = Request.builder().setSlotNumber(3).build();
        resp = parkingManager.leaveParking(r);
        Assert.assertEquals("The slot number is not allotted to any car", resp.getMessage());

    }

    @Test
    public void regNoForColourTest(){
        ParkingManager parkingManager = ParkingManager.init(2);

        Request r = Request.builder().setColour(Colour.BLACK).setRegNo("Ka-03-mh-9999").build();
        Response resp = parkingManager.parkCar(r);

        r = Request.builder().setColour(Colour.BLACK).build();
        resp = parkingManager.getRegNoForColour(r);
        Assert.assertEquals("Ka-03-mh-9999", resp.getRegNumbers().get(0));

        r = Request.builder().setColour(Colour.BLACK).setRegNo("Ka-04-mh-9999").build();
        resp = parkingManager.parkCar(r);

        r = Request.builder().setColour(Colour.BLACK).build();
        resp = parkingManager.getRegNoForColour(r);
        String [] expectArray = {"Ka-03-mh-9999", "Ka-04-mh-9999"};
        Assert.assertArrayEquals(expectArray, resp.getRegNumbers().toArray(new String[2]));

        r = Request.builder().setColour(Colour.WHITE).build();
        resp = parkingManager.getRegNoForColour(r);
        Assert.assertEquals("Not found", resp.getMessage());

    }


    @Test
    public void priorityTest(){
        ParkingManager parkingManager = ParkingManager.init(2);

        Request r = Request.builder().setColour(Colour.BLACK).setRegNo("Ka-03mh-9999").build();
        Response resp = parkingManager.parkCar(r);

        r = Request.builder().setSlotNumber(1).build();
        resp = parkingManager.leaveParking(r);
        Assert.assertEquals("Slot number 1 is free", resp.getMessage());

        r = Request.builder().setColour(Colour.BLACK).setRegNo("Ka-03mh-9999").build();
        resp = parkingManager.parkCar(r);
        Assert.assertEquals("The slot number is not allotted to any car", resp.getMessage());

    }
}
