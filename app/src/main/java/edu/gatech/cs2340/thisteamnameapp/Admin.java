package edu.gatech.cs2340.thisteamnameapp;

/**
 * Created by paigemca on 2/20/18.
 *
 * we aren't using this yet, all users are instantiated as User but may be useful later
 */

public class Admin {
    private String name;
    private String userid;
    private String password;

    /** constuctor */
    public Admin(String name, String userid, String password) {
        this.name = name;
        this.userid = userid;
        this.password = password;
    }

    /** getters and setters */
    public void setName(String name) {
        this.name = name;
    }
    public void setUserid(String id) {
        userid = id;
    }

    public void editUser(int userid) {

    }
    public void editShelter(int shelterid) {

    }
}
