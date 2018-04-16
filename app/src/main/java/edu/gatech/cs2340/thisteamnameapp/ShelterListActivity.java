package edu.gatech.cs2340.thisteamnameapp;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.Spinner;

import android.widget.Toast;

import android.graphics.Color;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * An activity representing a list of Shelters. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ShelterDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
@SuppressWarnings("CyclicClassDependency")
public class ShelterListActivity
        extends AppCompatActivity implements ShelterAdapter.ShelterAdapterListener {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    private static final String TAG = MainActivity.class.getSimpleName();
    private List<Shelter> shelterList;
    private ShelterAdapter mAdapter;
    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = getApplicationContext();
        setContentView(R.layout.activity_shelter_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Spinner filterSpinner = findViewById(R.id.filterspinner);
        String[] arr = {mContext.getString(R.string.anyonefilter),
                mContext.getString(R.string.malefilter), mContext.getString(R.string.femalefilter),
                mContext.getString(R.string.childrenfilter),
                mContext.getString(R.string.familyfilter),
                mContext.getString(R.string.youngadultfilter)};
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filterSpinner.setAdapter(adapter);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.toolbar_title);

        RecyclerView recyclerView = findViewById(R.id.shelter_list);
        shelterList = new ArrayList<>();
        getShelters();
        mAdapter = new ShelterAdapter(this, shelterList, this);

        whiteNotificationBar(recyclerView);

        RecyclerView.LayoutManager mLayoutManager =
                new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        Button filterButton = findViewById(R.id.filterButton);
        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String type = filterSpinner.getSelectedItem().toString(); //type from spinner
                mAdapter.getFilter().filter(type);
            }
        });

        File file = new File(this.getFilesDir(), ModelManagementFacade.DEFAULT_BINARY_FILE_NAME);
        ModelManagementFacade.getInstance().saveBinary(file);

    }


    private void getShelters() {
        ModelManagementFacade m = ModelManagementFacade.getInstance();
        shelterList = m.getShelterList();
    }

    /**
     * creates options for menu
     * @param menu the menu getting options
     * @return boolean successful filter recycler
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        // listening to search query text change
        //noinspection SameReturnValue,SameReturnValue,
        // SameReturnValue,SameReturnValue,SameReturnValue,
        // SameReturnValue,SameReturnValue,SameReturnValue
        //noinspection SameReturnValue,SameReturnValue
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                mAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                mAdapter.getFilter().filter(query);
                return false;
            }
        });
        return true;
    }
    /**
     * @return current context of app
     */
    public Context getContext(){
        return mContext;
    }

    /**
     * handles action bar item clicks here
     * @param item menu item clicked
     * @return boolean if item matches one in recyclerview
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.search) {
            return true;
        }
        Intent intent = new Intent(this, ApplicationActivity.class);
        startActivity(intent);

        return super.onOptionsItemSelected(item);
    }

    /**
     * close search view on back button pressed
     *
     */
    @Override
    public void onBackPressed() {
        // close search view on back button pressed
        Intent intent = new Intent(this, ApplicationActivity.class);
        startActivity(intent);
    }

    private void whiteNotificationBar(View view) {
        int flags = view.getSystemUiVisibility();
        flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
        view.setSystemUiVisibility(flags);
        getWindow().setStatusBarColor(Color.WHITE);
    }
    @Override
    public void onShelterSelected(Shelter shelter) {
        Toast.makeText(getApplicationContext(),
                mContext.getString(R.string.selected) + shelter.getName(),
                Toast.LENGTH_LONG).show();
    }
}


