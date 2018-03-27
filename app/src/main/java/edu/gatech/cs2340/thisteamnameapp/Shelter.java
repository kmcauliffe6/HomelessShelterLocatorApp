package edu.gatech.cs2340.thisteamnameapp;

import java.io.Serializable;

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

    @Override
    public String toString() {
        return shelterName;
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
    public String getDetails() {
        return details;
    }

    public void updateVacancy(int b) {
        if (bedsCheckedOut + b <= Integer.parseInt(capacity)) {
            bedsCheckedOut = bedsCheckedOut + b;
        }
    }
    public int getBedsCheckedOut() {
        return bedsCheckedOut;
    }

    public String getVacancy() {
        int vacancies = Integer.parseInt(getCapacity()) - getBedsCheckedOut();
        return Integer.toString(vacancies);

    }

}
