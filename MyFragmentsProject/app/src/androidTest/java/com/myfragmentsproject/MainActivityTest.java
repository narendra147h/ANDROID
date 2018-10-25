package com.myfragmentsproject;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.Instrumentation;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.TextView;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.w3c.dom.Text;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * Created by adpr270759 on 10-04-2018.
 */



@RunWith(AndroidJUnit4.class)
public class MainActivityTest{


    /**
     * We make rule for launching Activity under junit
     */

    @Rule
    public ActivityTestRule<MainActivity>  mActivity=new ActivityTestRule<MainActivity>(MainActivity.class);


    /**
     * @Before annotation is used for the function which is start before the test perform
     * Mainly It's Used for initialization
     */

    @Before
    public void setUp() throws Exception {

    }

    /**
     * @Test If We want to Perform Test then We use this annotion.
     */


    @Test
    public void pass_twoValueAndAfter_clikingSum_return() throws InterruptedException {

        onView(withId(R.id.num1)).perform(typeText(String.valueOf(8)));
        onView(withId(R.id.num2)).perform(typeText(String.valueOf(8)));



        onView(withId(R.id.plus)).perform(click());


        onView(withId(R.id.answer)).check(matches(withText("16")));

    }


    @Test
    public void pass_twoValueAndAfter_clikingMultiplyReturn() throws InterruptedException
    {
        onView(withId(R.id.num3)).perform(typeText(String.valueOf(3)),closeSoftKeyboard());
        onView(withId(R.id.num4)).perform(typeText(String.valueOf(2)),closeSoftKeyboard());


        onView(withId(R.id.mul)).perform(click());

        onView(withId(R.id.answer)).check(matches(withText("6")));
    }

    @Test
    public void PassOnlyOneValue_thenItTakesDefaultValueAndPerform_op() throws InterruptedException
    {
        onView(withId(R.id.num1)).perform(typeText(""),closeSoftKeyboard());
        onView(withId(R.id.num2)).perform(typeText(String.valueOf(4)),closeSoftKeyboard());

        onView(withId(R.id.plus)).perform(click());

        onView(withId(R.id.answer)).check(matches(withText("4")));

    }
    @Test
    public void ifPass_twoAlphaValue_thenitHandeledOnSum() throws InterruptedException
    {
        onView(withId(R.id.num1)).perform(typeText(String.valueOf("q")),closeSoftKeyboard());
        onView(withId(R.id.num2)).perform(typeText(String.valueOf(4)),closeSoftKeyboard());

        onView(withId(R.id.plus)).perform(click());


    }
    @Test
    public void ifPass_twoAlphaValue_thenitHandeledOnMultiply() throws InterruptedException
    {
        onView(withId(R.id.num3)).perform(typeText(String.valueOf("q")),closeSoftKeyboard());
        onView(withId(R.id.num4)).perform(typeText(String.valueOf(4)),closeSoftKeyboard());

        onView(withId(R.id.mul)).perform(click());


    }

    @Test
    public void _ifWeNotPassing_anyValueonCLicking_Sum()
    {
        onView(withId(R.id.num1)).perform(typeText(""),closeSoftKeyboard());
        onView(withId(R.id.num2)).perform(typeText(""),closeSoftKeyboard());

        onView(withId(R.id.plus)).perform(click());

        onView(withId(R.id.answer)).check(matches(withText("0")));

    }
    @Test
    public void _ifWeNotPassing_anyValueonCLicking_Mutliply()
    {
        onView(withId(R.id.num3)).perform(typeText(""),closeSoftKeyboard());
        onView(withId(R.id.num4)).perform(typeText(""),closeSoftKeyboard());

        onView(withId(R.id.mul)).perform(click());

        onView(withId(R.id.answer)).check(matches(withText("0")));

    }



    /**
     *
     * @After this annotation is work when test is performed and this is used for close all Type of Connection
     */
    @After
    public void tearDown() throws Exception {
        //mainActivity=null;

    }

}