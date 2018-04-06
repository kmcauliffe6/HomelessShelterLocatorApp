//package edu.gatech.cs2340.thisteamnameapp;
//
//import android.os.SystemClock;
//import android.support.test.espresso.Espresso;
//import android.test.ActivityInstrumentationTestCase2;
//import android.support.test.runner.AndroidJUnit4;
//
//import org.junit.Before;
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import android.support.test.rule.ActivityTestRule;
//
//import static android.support.test.espresso.Espresso.onData;
//import static android.support.test.espresso.Espresso.onView;
//import static android.support.test.espresso.action.ViewActions.click;
//import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
//import static android.support.test.espresso.action.ViewActions.typeText;
//import static android.support.test.espresso.assertion.ViewAssertions.matches;
//import static android.support.test.espresso.matcher.ViewMatchers.withId;
//import static android.support.test.espresso.matcher.ViewMatchers.withText;
//import static org.hamcrest.Matchers.instanceOf;
//import static org.hamcrest.Matchers.is;
//import static org.hamcrest.core.AllOf.allOf;
//
///**
// * Created by robertwaters on 3/15/16.
// */
//@RunWith(AndroidJUnit4.class)
//public class ExampleMainActivityTest {
//
//
//    /** this line is preferred way to hook up to activity */
//    @Rule
//    public ActivityTestRule<MainActivity> mActivityRule =
//            new ActivityTestRule<>(MainActivity.class);
//
//
//    @Test
//    public void checkAdd() {
//        //type 23 into the first edit box
//        onView(withId(R.id.number1Field)).perform(typeText("23"), closeSoftKeyboard());
//        //type 45 into the second edit box
//        onView(withId(R.id.number2Field)).perform(typeText("45"), closeSoftKeyboard());
//        //click on the spinner to select it
//        onView(withId(R.id.spinner)).perform(click());
//        //no access the adapter to look for the chosen item (+ in this case) and select it
//        onData(allOf(is(instanceOf(String.class)), is("+"))).perform(click());
//        //now click the execute button
//        onView(withId(R.id.executeButton)).perform(click());
//        //SystemClock.sleep(1000);
//        /*
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        */
//        //finally we grab the result text and make sure it matches our result
//        onView(withId(R.id.resultText2)).check(matches(withText("68")));
//        Espresso.pressBack();
//    }
//
//    @Test
//    public void checkMultiply() {
//        //type 15 into the first edit box
//        onView(withId(R.id.number1Field)).perform(typeText("15"), closeSoftKeyboard());
//        //type 3 into the second edit box
//        onView(withId(R.id.number2Field)).perform(typeText("3"), closeSoftKeyboard());
//        //click on the spinner to select it
//        onView(withId(R.id.spinner)).perform(click());
//        //no access the adapter to look for the chosen item (* in this case) and select it
//        onData(allOf(is(instanceOf(String.class)), is("*"))).perform(click());
//        //now click the execute button
//        onView(withId(R.id.executeButton)).perform(click());
//
//        //SystemClock.sleep(1000);
//        /*
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }*/
//        //finally we grab the result text and make sure it matches our result
//        onView(withId(R.id.resultText2)).check(matches(withText("45")));
//        Espresso.pressBack();
//    }
//
//
//}
