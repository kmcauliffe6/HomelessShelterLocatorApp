package edu.gatech.cs2340.thisteamnameapp;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

//import static org.junit.Assert.assertNull;
//import static org.mockito.Mockito.*;

/**
 * Created by nishat and mariana on 4/6/18.
 */

public class HomelessAppTest {

    private Shelter testShelter;
    private User u;

    /**
     * set up of test
    */
    @Before
    public void setUp() {
        testShelter = new Shelter();
        u = new User();
    }

    //String shelterName, String capacity, String gender,
    //String longitude, String latitude, String address,
    //String phoneNumber, int id, String details

    //String name, String userid, String password, String type

    // Shelter.java

    /**
     * testing decreasing vacancy
    */
    @Test
    public void testDecreaseVacancy() {
        // assertEquals("Simple Add result incorrect",56, testModel.execute(16, 40, "+"));
        testShelter = new Shelter("Mari's", "50", "Female", "1", "1", "301 10th St", "4445556678", 123, "the best shelter!");
        u = new User("nish","1", "pass", "admin");
        // check vacancy update
        testShelter.decreaseVacancy(5);
        assertEquals("vacancy not decreased properly", 45, testShelter.getVacancy());
        // check boolean in if statement if user is checked out -> should return true
        assertEquals("boolean works not", true, testShelter.decreaseVacancy(0));
        // user is now not checked in


    }




}
