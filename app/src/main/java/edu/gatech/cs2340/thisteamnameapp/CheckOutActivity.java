package edu.gatech.cs2340.thisteamnameapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class CheckOutActivity extends AppCompatActivity {

    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "id";

    /**
     * The dummy content this fragment is presenting.
     */
    private Shelter mItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ModelManagementFacade umf = ModelManagementFacade.getInstance();
        setContentView(R.layout.activity_check_out);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Model m = Model.getInstance();
        //User u = m.getCurrentUser();
        //String name = u.getUserid();

        final Spinner beds = (Spinner) findViewById(R.id.checkOutSpinner);
        String[] arr = {"0", "1", "2", "3", "4", "5", "6"};
        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, arr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        beds.setAdapter(adapter);


        int id = getIntent().getIntExtra("id", 1000);
        mItem = umf.getShelterByID(id);

        TextView vacancy = findViewById(R.id.vacancies);
        vacancy.setText("Current Vacancies: " + mItem.getVacancy());

        TextView prompt = findViewById(R.id.prompt);
        prompt.setText("How many beds would you like to reserve?");

        Button myButton = findViewById(R.id.checkout_button);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num = Integer.parseInt((String) beds.getSelectedItem());
                boolean m = mItem.updateVacancy(num);
                if (!m) {
                    Toast toast = Toast.makeText(getApplicationContext(), "unable to check out this room" , Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "reserved rooms" , Toast.LENGTH_SHORT);
                    toast.show();
                    Intent intent = new Intent (CheckOutActivity.this, ShelterListActivity.class);
                    startActivity(intent);
                }


            }
        });

    }

}
