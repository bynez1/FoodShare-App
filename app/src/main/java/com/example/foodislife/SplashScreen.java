package com.example.foodislife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.airbnb.lottie.LottieAnimationView;

public class SplashScreen extends AppCompatActivity {


    LottieAnimationView lottie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        lottie = findViewById(R.id.lottie);



        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this, LoginActivity.class);
               startActivity (i);
           }
       }, 2500);
    }
}