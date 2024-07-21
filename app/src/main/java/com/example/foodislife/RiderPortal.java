package com.example.foodislife;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.view.View;


import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.concurrent.Executor;

public class RiderPortal extends AppCompatActivity {


    Adapter myAdapter;
    ArrayList<user> lists;
    EditText riderId, riderpwd;
    ImageView biometric;

    Button riderLogin, reject, accept;
    ValueEventListener eventListener;
    LottieAnimationView lottieRider1;


    private BiometricPrompt getPrompt() {
        Executor executor = ContextCompat.getMainExecutor(this);
        BiometricPrompt.AuthenticationCallback callback = new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                notifyUser(errString.toString());
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                notifyUser("Authentication Succeeded!!!");
                Intent intent = new Intent(RiderPortal.this, userList.class);
                startActivity(intent);
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                notifyUser("Authentication Failed!!!");
            }
        };

        BiometricPrompt biometricPrompt = new BiometricPrompt(this, executor, callback);
        return biometricPrompt;

    }
    private void notifyUser(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();


    }
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rider_portal);

        riderLogin = findViewById(R.id.RiderLogin);
        riderId = findViewById(R.id.riderID);
        riderpwd = findViewById(R.id.riderPsw);
        biometric = findViewById(R.id.bio);


        biometric.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                    .setTitle("Please Verify Finger Scan")
                    .setDescription("Rider Authentication is Required")
                    .setNegativeButtonText("Cancel")
                    .build();

            getPrompt().authenticate(promptInfo);
            }
        });



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