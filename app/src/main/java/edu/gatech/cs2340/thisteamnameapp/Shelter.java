package edu.gatech.cs2340.thisteamnameapp;

/**
 * Created by paigemca on 2/26/18.
 */

public class Shelter {
    private String shelterName;
    private String capacity;
    private String gender;
    private String longitude;
    private String latitude;
    private String address;
    private String phoneNumber;
    private int id;
    private String details;

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

}