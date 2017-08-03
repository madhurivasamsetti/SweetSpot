package com.example.vasam.sweetspot.fragments;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.vasam.sweetspot.R;

public class DetailStepsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_imgredients);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container,new DetailStepsFragment()).commit();
    }
}
