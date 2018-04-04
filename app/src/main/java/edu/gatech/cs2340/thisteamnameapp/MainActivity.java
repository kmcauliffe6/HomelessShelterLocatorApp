package edu.gatech.cs2340.thisteamnameapp;


import android.support.v7.app.AppCompatActivity;

import android.content.Intent;



import android.os.Bundle;

import android.view.View;

import java.io.File;

/**
 * welcome screen with buttons for log-in and register activity
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        File file = new File(this.getFilesDir(), ModelManagementFacade.DEFAULT_BINARY_FILE_NAME);
        ModelManagementFacade.getInstance().loadBinary(file);
    }

    public void goToSplashActivity (View view){
        Intent intent = new Intent (this, SplashActivity.class);
        startActivity(intent);
    }
    public void goToLoginActivity (View view){
        Intent intent = new Intent (this, LoginActivity.class);
        startActivity(intent);
    }
    public void goToRegisterActivity (View view){
        Intent intent = new Intent (this, RegisterActivity.class);
        startActivity(intent);
    }

}
