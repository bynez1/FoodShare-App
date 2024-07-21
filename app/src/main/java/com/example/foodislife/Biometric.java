package com.example.foodislife;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.api.Context;

import java.util.concurrent.Executor;

public class Biometric extends AppCompatActivity {
   androidx.biometric.BiometricPrompt biometricPrompt;
    BiometricPrompt.PromptInfo promptInfo;
    ConstraintLayout mLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biometric);




        BiometricManager biometricManager = BiometricManager.from(this);
        switch (biometricManager.canAuthenticate()){
            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                Toast.makeText(getApplicationContext(), "Your device doesn't have fingerprint", Toast.LENGTH_SHORT).show();
        break;

            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                Toast.makeText(getApplicationContext(), "Not working", Toast.LENGTH_SHORT).show();
        break;
            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                Toast.makeText(getApplicationContext(), " No fingerprint assigned  ", Toast.LENGTH_SHORT).show();
        break;
        }
        Executor executor = ContextCompat.getMainExecutor(this);

        biometricPrompt = new BiometricPrompt(this, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Biometric.this, userList.class);
                startActivity(intent);
                //mLayout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
            }
        });

        promptInfo = new BiometricPrompt.PromptInfo.Builder().setTitle("FoodShare")
                .setDescription("Use FingerPrint to Login").setDeviceCredentialAllowed(true).build();

        biometricPrompt.authenticate(promptInfo);
    }
}