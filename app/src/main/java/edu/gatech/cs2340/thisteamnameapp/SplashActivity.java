package edu.gatech.cs2340.thisteamnameapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * creates splash screen
 *
 */
@SuppressWarnings("CyclicClassDependency")
public class SplashActivity extends AppCompatActivity {

    /**
     * creates splash screen
     * @param savedInstanceState instance when splash screen created
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}