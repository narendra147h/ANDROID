package myservices.com.services;

import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ServiceTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.junit.Assert.*;

/**
 * Created by adpr270759 on 12-04-2018.
 */
@RunWith(JUnit4.class)
public class MyserviceTest {

    @Rule public final ServiceTestRule mServiceRule = ServiceTestRule.withTimeout(60, TimeUnit.SECONDS);

    @Test
    public void testWithStartedService() throws TimeoutException {

        Intent intent = new Intent(InstrumentationRegistry.getTargetContext(), Myservice.class);

        mServiceRule.startService(intent);
    }
}