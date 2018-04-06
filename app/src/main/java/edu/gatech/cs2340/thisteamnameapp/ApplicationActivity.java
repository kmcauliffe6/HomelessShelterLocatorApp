package edu.gatech.cs2340.thisteamnameapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

/**
 * main screen with buttons for "see shelters" and "log out"
 */
public class ApplicationActivity extends AppCompatActivity {

    Spinner spinnerctrl;
    Button btn;
    Locale myLocale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spinnerctrl = (Spinner) findViewById(R.id.languagespinner);
        String[] arr = {"English", "Spanish", "French"};
        final ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerctrl.setAdapter(adapter);

        Button languagebutton = findViewById(R.id.languagebutton);
        languagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String type = spinnerctrl.getSelectedItem().toString(); //type from spinner
                if (type.equals("English")) {
                    setLocale("en");
                } else if (type.equals("Spanish")) {
                    setLocale("es");
                } else if (type.equals("French")) {
                    setLocale("fr");
                }

            }
        });

    }

    public void goToWelcomeActivity (View view){
        Intent intent = new Intent (this, MainActivity.class);
        File file = new File(this.getFilesDir(), ModelManagementFacade.DEFAULT_BINARY_FILE_NAME);
        ModelManagementFacade.getInstance().saveBinary(file);
        startActivity(intent);
    }

    public void goToShelterListActivity (View view) throws IOException {
        readSDFFile();
        Intent intent = new Intent (this, ShelterListActivity.class);
        startActivity(intent);
    }

    public void goToShelterMapFilterActivity (View view) throws IOException {
        Intent intent = new Intent (this, ShelterMapFilterActivity.class);
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
    }

    public void setLocale(String lang) {
        myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
//        Intent refresh = new Intent(this, LoginActivity.class);
//        startActivity(refresh);
    }

}
