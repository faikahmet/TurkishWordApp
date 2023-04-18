package com.example.ahmet.turkishwords.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ahmet.turkishwords.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();

        new Beklet().start();
    }

    public class Beklet extends Thread{
        @Override
        public void run() {
            try{
                Thread.sleep(2000);
            }catch (Exception e){}

            startActivity(new Intent(getApplicationContext(),MenuActivity.class));
            finish();
        }
    }
}