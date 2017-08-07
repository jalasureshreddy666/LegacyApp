package com.legacyapp.Activities;

import android.support.v7.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Start MainActivity.class
        Intent myIntent = new Intent(SplashScreenActivity.this,
                MainActivity.class);
        startActivity(myIntent);
        finish();
    }

}
