package edu.gatech.cs2340.thisteamnameapp;

import java.io.Serializable;
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
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by paigemca on 2/20/18.
 */

public class Model implements Serializable {
    private static final Model _instance = new Model();
    public static Model getInstance() { return _instance; }

    /** holds the list of all users */
    private final List<User> users;
    private transient Map<String, User> userMap = new HashMap<>();
    /** holds the list of all users */
    //private List<Admin> admins;
    private final List<Shelter> shelters;
    private User currentUser;

    private Model() {
        users = new ArrayList<>();
        shelters = new ArrayList<>();
        //admins = new ArrayList<>();
    }

    public List<Shelter> getShelters() {
        return shelters;
    }

    public Map<String, User> getUsers() {
        return (HashMap<String, User>) userMap;
    }

    public void setCurrentUser(User u) {
        currentUser = u;
    }
    public User getCurrentUser() {
        return currentUser;
    }

    public void addUser(User m) {
        users.add(m);
        userMap.put(m.getUserid(), m);
        System.out.println(m.getUserid());
        System.out.println(m.getPassword());
    }

    public void addShelter(Shelter s) {
            shelters.add(s);}

    /**
     * parses CSV file and adds Shelters to shelters list
     * @param is the input from the CSV file
     * @throws IOException
     */
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

    /**
     * finds shelters with input parameter
     * @param id the shelter id
     * @return shelter matching the input id
     */
    public Shelter findItemByID(int id) {
        for (Shelter d : shelters) {
            if (d.getId() == id) return d;
        }
        return null;
    }

    /**
     *
     * @param writer
     */
    void saveAsText(PrintWriter writer) {
        System.out.println("Manager saving: " + users.size() + " users" );
        writer.println(users.size());
        for(User s : users) {
            s.saveAsText(writer);
        }
    }

    /**
     * load the model from a custom text file
     *
     * @param reader  the file to read from
     */
    void loadFromText(BufferedReader reader) {
        System.out.println("Loading Text File");
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
        System.out.println("Done loading text file with " + users.size() + " students");

    }

    /**
     * This should only be called during serialization (reading in).
     *
     * This recomputes the student map which is derived from the student collection.
     *
     */
    void regenMap() {
        if (userMap != null)
            userMap.clear();
        else
            userMap = new HashMap<>();
        for (User s : users) {
            userMap.put(s.getName(), s);
        }
    }

}
