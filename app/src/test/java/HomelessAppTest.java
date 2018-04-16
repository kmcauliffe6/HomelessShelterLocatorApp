package edu.gatech.cs2340.thisteamnameapp;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

//import static org.junit.Assert.assertNull;
//import static org.mockito.Mockito.*;

/**
 * Testing File for app
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
     * author: Bura
     */
    @Test
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
        testShelter.decreaseVacancy(1);
        assertEquals("vacancy decreased properly", 6,
                Integer.parseInt(testShelter.getVacancy()));

        //vacancy = 0
        testShelter.updateVacancy(6);
        assertEquals("accurate when vacancy = 0", 0,
                Integer.parseInt(testShelter.getVacancy()));

        //vacancy < 0
        testShelter.updateVacancy(2);
        assertEquals("accurate when vacancy < 0", 0,
                Integer.parseInt(testShelter.getVacancy()));
    }

    /**
     * testing updating vacancy
     * author: Paige
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
     * author: Mariana
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

    /**
     * testing whether user can check into a shelter
     * author: Nishat
     */
    @Test
    public void testCheckIn() {
        testShelter = new Shelter("Mari's", "50", "Female",
                "1", "1", "301 10th St", "4445556678",
                123, "the best shelter!");
        u = new User("nish","1", "pass", "admin");
        //public boolean isCheckedOut() {
        //return (!"Not Checked In".equals(shelterCheckedInto));

        // the user is checking in to one shelter
        assertEquals("Can't check into a null shelter", u.checkIn(""), false);

        //User checking in to a test shelter
        assertEquals("User is checked in", u.checkIn("Mari's"), true);

        // the user is trying to check into the same shelter
        assertEquals("User can't check into same shelter", u.checkIn("Mari's"), false);

        // user checks into a new shelter
        assertEquals("User checks into a new shelter", u.checkIn("Nishat's"), true);
        assertEquals("Checks into the right shelter", u.getShelterCheckedInto(), "Nishat's");

    }

    /**
     * testing whether entering ID returns the right shelter
     * author: Katie
     */
    @Test
    public void testfindItemByID() {
        //
  //      testShelter = new Shelter("Katie", "50", "Female",
                "1", "1", "301 10th St", "4445556678",
                123, "the best shelter!");
//        u = new User("katie","1", "pass", "admin");

       // Model m = new Model();
        //m.shelters = {}
        Shelter myshelter = testfindItemByID(11);
        // the user is looking for a specific shelter
        assertEquals("correct shelter sent", findItemByID(11), myshelter.getName());

        //User checking is looking for a specific shelter that doesn't exist
      //  assertEquals("returns the fact that the shelter id is not valid", findItemByID(), true);

        // the user is trying to find specific shelter and returns right shelter
        //assertEquals("", testfindItemByID();, false);
    }
}
