package edu.gatech.cs2340.thisteamnameapp;



import java.io.Serializable;



/**
 * Information Holder class for Shelter objects
 * Created by paigemca on 2/26/18.
 */
//need to change capacity to int!!
public class Shelter implements Serializable {
    private final String shelterName;
    private final String capacity;
    private final String gender;
    private final String longitude;
    private final String latitude;
    private final String address;
    private final String phoneNumber;
    private final int id;
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

    public Shelter(String shelterName, String capacity, String gender,
            String longitude, String latitude, String address,
            String phoneNumber, int id, String details) {
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
    public String getCapacity() {
        return capacity;
    }
    /**
     * @return gender
     */
    public String getGender() {
        return gender;
    }
    /**
     * @return longitude
     */
    public String getLongitude() {
        return longitude;
    }
    /**
     * @return latitude
     */
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
        User u = m.getCurUser();
        if (u.isCheckedOut()) {
            return false;
        }
        if ((bedsCheckedOut + b) <= Integer.parseInt(capacity)) {
            bedsCheckedOut = bedsCheckedOut + b;
            u.setShelterCheckedInto(shelterName); //method in user
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
        int vacancies = Integer.parseInt(getCapacity()) - getBedsCheckedOut();
        return Integer.toString(vacancies);

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
        User u = m.getCurUser();
        int curVacancies = Integer.parseInt(capacity) - bedsCheckedOut;
        if ((u.isCheckedOut())
                && (curVacancies +  b) <= Integer.parseInt(capacity)) {
            bedsCheckedOut = bedsCheckedOut - b;
            u.setShelterCheckedInto("Not Checked In");
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
        writer.println(shelterName + "\t" + capacity + "\t" + gender + "\t" + longitude + "\t" + latitude
        + "\t" + address + "\t" + phoneNumber + "\t" + id + "\t" + details);
    }


    /**
     * This is a static factory method that constructs a student given a text line in the correct format.
     * It assumes that a student is in a single string with each attribute separated by a tab character
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
        Shelter s = new Shelter(tokens[0], tokens[1], tokens[2], tokens[3], tokens[5], tokens[6], tokens[7],Integer.parseInt(tokens[8]),
                tokens[9]);
        return s;
    } */


}
