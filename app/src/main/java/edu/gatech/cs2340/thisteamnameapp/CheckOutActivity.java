package edu.gatech.cs2340.thisteamnameapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.view.MenuItem;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


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
    private static Context mContext;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = getApplicationContext();

        ModelManagementFacade umf = ModelManagementFacade.getInstance();
        setContentView(R.layout.activity_check_out);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Spinner beds = findViewById(R.id.checkOutSpinner);
        String[] arr = {"1", "2", "3", "4", "5", "6"};
        ArrayAdapter<String> adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, arr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        beds.setAdapter(adapter);


        int id = getIntent().getIntExtra("id", 1000);
        mItem = umf.getShelterByID(id);

        TextView vacancy = findViewById(R.id.vacancies);
        vacancy.setText(mContext.getString(R.string.currentvacancies) + mItem.getVacancy());

        TextView prompt = findViewById(R.id.prompt);
        prompt.setText(mContext.getString(R.string.prompttextreserve));

        Button myButton = findViewById(R.id.checkout_button);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num = Integer.parseInt((String) beds.getSelectedItem());
                boolean m = mItem.updateVacancy(num);
                if (!m) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            mContext.getString(R.string.unabletocheckout) , Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            mContext.getString(R.string.reservedrooms) , Toast.LENGTH_SHORT);
                    toast.show();
                    Intent intent = new Intent (CheckOutActivity.this,
                            ShelterListActivity.class);
                    startActivity(intent);
                }


            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        Intent intent = new Intent(this, ShelterListActivity.class);
        startActivity(intent);

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        // close search view on back button pressed
        Intent intent = new Intent(this, ApplicationActivity.class);
        startActivity(intent);
    }

    public static Context getContext(){
        return mContext;
    }

}
