package com.example.ahmet.turkishwords.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ahmet.turkishwords.R;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        getSupportActionBar().hide();
    }
}
