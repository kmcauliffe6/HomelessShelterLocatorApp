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
     * testing getting vacancy
     */
    public void getVacancy() {
        testShelter = new Shelter("Bura's", "10", "Female",
                "1", "1", "301 10th St", "4445556678",
                123, "the best shelter!");
        u = new User("Bura","5", "pass", "admin");

        //original vacancy
        assertEquals("accurate when vacancy > 0", 10,
                Integer.parseInt(testShelter.getVacancy()));

        //vacancy > 0
        testShelter.updateVacancy(5);
        assertEquals("vacancy decreased properly", 5,
                Integer.parseInt(testShelter.getVacancy()));

        //bringing vacancy back up
        testShelter.decreaseVacancy(2);
        assertEquals("vacancy decreased properly", 7,
                Integer.parseInt(testShelter.getVacancy()));

        //vacancy = 0
        testShelter.updateVacancy(7);
        assertEquals("accurate when vacancy = 0", 0,
                Integer.parseInt(testShelter.getVacancy()));

        //vacancy < 0
        testShelter.updateVacancy(2);
        assertEquals("accurate when vacancy < 0", 0,
                Integer.parseInt(testShelter.getVacancy()));
    }

    /**
     * testing updating vacancy
    */
    @Test
    public void testUpdateVacancy() {
        // assertEquals("Simple Add result incorrect",56, testModel.execute(16, 40, "+"));
        testShelter = new Shelter("Mari's", "50", "Female",
                "1", "1", "301 10th St", "4445556678",
                123, "the best shelter!");
        u = new User("nish","1", "pass", "admin");
        // check vacancy update, checkout 5 beds
        testShelter.updateVacancy(5);
        assertEquals("vacancy decreased properly", 45,
                Integer.parseInt(testShelter.getVacancy()));

        // check vacancy update, try to checkout -1 beds
        testShelter.updateVacancy(-1);
        assertEquals("Can't checkout a negative amount of beds", 45,
                Integer.parseInt(testShelter.getVacancy()));

        // check vacancy update, try to checkout 0 beds
        testShelter.updateVacancy(0);
        assertEquals("Can't checkout 0 beds", 45,
                Integer.parseInt(testShelter.getVacancy()));

        // check vacancy update, try to checkout 7 beds
        testShelter.updateVacancy(7);
        assertEquals("Can't checkout 7 beds", 45,
                Integer.parseInt(testShelter.getVacancy()));

        // check vacancy update, try to checkout 1 bed
        testShelter.updateVacancy(1);
        assertEquals("Checked out 1 bed", 44,
                Integer.parseInt(testShelter.getVacancy()));

        // check vacancy update, try to checkout 6 beds
        testShelter.updateVacancy(6);
        assertEquals("Checked out 6 beds", 38,
                Integer.parseInt(testShelter.getVacancy()));
    }

    /**
     * testing decreasing vacancy
     */
    @Test
    public void testDecreaseVacancy() {
        // assertEquals("Simple Add result incorrect",56, testModel.execute(16, 40, "+"));
        testShelter = new Shelter("Mari's", "50", "Female",
                "1", "1", "301 10th St", "4445556678",
                123, "the best shelter!");
        u = new User("nish","1", "pass", "admin");

        // return more beds than the capacity
        assertEquals("More beds returned than allowed", false,
                testShelter.decreaseVacancy(5));

        //return the right amount of beds
        //checkout 5 beds
        testShelter.updateVacancy(5);
        testShelter.decreaseVacancy(2);
        testShelter.decreaseVacancy(3);
        assertEquals("Returned beds correctly", 50,
                Integer.parseInt(testShelter.getVacancy()));

        //checkout 20 beds -- vacancy = 30
        testShelter.updateVacancy(5);
        testShelter.updateVacancy(5);
        testShelter.updateVacancy(5);
        testShelter.updateVacancy(5);
        assertEquals("Correctly checked out 20 beds", 30,
                Integer.parseInt(testShelter.getVacancy()));

        // check vacancy update, try to checkout 0 beds
        testShelter.decreaseVacancy(0);
        assertEquals("Can't return 0 beds", 30,
                Integer.parseInt(testShelter.getVacancy()));

        // check vacancy update, try to checkout 7 beds
        testShelter.decreaseVacancy(1);
        assertEquals("Returned 1 bed",31,
                Integer.parseInt(testShelter.getVacancy()));

        // check vacancy update, try to checkout 1 bed
        testShelter.decreaseVacancy(6);
        assertEquals("Returned 6 beds", 37,
                Integer.parseInt(testShelter.getVacancy()));

        // check vacancy update, try to checkout 6 beds
        testShelter.decreaseVacancy(7);
        assertEquals("Can't return 7 beds", 37,
                Integer.parseInt(testShelter.getVacancy()));
    }

}
