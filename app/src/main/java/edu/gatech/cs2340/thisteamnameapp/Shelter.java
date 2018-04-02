package edu.gatech.cs2340.thisteamnameapp;

import android.widget.Toast;

import java.io.Serializable;
import java.io.PrintWriter;


/**
 * Created by paigemca on 2/26/18.
 */
//need to change capacity to int!!
public class Shelter implements Serializable {
    private String shelterName;
    private String capacity;
    private String gender;
    private String longitude;
    private String latitude;
    private String address;
    private String phoneNumber;
    private int id;
    private String details;
    private int bedsCheckedOut;

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


    public String getName() {
        return shelterName;
    }
    public int getId() {
        return id;
    }
    public String getCapacity() {
        return capacity;
    }
    public String getGender() {
        return gender;
    }
    public String getLongitude() {
        return longitude;
    }
    public String getLatitude() {
        return latitude;
    }
    public String getPhone() {
        return phoneNumber;
    }
    public String getAddress() {
        return address;
    }
    public java.lang.CharSequence getDetails() {
        return details;
    }

    public boolean updateVacancy(int b) {
        ModelManagementFacade m = ModelManagementFacade.getInstance();
        User u = m.getCurUser();
        if (u.isCheckedOut()) {
            return false;
        }
        if (bedsCheckedOut + b <= Integer.parseInt(capacity)) {
            bedsCheckedOut = bedsCheckedOut + b;
            u.setShelterCheckedInto(shelterName);
            return true;
        } else {
            return false;
        }
    }
    public int getBedsCheckedOut() {
        return bedsCheckedOut;
    }

    public String getVacancy() {
        int vacancies = Integer.parseInt(getCapacity()) - getBedsCheckedOut();
        return Integer.toString(vacancies);

    }
    public boolean decreaseVacancy(int b) {
        ModelManagementFacade m = ModelManagementFacade.getInstance();
        User u = m.getCurUser();
        if (u.isCheckedOut()
                && Integer.parseInt(capacity) - bedsCheckedOut +  b <= Integer.parseInt(capacity)) {
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

    /**
     * Save this class in a custom save format
     * I chose to use tab (\t) to make line splitting easy for loading
     * If your data had tabs, you would need something else as a delimiter
     *
     * @param writer the file to write this student to
     */
    public void saveAsText(PrintWriter writer) {
        System.out.println("Shelter saving shelter: " + shelterName);
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
    public static Shelter parseEntry(String line) {
        assert line != null;
        String[] tokens = line.split("\t");
        assert tokens.length == 9;
        Shelter s = new Shelter(tokens[0], tokens[1], tokens[2], tokens[3], tokens[5], tokens[6], tokens[7],Integer.parseInt(tokens[8]),
                tokens[9]);
        return s;
    }


}
