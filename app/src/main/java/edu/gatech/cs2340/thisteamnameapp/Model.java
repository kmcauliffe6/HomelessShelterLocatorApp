package edu.gatech.cs2340.thisteamnameapp;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * Created by paigemca on 2/20/18.
 */

public class Model {
    private static final Model _instance = new Model();
    public static Model getInstance() { return _instance; }

    /** holds the list of all users */
    private List<User> users;
    /** holds the list of all users */
    //private List<Admin> admins;
    private List<Shelter> shelters;

    private Model() {
        users = new ArrayList<>();
        shelters = new ArrayList<>();
        //admins = new ArrayList<>();
    }

    public List<Shelter> getShelters() {
        return shelters;
    }

    public ArrayList<User> getUsers() {
        return (ArrayList<User>) users;
    }

    public void addUser(User m) {
        users.add(m);
    }

    public void addShelter(Shelter s) {
            shelters.add(s);}

    public void createShelterList(InputStream is) throws IOException {
        try {

        BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

        String line;
        br.readLine(); //get rid of header line
        while ((line = br.readLine()) != null) {
            String[] tokens = line.split(",");
            int id = Integer.parseInt(tokens[0]);
            String name = tokens[1];
            String capacity = tokens[2];
            String gender = tokens[3];
            String longitude = tokens[4];
            String latitude = tokens[5];
            String address = tokens[6];
            String details = tokens[7];
            String phoneNumber = tokens[8];
            Shelter newShelter = new Shelter(name, capacity, gender,
                    longitude, latitude, address,
                    phoneNumber, id, details);
            addShelter(newShelter);
        }
        br.close();
    } catch (IOException o) {
    }}

    public Shelter findItemByID(int id) {
        for (Shelter d : shelters) {
            if (d.getId() == id) return d;
        }
        return null;
    }

}
