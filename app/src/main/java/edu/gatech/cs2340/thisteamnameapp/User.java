package edu.gatech.cs2340.thisteamnameapp;

import java.io.Serializable;
import java.io.PrintWriter;

/**
 * Created by paigemca on 2/20/18.
 */

public class User implements Serializable {
    private String name;
    private String userid;
    private String password;
    private String actType;
    private String shelterCheckedInto;


    public User(String name, String userid, String password, String type) {
        this.name = name;
        this.userid = userid;
        this.password = password;
        actType = type;
        shelterCheckedInto = "Not Checked In";
    }

    /** getters and setters */
    public void setName(String name) {
        this.name = name;
    }
    public void setUserid(String id) {
        userid = id;
    }
    public String getName() {
        return name;
    }
    public String getUserid() {
        return userid;
    }
    public String getPassword() {
        return password;
    }
    public String getShelterCheckedInto() {
        return shelterCheckedInto;
    }
    public void setShelterCheckedInto(String shelterName) {
        shelterCheckedInto = shelterName;
    }

    @Override
    public String toString() {
        return "User: " + name + " " + userid + " " + password + " " + actType;
    }

    public boolean isCheckedOut() {
        return (!shelterCheckedInto.equals("Not Checked In"));
    }

    /**
     * Save this class in a custom save format
     * uses tab (\t) to make line splitting easy for loading
     *
     * @param writer the file to write this student to
     */
    public void saveAsText(PrintWriter writer) {
        System.out.println("User saving user: " + name);
        writer.println(name + "\t" + userid + "\t" + password + "\t" + actType);
    }

    /**
     * static factory method that constructs a student given a text line in the correct format.
     * It assumes that a student is in a single string with each attribute separated by a tab character
     * The order of the data is assumed to be:
     *
     * 0 - name
     * 1 - user id
     * 2 - password
     * 3 - actType
     *
     *
     * @param line  the text line containing the data
     * @return the student object
     */
    public static User parseEntry(String line) {
        assert line != null;
        String[] tokens = line.split("\t");
        assert tokens.length == 4;
        User s = new User(tokens[0], tokens[1], tokens[2], tokens[3]);
        return s;
    }
}
