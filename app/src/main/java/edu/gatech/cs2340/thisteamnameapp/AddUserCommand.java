package edu.gatech.cs2340.thisteamnameapp;

/**
 * Created by paigemca on 3/27/18.
 */

public class AddUserCommand extends AbstractCommand {
    User user;

    public AddUserCommand(User s) {
        user = s;
    }

    @Override
    public boolean execute() {
        ModelManagementFacade umf = ModelManagementFacade.getInstance();
        umf.addUser(user);
        return true;
    }

    /*@Override
    public boolean undo() {
        ModelManagementFacade umf = ModelManagementFacade.getInstance();
        umf.removeStudent(student);
        return true;
    }*/

}
