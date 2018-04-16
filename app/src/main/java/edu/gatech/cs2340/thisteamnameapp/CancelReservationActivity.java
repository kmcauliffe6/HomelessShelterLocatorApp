package edu.gatech.cs2340.thisteamnameapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Cancel Reservation Activity class for User objects using shelters
 *
 */

@SuppressWarnings("CyclicClassDependency")
public class CancelReservationActivity extends AppCompatActivity {
    private Shelter mItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ModelManagementFacade umf = ModelManagementFacade.getInstance();
        setContentView(R.layout.activity_cancel_reservation);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final Spinner beds = findViewById(R.id.checkOutSpinner);
        String[] arr = {"0", "1", "2", "3", "4", "5", "6"};
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        beds.setAdapter(adapter);

        Intent it = getIntent();
        int id = it.getIntExtra("id", 1000);
        mItem = umf.getShelterByID(id);

        TextView vacancy = findViewById(R.id.vacancies);
        vacancy.setText("Current Vacancies: " + mItem.getVacancy());

        TextView prompt = findViewById(R.id.prompt);
        prompt.setText("How many beds would you like to return?");

        Button cancelButton = findViewById(R.id.cancelreservation_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num = Integer.parseInt((String) beds.getSelectedItem());
                boolean m = mItem.decreaseVacancy(num);
                if (!m) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "beds not returned" , Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "reservation canceled" , Toast.LENGTH_SHORT);
                    toast.show();
                    Intent intent = new Intent (CancelReservationActivity.this,
                            ShelterListActivity.class);
                    startActivity(intent);
                }


            }
        });
    }
    /**
     * On Back Pressed to close search view on back button pressed.
     *
     */
    @Override
    public void onBackPressed() {
        // close search view on back button pressed
        Intent intent = new Intent(this, ShelterListActivity.class);
        startActivity(intent);
    }

}
