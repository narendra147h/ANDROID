package com.boundservice;

import android.content.Intent;
import android.os.IBinder;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ServiceTestRule;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.concurrent.TimeoutException;

import static org.junit.Assert.assertTrue;

/**
 * Created by adpr270759 on 13-04-2018.
 */

@RunWith(JUnit4.class)
public class MyBoundServiceTest {

    ServiceTestRule mServiceTest=new ServiceTestRule();

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testWithBoundService() throws TimeoutException {
        Intent intent = new Intent(InstrumentationRegistry.getTargetContext(), MyBoundService.class);

        IBinder iBinder = mServiceTest.bindService(intent);

        MyBoundService.Mybinder mybinder = (MyBoundService.Mybinder) iBinder;

        MyBoundService service = mybinder.getobj();

        service.mydate();

        assertTrue("True wasn't returned", service.doSomethingToReturnTrue());


    }

    @After
    public void tearDown() throws Exception {
    }

}