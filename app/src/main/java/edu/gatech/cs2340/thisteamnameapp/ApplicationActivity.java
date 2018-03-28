package edu.gatech.cs2340.thisteamnameapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * main screen with buttons for "see shelters" and "log out"
 */
public class ApplicationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void goToWelcomeActivity (View view){
        Intent intent = new Intent (this, MainActivity.class);
        File file = new File(this.getFilesDir(), ModelManagementFacade.DEFAULT_BINARY_FILE_NAME);
        ModelManagementFacade.getInstance().saveBinary(file);
        startActivity(intent);
    }

    public void goToShelterListActivity (View view) throws IOException {
        //see shelters pressed
        readSDFFile();
        Intent intent = new Intent (this, ShelterListActivity.class);
        startActivity(intent);
    }

    private void readSDFFile() throws IOException {
        ModelManagementFacade model = ModelManagementFacade.getInstance();
        try {
            //Open a stream on the raw file
            InputStream is = getResources().openRawResource(R.raw.shelters);
            model.setUpShelterList(is);
            //From here we probably should call a model method and pass the InputStream
            //Wrap it in a BufferedReader so that we get the readLine() method
        }catch (IOException e) {

        }





}}
