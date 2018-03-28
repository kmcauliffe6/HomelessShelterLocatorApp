package edu.gatech.cs2340.thisteamnameapp;

/**
 * Created by paigemca on 3/27/18.
 */

import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class ModelManagementFacade {
    public final static String DEFAULT_BINARY_FILE_NAME = "data.bin";
    public final static String DEFAULT_TEXT_FILE_NAME = "data.txt";
    public final static String DEFAULT_JSON_FILE_NAME = "data.json";

        /**
         * the facade maintains references to any required model classes
         */
        private Model sm;

        /**
         * Singleton pattern
         */
        private static ModelManagementFacade instance = new ModelManagementFacade();


        /**
         * private constructor for facade pattern
         */
        private ModelManagementFacade() {
            sm = Model.getInstance();
        }

        /**
         * Singleton pattern accessor for instance
         *
         * @return the one and only one instance of this facade
         */
        public static ModelManagementFacade getInstance() {
            return instance;
        }

        public Map<String, User> getUsersAsList() {
            return sm.getMap();
        }

        public List<Shelter> getShelterList() {return sm.getShelters();}

        public void setUpShelterList(InputStream is) throws IOException {
            sm.createShelterList(is);
        }



        public User getUserByID(final String id) {
            return sm.getUserByID(id);
        }

        public Shelter getShelterByID(final int id) {
            return sm.findItemByID(id);
        }



        public void addUser(final String name, final String password, final String id, final String actType) {
            sm.addUser(name, password, id, actType);

        }

        public boolean loadBinary(File file) {
            boolean success = true;
            try {
            /*
              To read, we must use the ObjectInputStream since we want to read our model in with
              a single read.
             */
                ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
                // assuming we saved our top level object, we read it back in with one line of code.
                sm = (Model) in.readObject();
                sm.regenMap();
                in.close();
            } catch (IOException e) {
                Log.e("UserManagementFacade", "Error reading an entry from binary file", e);
                success = false;
            } catch (ClassNotFoundException e) {
                Log.e("UserManagementFacade", "Error casting a class from the binary file", e);
                success = false;
            }

            return success;
        }

        /*public boolean loadText(File file) {
            try {
                //make an input object for reading
                BufferedReader reader = new BufferedReader(new FileReader(file));
                sm.loadFromText(reader);

            } catch (FileNotFoundException e) {
                Log.e("ModelSingleton", "Failed to open text file for loading!");
                return false;
            }

            return true;
        } */
        /*
        public boolean loadJson(File file) {
            try {
                BufferedReader input = new BufferedReader(new FileReader(file));
                //Since we saved the json as a string, we just read in the string normally
                String inString = input.readLine();
                Log.d("DEBUG", "JSON: " + inString);
                //Then we use the Gson library to recreate the object references and links automagically
                Gson gson = new Gson();

                sm = gson.fromJson(inString, Model.class);

                input.close();
            } catch (IOException e) {
                Log.e("UserManagementFacade", "Failed to open/read the buffered reader for json");
                return false;
            }

            return true;

        } */

        public boolean saveBinary(File file) {
            boolean success = true;
            try {
            /*
               For binary, we use Serialization, so everything we write has to implement
               the Serializable interface.  Fortunately all the collection classes and APi classes
               that we might use are already Serializable.  You just have to make sure your
               classes implement Serializable.

               We have to use an ObjectOutputStream to write objects.

               One thing to be careful of:  You cannot serialize static data.
             */


                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
                // We basically can save our entire data model with one write, since this will follow
                // all the links and pointers to save everything.  Just save the top level object.
                out.writeObject(sm);
                out.close();

            } catch (IOException e) {
                Log.e("UserManagerFacade", "Error writing an entry from binary file", e);
                success = false;
            }
            return success;
        }

        public boolean saveText(File file) {
            System.out.println("Saving as a text file");
            try {
                PrintWriter pw = new PrintWriter(file);
                sm.saveAsText(pw);
                pw.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Log.d("UserManagerFacade", "Error opening the text file for save!");
                return false;
            }

            return true;
        }

        public boolean saveJson(File file) {

            try {
                PrintWriter writer = new PrintWriter(file);
            /*
                We are using the Google Gson library to make things easy.  You will need to add the
                following line to your gradle file so the proper dependencies are set up:
                compile 'com.google.code.gson:gson:2.3'

                Gson, like object serialization will take a single object and save all the objects
                it refers to.  You can save everything by one reference, as long as it is the
                top-level reference.


             */
                Gson gson = new Gson();
                // convert our objects to a string for output
                String outString = gson.toJson(sm);
                Log.d("DEBUG", "JSON Saved: " + outString);
                //then just write the string
                writer.println(outString);
                writer.close();
            } catch (FileNotFoundException e) {
                Log.e("UserManagementFacade", "Failed to open json file for output");
                return false;
            }

            return true;
        }


        void addUser(User student) {
            sm.addUser(student);
        }


        /*void removeStudent(Student student) {
            sm.removeStudent(student);
        }*/
    }

