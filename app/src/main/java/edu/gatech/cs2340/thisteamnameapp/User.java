package edu.gatech.cs2340.thisteamnameapp;

import android.support.annotation.Nullable;

import java.io.Serializable;
import java.io.PrintWriter;

/**
 * Information Holder class for User objects
 * Created by Paige McAuliffe on 2/20/18.
 */

public class User implements Serializable {
    @Nullable
    private final String name;
    @Nullable
    private final String userid;
    @Nullable
    private final String password;
    @Nullable
    private final String actType;
    private String shelterCheckedInto;

    /**
     * Constructor for User class
     * @param name the User's name
     * @param userid the user's log in id
     * @param password used for log in
     * @param type the user's account type (admin, shelter employee, etc)
     */
    public User(@Nullable String name, @Nullable String userid,
                @Nullable String password, @Nullable String type) {
        this.name = name;
        this.userid = userid;
        this.password = password;
        actType = type;
        shelterCheckedInto = "Not Checked In";
    }

    /**
     * No argument constructor
     */
    public User() {
        this.name = null;
        this.userid = null;
        this.password = null;
        actType = null;
        shelterCheckedInto = "Not Checked In";
    }

// --Commented out by Inspection START (4/8/18, 8:48 PM):
//    /** setter for name instance variable
//     * @param name the user's name  */
//    public void setName(String name) {
//        this.name = name;
//    }
// --Commented out by Inspection STOP (4/8/18, 8:48 PM)
    /*public void setUserid(String id) {
        userid = id;
    } */
// --Commented out by Inspection START (4/8/18, 8:48 PM):
//    /** getter for name instance variable
//     * @return name the user's name  */
//    public String getName() {
//        return name;
//    }
// --Commented out by Inspection STOP (4/8/18, 8:48 PM)
    /** getter for userid instance variable
     * @return userid the user's id  */
    @Nullable
    public String getUserid() {
        return userid;
    }
    /** getter for password instance variable
     * @return the user's name  */
    @Nullable
    public String getPassword() {
        return password;
    }

    /** getter for shelter checked into
     * @return shelter that is being checked into*/
    public String getShelterCheckedInto() {
        return shelterCheckedInto;
    }

    /** setter for shelter checked into
     * @param shelterName the shelter name   */
    public void setShelterCheckedInto(String shelterName) {
        shelterCheckedInto = shelterName;
        checkIn(shelterName);
    }

    /** check into a shelter
     * @param shelterName the shelter name
     * @return true if the user was able to check into a new shelter
     */
    public boolean checkIn(String shelterName) {
        if (shelterName.equals("")) {
            return false;
        } else if (shelterCheckedInto == null) {
            shelterCheckedInto = shelterName;
            return true;
        } else if (shelterName.equals(shelterCheckedInto)) {
            return false;
        } else {
            shelterCheckedInto = shelterName;
            return true;
        }
    }

    @Override
    public String toString() {
        return "User: " + name + " " + userid + " " + password + " " + actType;
    }
    /** method checks whether this User is checked into a shelter
     * @return boolean for "is checked out?"  */
    public boolean isCheckedOut() {
        return (!"Not Checked In".equals(shelterCheckedInto));
    }

    /**
     * Save this class in a custom save format
     * uses tab (\t) to make line splitting easy for loading
     *
     * @param writer the file to write this student to
     */
    public void saveAsText(PrintWriter writer) {
        //System.out.println("User saving user: " + name); add as toast
        writer.println(name + "\t" + userid + "\t" + password + "\t" + actType);
    }


    /*
    public static User parseEntry(String line) {
        assert line != null;
        String[] tokens = line.split("\t");
        assert tokens.length == 4;
        User s = new User(tokens[0], tokens[1], tokens[2], tokens[3]);
        return s;
    } */
}
