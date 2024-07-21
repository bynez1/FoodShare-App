package com.example.foodislife;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.hbb20.CountryCodePicker;

public class LoginPhoneNumberActivity extends AppCompatActivity {

    CountryCodePicker countryCodePicker;
    EditText phoneInput;
    Button sendOtpBtn;
    ProgressBar progressBar;
    TextView LogMeIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_phone_number);

        countryCodePicker = findViewById(R.id.login_countrycode);
        phoneInput = findViewById(R.id.login_mobile_number);
        sendOtpBtn = findViewById(R.id.send_otp_btn);
        progressBar = findViewById(R.id.login_progress_bar);
        LogMeIn = findViewById(R.id.logMeIn);

        progressBar.setVisibility(View.GONE);

        LogMeIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(),  SplashActivity.class);
                startActivity(intent);

            }
        });

        countryCodePicker.registerCarrierNumberEditText(phoneInput);
        sendOtpBtn.setOnClickListener((v)->{
            if(!countryCodePicker.isValidFullNumber()){
                phoneInput.setError("Phone number not valid");
                return;
            }
            Intent intent = new Intent(LoginPhoneNumberActivity.this,LoginOtpActivity.class);
            intent.putExtra("phone",countryCodePicker.getFullNumberWithPlus());
            startActivity(intent);
        });
    }



}