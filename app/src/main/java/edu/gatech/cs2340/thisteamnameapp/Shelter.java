package edu.gatech.cs2340.thisteamnameapp;



import android.support.annotation.Nullable;

import java.io.Serializable;



/**
 * Information Holder class for Shelter objects
 * Created by paigemca on 2/26/18.
 */
//need to change capacity to int!!
@SuppressWarnings({"ClassWithTooManyDependents", "CyclicClassDependency"})
public class Shelter implements Serializable {
    @Nullable
    private final String shelterName;
    @Nullable
    private final String capacity;
    @Nullable
    private final String gender;
    @Nullable
    private final String longitude;
    @Nullable
    private final String latitude;
    @Nullable
    private final String address;
    @Nullable
    private final String phoneNumber;
    private final int id;
    @Nullable
    private final String details;
    private int bedsCheckedOut;
    /**
     * Constructor for Shelter class
     * @param shelterName the name of this shelter
     * @param capacity the number of available beds
     * @param gender the gender restrictions
     * @param longitude of location
     * @param latitude of location
     *  @param address the shelter's address
     * @param phoneNumber the shelter's phone #
     *  @param id the shelter id number
     *  @param details details about the shelter
     */

    public Shelter(@Nullable String shelterName, @Nullable String capacity, @Nullable String gender,
                   @Nullable String longitude, @Nullable String latitude, @Nullable String address,
                   @Nullable String phoneNumber, int id, @Nullable String details) {
        this.shelterName = shelterName;
        this.capacity = capacity;
        this.gender = gender;
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.id = id;
        this.details = details;
    }

    /**
     * no argument constructor for testing purposes
     *
     */
    public Shelter() {
        this.shelterName = null;
        this.capacity = null;
        this.gender = null;
        this.longitude = null;
        this.latitude = null;
        this.address = null;
        this.phoneNumber = null;
        this.id = 0;
        this.details = null;
    }

    /**
     * getter for shelter name
     * @return shelter name
     */
    public String getName() {
        return shelterName;
    }
    /**
     * @return shelter id
     */
    public int getId() {
        return id;
    }
    /**
     * @return shelter capacity
     */
    @Nullable
    public String getCapacity() {
        return capacity;
    }
    /**
     * @return gender
     */
    @Nullable
    public String getGender() {
        return gender;
    }
    /**
     * @return longitude
     */
    @Nullable
    public String getLongitude() {
        return longitude;
    }
    /**
     * @return latitude
     */
    @Nullable
    public String getLatitude() {
        return latitude;
    }
    /**
     * @return shelter phone number
     */
    public String getPhone() {
        return phoneNumber;
    }
    /**
     * @return shelter address
     */
    @Nullable
    public String getAddress() {
        return address;
    }
    /**
     * @return shelter details
     */
    public java.lang.CharSequence getDetails() {
        return details;
    }
    /**
     * method that takes in a number of beds (b), checks if current
     * user has checked out a bed and if there is space available,
     * updates vacancy count
     * @param b the number of beds to be checked out
     * @return boolean whether or not the beds could be removed
     */
    public boolean updateVacancy(int b) {
        ModelManagementFacade m = ModelManagementFacade.getInstance();
        if (b <= 0 || b > 6) {
            return false;
        } else if ((bedsCheckedOut + b) <= Integer.parseInt(capacity)) {
            bedsCheckedOut += b;
            return true;
        } else {
            return false;
        }
    }
    /**
     * @return number of beds checked out
     */
    private int getBedsCheckedOut() {
        return bedsCheckedOut;
    }
    /**
     * calculates the current number of vacancies from capacity and
     * beds checked out
     * @return number of vacancies
     */
    public String getVacancy() {
        int cap = Integer.parseInt(getCapacity());
        int out = getBedsCheckedOut();
        int vacancies = cap - out;
            if (vacancies >= 0) {
                return Integer.toString(vacancies);
            } else {
                return Integer.toString(0);
            }
    }
    /**
     * method that takes in a number of beds (b), checks if current
     * user has checked out a bed and if there is space available,
     * updates vacancy count
     * @param b the number of beds to be returned
     * @return boolean whether or not the beds could be returned
     */
    public boolean decreaseVacancy(int b) {
        ModelManagementFacade m = ModelManagementFacade.getInstance();
        int curVacancies = Integer.parseInt(capacity) - bedsCheckedOut;
        if (b <= 0 || b > 6) {
            return false;
        } else if ((curVacancies + b) <= Integer.parseInt(capacity)) {
            bedsCheckedOut -= b;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Shelter: " + shelterName + " " + id + " ";
    }


    /*
    public void saveAsText(PrintWriter writer) {
        //System.out.println("Shelter saving shelter: " + shelterName);
        writer.println(shelterName + "\t" + capacity + "\t" + gender + "\t" + longitude + "\t" +
        latitude
        + "\t" + address + "\t" + phoneNumber + "\t" + id + "\t" + details);
    }


    /**
     * This is a static factory method that constructs a student given a text line in the correct
     * format.
     * It assumes that a student is in a single string with each attribute separated by a tab
     * character
     * The order of the data is assumed to be:
     *
     * 0 - name
     * 1 - user id
     * 2 - id code
     * 3 - email
     * 4 - password
     *
     * @param line  the text line containing the data
     * @return the student object
     */
    /*
    public static Shelter parseEntry(String line) {
        assert line != null;
        String[] tokens = line.split("\t");
        assert tokens.length == 9;
        Shelter s = new Shelter(tokens[0], tokens[1], tokens[2], tokens[3],
         tokens[5], tokens[6], tokens[7],Integer.parseInt(tokens[8]),
                tokens[9]);
        return s;
    } */


}
