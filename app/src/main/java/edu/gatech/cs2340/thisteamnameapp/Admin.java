package edu.gatech.cs2340.thisteamnameapp;

/**
 * Created by paigemca on 2/20/18.
 *
 * we aren't using this yet, all users are instantiated as User but may be useful later
 */

public class Admin {
    private String name;

    /** constuctor
     * @param name for admin's name
     * @param userid for admin's id*/
    public Admin(String name, String userid) {
        this.name = name;
        String userid1 = userid;
    }

    /** getters and setters
     * @param name the admin's name */
    public void setName(String name) {
        this.name = name;
    }

}
