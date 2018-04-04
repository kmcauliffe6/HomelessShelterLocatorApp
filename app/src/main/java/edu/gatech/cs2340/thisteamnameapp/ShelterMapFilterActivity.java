package edu.gatech.cs2340.thisteamnameapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class ShelterMapFilterActivity extends AppCompatActivity{
    private static final String TAG = MainActivity.class.getSimpleName();
    private List<Shelter> shelterList;
    private RecyclerView recyclerView;
    private ShelterAdapter mAdapter;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter_map_filter);

        final Spinner filterSpinner = findViewById(R.id.filterspinner);
        String[] arr = {"Anyone", " Male", "Female", "Children", "Family/Newborn", "Young Adults"};
        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, arr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filterSpinner.setAdapter(adapter);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.toolbar_title);

        shelterList = new ArrayList<>();
        getShelters();

        //whiteNotificationBar(recyclerView);


        Button filterButton = findViewById(R.id.filterButton);
        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String filterBy = filterSpinner.getSelectedItem().toString(); //find a way to select type from spinner
                Intent intent = new Intent (ShelterMapFilterActivity.this, ShelterMapActivity.class);
                intent.putExtra("Filter By", filterBy);
                startActivity(intent);

            }
        });

    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, ApplicationActivity.class);
        startActivity(intent);
    }

    private void getShelters() {
        ModelManagementFacade m = ModelManagementFacade.getInstance();
        //System.out.println(m.getShelterList());
        shelterList = m.getShelterList();
    }


}
