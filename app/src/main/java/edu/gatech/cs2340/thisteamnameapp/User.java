package edu.gatech.cs2340.thisteamnameapp;

/**
 * Created by paigemca on 2/20/18.
 */

public class User {
    private String name;
    private String userid;
    private String password;
    private String actType;


    public User(String name, String userid, String password, String type) {
        this.name = name;
        this.userid = userid;
        this.password = password;
        actType = type;
    }

    /** getters and setters */
    public void setName(String name) {
        this.name = name;
    }
    public void setUserid(String id) {
        userid = id;
    }

    public String getUserid() {
        return userid;
    }
    public String getPassword() {
        return password;
    }
}
