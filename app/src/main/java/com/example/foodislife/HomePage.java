package com.example.foodislife;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;

import android.app.LauncherActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;


import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;


public class HomePage extends AppCompatActivity {
    CardView donate, receive, logout,  about, contact,  pickup;
    Button Login;

    TextView NameV, reg;

    LottieAnimationView lottie_rider;
    SearchView search1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        lottie_rider = findViewById(R.id.lottie1);
        donate = findViewById(R.id.donate);
        NameV = findViewById(R.id.NameView);
        search1= findViewById(R.id.search);
        search1.clearFocus();
        receive = findViewById(R.id.cardReceive);
        logout = findViewById(R.id.cardLogout);
        pickup = findViewById(R.id.pickUpRider);
        about = findViewById(R.id.cardAboutus);
        contact = findViewById(R.id.cardContact);



        search1.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterlist(newText);
                return true;
            }

            private void filterlist(String text) {



            }
        });


//passing user name from one activity to another
        String name = getIntent().getStringExtra("morris" );
        NameV.setText("Hi  " + name + "!");


        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Donor.class);
                startActivity(intent);
                                     }
                                  });
        receive.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick (View v){
                Intent intent = new Intent(getApplicationContext(), Receive.class);
                startActivity(intent);
            }
            });


        about.setOnClickListener(new View.OnClickListener()

            {
                @Override
                public void onClick (View v){
                Intent intent = new Intent(getApplicationContext(), About.class);
                startActivity(intent);
            }
            });

        pickup.setOnClickListener(new View.OnClickListener()

            {
                @Override
                public void onClick (View v){
                Intent intent = new Intent(getApplicationContext(), RiderPortal.class);
                startActivity(intent);
            }
            });
        contact.setOnClickListener(new View.OnClickListener()

            {
                @Override
                public void onClick (View v){
                Intent intent = new Intent(getApplicationContext(), Contact.class);
                startActivity(intent);
            }
            });
        logout.setOnClickListener(new View.OnClickListener()

            {
                @Override
                public void onClick (View v){
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(HomePage.this, landingpage.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
            });
        }
    }
