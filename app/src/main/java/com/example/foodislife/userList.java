package com.example.foodislife;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class userList extends AppCompatActivity implements RecyclerViewInterface{

    RecyclerView recyclerView;
    DatabaseReference database;
    Adapter adapter;
    ArrayList<user>list;



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(userList. this, Donor.class));
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        recyclerView = findViewById(R.id.userlist);
        database = FirebaseDatabase.getInstance().getReference("Donors");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this ));
        list = new ArrayList<>();
        adapter = new Adapter(this, list, this);
        recyclerView.setAdapter(adapter);


        adapter.setOnItemClickListener(new Adapter.OnItemClickListener() {
           @Override
            
            //method to remove recyclerView
            
            public void onItemClick(int position) {
                list.remove(position);
                adapter.notifyItemRemoved(position);
            }
        });



database.addValueEventListener(new ValueEventListener() {
    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {

        for (DataSnapshot dataSnapshot: snapshot.getChildren()){

            user user = dataSnapshot.getValue(com.example.foodislife.user.class);
            list.add(user);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
});


    }

    @Override
    public void onItemClick(int position) {

    }
}