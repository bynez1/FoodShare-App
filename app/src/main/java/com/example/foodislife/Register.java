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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class Register extends AppCompatActivity {

    public static final String TAG ="Tag";

    Button Sub_Button;
    TextView signin;
    EditText user_name;
    EditText user_mail;
    EditText pass_word;
    EditText Con_Password;




    FirebaseFirestore fstore;
    String userID;
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        user_mail = findViewById(R.id.Usermail);
        pass_word = findViewById(R.id.Password);
        Con_Password = findViewById(R.id.ConPassword);
        signin = findViewById(R.id.signin);
        Sub_Button = findViewById(R.id.SubButton);




        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // redirecting to login
                Intent intent = new Intent(Register.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        Sub_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // checking for a valid name

                final String email = user_mail.getText().toString().trim();
                String password = pass_word.getText().toString().trim();
                String ConPassword = Con_Password.getText().toString().trim();
                //final String Username = user_name.getText().toString().trim();

                // checking for a valid address and password
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Please Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Please Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!password.equals(ConPassword)) {
                    Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_SHORT).show();
                    return;
                }

                fAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (!task.isSuccessful()) {
                                    Toast.makeText(Register.this, "Registration failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(Register.this, "Registered Successfully.",
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Register.this,LoginActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
            }
        });
    }
}