package edu.gatech.cs2340.thisteamnameapp;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;


 //Created by Paige McAuliffe on 2/20/18.


public class Model implements Serializable {
    private static final Model _instance = new Model();
    /**
     * returns an instance of the model
     * @return instance of model
     */
    public static Model getInstance() {
        return _instance;
    }

    /**
     * holds the list of all users
     */
    private final List<User> users;
    private transient Map<String, User> userMap = new HashMap<>();
    /**
     * holds the list of all users
     */
    //private List<Admin> admins;
    private final List<Shelter> shelters;
    private User currentUser;

    private Model() {
        users = new ArrayList<>();
        shelters = new ArrayList<>();
        //admins = new ArrayList<>();
    }
    /**
     * @return the list of shelters
     */
    public List<Shelter> getShelters() {
        return shelters;
    }
    /**
     * @return the map of users
     */
    Map<String, User> getMap() {
        return userMap;
    }
    /**
     * @return the list of shelters
     * @param id the id of user being searched for
     */
    User getUserByID(String id) {
        return userMap.get(id);
    }
    /**
     * sets the current user when a user logs in
     * @param u the User logging in
     */
    public void setCurrentUser(User u) {
        currentUser = u;
    }
    /**
     * @return the current User
     */
    public User getCurrentUser() {
        return currentUser;
    }
    /**
     * adds a user to the user list and map
     * @param m the User being added
     */
    public void addUser(User m) {
        users.add(m);
        userMap.put(m.getUserid(), m);
    }
    /**
     * adds a shelters to the list of shelters
     * @param s the shelter being added
     */
    private void addShelter(Shelter s) {
        shelters.add(s);
    }
    /**
     * parses CSV file and adds Shelters to shelters list
     * @param is the input from the CSV file
     * @throws IOException if happens
     */
    public void createShelterList(InputStream is) throws IOException {
        try {

        BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

        String line;
        br.readLine(); //get rid of header line
        if (shelters.isEmpty() || users.isEmpty()) {
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                String id = tokens[0];
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
                        phoneNumber, Integer.parseInt(id), details);
                addShelter(newShelter);
            }
        }

        br.close();
    } catch (IOException o) {
    }}

    /**
     * finds shelters with input parameter
     * @param id the shelter id
     * @return shelter matching the input id
     */
    public Shelter findItemByID(int id) {
        for (Shelter d : shelters) {
            if (d.getId() == id) {return d;}
        }
        return null;
    }

    /**
     *
     * @param writer the writer
     */
    void saveAsText(PrintWriter writer) {
        writer.println(users.size());
        for(User s : users) {
            s.saveAsText(writer);
        }
    }


    /*
    /**
     * load the model from a custom text file
     *
     * @param reader  the file to read from
     */
    /*
    void loadFromText(BufferedReader reader) {
        users.clear();
        try {
            String countStr = reader.readLine();
            assert countStr != null;
            int count = Integer.parseInt(countStr);

            //then read in each user to model
            for (int i = 0; i < count; ++i) {
                String line = reader.readLine();
                User s = User.parseEntry(line);
                users.add(s);
                userMap.put(s.getUserid(), s);
            }
            //be sure and close the file
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    } */

    /**
     * This should only be called during serialization (reading in).
     *
     * This recomputes the student map which is derived from the student collection.
     *
     */
    void regenMap() {
        if (userMap != null) {
            userMap.clear();
        } else {
            userMap = new HashMap<>();
        }
        for (User s : users) {
            userMap.put(s.getUserid(), s);
        }
    }



}
