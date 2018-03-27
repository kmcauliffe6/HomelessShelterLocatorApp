package edu.gatech.cs2340.thisteamnameapp;

/**
 * Created by paigemca on 3/27/18.
 */

public abstract class AbstractCommand {
    public final static CommandManager manager = new CommandManager();
    public abstract boolean execute();
    //public abstract boolean undo();
}
