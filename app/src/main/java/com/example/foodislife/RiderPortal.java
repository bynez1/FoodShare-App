package com.example.foodislife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RiderPortal extends AppCompatActivity {


    Adapter myAdapter;
    ArrayList<user> lists;
    EditText riderId, riderpwd;
    Button riderLogin, reject, accept;
    ValueEventListener eventListener;
    LottieAnimationView lottieRider1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rider_portal);

        riderLogin = findViewById(R.id.RiderLogin);
        riderId = findViewById(R.id.riderID);
        riderpwd = findViewById(R.id.riderPsw);


        riderLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (riderId.getText().toString().equals("RD30001") && riderpwd.getText().toString().equals("admin")) {
                    Toast.makeText(getApplicationContext(), ".... Redirecting", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getApplicationContext(), userList.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(getApplicationContext(), "Wrong Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

        //new Handler().postDelayed(new Runnable(){
           // @Override
            //public void run() {
               // Intent i = new Intent(getApplicationContext(), HomePage.class);
                //startActivity (i);
           // }
       // }, 40000);
   // }
//}