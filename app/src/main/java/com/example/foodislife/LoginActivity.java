package com.example.foodislife;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;


public class LoginActivity extends AppCompatActivity {

    EditText User_mail;
    EditText pass_word;
    Button Login;
    TextView Signup;

    FirebaseFirestore fstore;
    String userID;
    FirebaseAuth fAuth;
    LottieAnimationView location;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

User_mail= findViewById(R.id.Usermail);
pass_word= findViewById(R.id.Password);
Login= findViewById(R.id.login);
Signup = findViewById(R.id.Idsign);
location = findViewById(R.id.lottielocation);




        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

 Signup.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View v) {
         Intent intent =new Intent(getApplicationContext(),  Register.class);
         startActivity(intent);

     }
 });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String email, password;

                email = User_mail.getText().toString().trim();
                password = pass_word.getText().toString().trim();

                //Intent intent =new Intent(LoginActivity.this, HomePage.class);
                //intent.putExtra("morris", email);
                //startActivity(intent);

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Email is Required", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Password is Required", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.length() < 6){

                    pass_word.setError ("Password must be >= 6 Character");
                    return ;
                }

                fAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), cardviewDesign.class));
                                    finish();
                                } else {
                                    Toast.makeText(getApplicationContext(), "failed" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }

        });

    }

}

