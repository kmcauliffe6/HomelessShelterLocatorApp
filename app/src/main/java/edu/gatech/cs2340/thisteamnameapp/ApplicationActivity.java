package edu.gatech.cs2340.thisteamnameapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
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
@SuppressWarnings("CyclicClassDependency")
public class ApplicationActivity extends AppCompatActivity {

    private Spinner spinnerctrl;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spinnerctrl = (Spinner) findViewById(R.id.languagespinner);
        String[] arr = {"English", "Spanish", "French"};
        final ArrayAdapter<String> adapter =
                new ArrayAdapter(this, android.R.layout.simple_spinner_item, arr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerctrl.setAdapter(adapter);

        Button filterButton = findViewById(R.id.languagebutton);
        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String type = spinnerctrl.getSelectedItem().toString();//type from spinner
                if ("English".equals(type)) {
                    setLocale("en");
                } else if ("Spanish".equals(type)) {
                    setLocale("es");
                } else if ("French".equals(type)) {
                    setLocale("fr");
                }

            }
        });

    }
    /**
     * saves state and goes to MainActivity
     * @param view the current view
     */
    public void goToWelcomeActivity (View view){
        Intent intent = new Intent (this, MainActivity.class);
        File file = new File(this.getFilesDir(), ModelManagementFacade.DEFAULT_BINARY_FILE_NAME);
        ModelManagementFacade instance  = ModelManagementFacade.getInstance();
        instance.saveBinary(file);
        startActivity(intent);
    }
    /**
     * reads in Shelter csv file and goes to ShelterListActivity
     * @param view the current view
     *
     */
    public void goToShelterListActivity (View view) {
        readSDFFile();
        Intent intent = new Intent (this, ShelterListActivity.class);
        startActivity(intent);
    }
    /**
     * goes to ShelterMapActivity
     * @param view the current view
     */
    public void goToShelterMapFilterActivity (View view) {
        Intent intent = new Intent (this, ShelterMapFilterActivity.class);
        startActivity(intent);
    }

    private void readSDFFile() {
        ModelManagementFacade model = ModelManagementFacade.getInstance();
        try {
            //Open a stream on the raw file
            InputStream is = getResources().openRawResource(R.raw.shelters);
            model.setUpShelterList(is);
            //From here we probably should call a model method and pass the InputStream
            //Wrap it in a BufferedReader so that we get the readLine() method
        }catch (IOException e) {
            Log.d("error", "IOException thrown");
        }
    }

    private void setLocale(String lang) {
        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        //noinspection deprecation
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
//        Intent refresh = new Intent(this, ApplicationActivity.class);
//        startActivity(refresh);
    }

}
