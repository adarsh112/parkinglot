package com.gojek;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

public class MainClassTest {

    @Mock
    private MainClass mainClass;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testMainMethod(){
        MainClass.main(new String[]{"asd"});
    }

    @Test
    public void testSimpleMethod(){
        int ret = mainClass.simpleMethod(5);
        System.out.println(ret);
        Assert.assertEquals(6, ret);
    }
}
