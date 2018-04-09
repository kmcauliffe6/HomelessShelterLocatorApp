package edu.gatech.cs2340.thisteamnameapp;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;
/**
 * activity that displays shelters on a map using Google Maps API
 */
public class ShelterMapActivity extends FragmentActivity implements OnMapReadyCallback {

    private String filterBy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        filterBy = getIntent().getStringExtra("Filter By");

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        GoogleMap mMap = googleMap;
        //noinspection MagicNumber, desired zoom for map
        int zoom = 11;

        List<Shelter> shelters = getFilteredShelters();
        for (Shelter s: shelters) {
            LatLng loc = new LatLng(Double.parseDouble(s.getLatitude()), Double.parseDouble(s.getLongitude()));
            mMap.addMarker(new MarkerOptions().position(loc).title(s.getName()).snippet("Current Vacancies: "+ s.getVacancy()));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(loc));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                    new LatLng(Double.parseDouble(s.getLatitude()), Double.parseDouble(s.getLongitude())), zoom));
        }

    }

    private List<Shelter> getFilteredShelters() {
        ModelManagementFacade umf = ModelManagementFacade.getInstance();
        List<Shelter> shelters = umf.getShelterList();
        List<Shelter> filteredShelters = new ArrayList<>();
        String[] arr = {"Anyone", " Male", "Female", "Children", "Family/Newborn", "Young Adults"};
        for (String p: arr) {
            if (filterBy.equals(p)) {
                if ("Anyone".equals(filterBy)) {
                    filteredShelters = shelters;
                } else {
                    for (Shelter s: shelters) {
                        if (s.getGender().toLowerCase().contains(filterBy.toLowerCase())) {
                            filteredShelters.add(s);
                        }
                    }
                }

            }
        }
        return filteredShelters;
    }
    @Override
    public void onBackPressed() {
        // close search view on back button pressed
        Intent intent = new Intent(this, ShelterMapFilterActivity.class);
        startActivity(intent);
    }
}
