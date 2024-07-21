package com.example.foodislife;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.GeoPoint;


import java.util.HashMap;
import java.util.Map;

public class Donor extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener {

    private GoogleMap mMap;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    LocationRequest mLocationRequest;
    private int REQUEST_CODE = 11;
    SupportMapFragment mapFragment;
    EditText mOrganization, mFoodType, mDescription, mAddress,mPhone;
    Button mSubmitBtn, acceptButton, rejectedButton;

    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    String userID;
    DatabaseReference databaseUser;
    public static final String TAG = "TAG";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor);
        mOrganization = findViewById(R.id.organization);
        mFoodType = findViewById(R.id.foodtype);
        mPhone = findViewById(R.id.phone);
        mDescription = findViewById(R.id.description);
        mAddress = findViewById(R.id.address);
        mSubmitBtn=findViewById(R.id.submit);

        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();




        databaseUser = FirebaseDatabase.getInstance().getReference().child("Donors");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference Donate = database.getReference("Donors");

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.google_map);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mapFragment.getMapAsync(this);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        buildGoogleApiClient();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled(true);
    }

    protected synchronized void buildGoogleApiClient(){
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        mLastLocation = location;
        LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());

        //MarkerOptions markerOptions1 = new MarkerOptions().position(latLng).title("You are here");
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        //mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        //mMap.addMarker(markerOptions1).showInfoWindow();

        MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("You are here");
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));
        mMap.addMarker(markerOptions).showInfoWindow();

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.TIRAMISU){
            if (ContextCompat.checkSelfPermission(Donor.this,
                    Manifest.permission.POST_NOTIFICATIONS) !=PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(Donor.this,
                        new String[]{Manifest.permission.POST_NOTIFICATIONS}, 101);
            }
        }


        mSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makenotification();

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference Donate = database.getReference("Donors");

                HashMap<String, Object> users = new HashMap<>();
                String Organization = mOrganization.getText().toString().trim();
                String foodtype = mFoodType.getText().toString().trim();
                String phone = mPhone.getText().toString().trim();
                String description = mDescription.getText().toString().trim();
                String address = mAddress.getText().toString().trim();
                String id = databaseUser.child("Donor").push().getKey();
                String type = "Donor";

                user Users = new user(Organization, foodtype, phone, description, address, id);


                if (TextUtils.isEmpty(Organization)) {
                    mOrganization.setError(" A Name is Required.");
                    return;
                }
                if (TextUtils.isEmpty(foodtype)) {
                    mFoodType.setError("Food Type is Required.");
                    return;
                }
                if (phone.length() < 10) {
                    mPhone.setError("Phone Number Must be >=10 Characters");
                    return;
                }
                if (TextUtils.isEmpty(description)) {
                    mDescription.setError("Required.");
                    return;
                }
                if (TextUtils.isEmpty(address)) {
                    mAddress.setError("Required.");
                    return;
                }

                //userID = fAuth.getCurrentUser().getUid();
                //DocumentReference documentReference = fStore.collection("donate").document(userID);
                //fstore.collection("user data");
                FirebaseDatabase database1 = FirebaseDatabase.getInstance();
                DatabaseReference Donate1 = database.getReference("Donors");

                GeoPoint geoPoint = new GeoPoint(mLastLocation.getLatitude(), mLastLocation.getLongitude());
                HashMap<String, Object> user = new HashMap<>();
                users.put("timestamp", FieldValue.serverTimestamp());
                users.put("name", Organization);
                users.put("food item", foodtype);
                users.put("phone", phone);
                users.put("description", description);
                users.put("location", geoPoint);
                users.put("userid", id);
                users.put("type", type);


                databaseUser.push().setValue(Users);
                Toast.makeText(Donor.this, "Donation Inserted", Toast.LENGTH_SHORT ).show();


            }
        });


    }


    @RequiresApi(api = Build.VERSION_CODES.S)
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mapFragment.getMapAsync(this);
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }

    }
    //Notification method when a donation has been inserted from a donor
    public void makenotification(){
        String chanelId = "CHANNEL_ID_NOTIFICATION";
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), chanelId);
        builder.setSmallIcon(R.drawable.notifications)
                .setContentTitle("Donation Alert")
                .setContentText("A donor has registered a donation")
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        Intent intent = new Intent(Donor.this, NotificationActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("data", "A Donor has Registered a Donation");


        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),
                0, intent, PendingIntent.FLAG_MUTABLE);
        builder.setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager)
                getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                NotificationChannel notificationChannel =
                        notificationManager.getNotificationChannel(chanelId);
                if ( notificationChannel== null){
                    int importance = NotificationManager.IMPORTANCE_HIGH;
                    notificationChannel = new NotificationChannel(chanelId, "Donation", importance);
                    notificationChannel.setLightColor(android.R.color.holo_blue_light);
                    notificationChannel.enableVibration(true);
                    notificationManager.createNotificationChannel(notificationChannel);
                }

            }
        notificationManager.notify(0, builder.build());

        }

    }

