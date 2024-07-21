package com.example.foodislife;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class NotificationActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        textView= findViewById(R.id.notification);
        String data = getIntent().getStringExtra("data");
        textView.setText(data);
    }
}