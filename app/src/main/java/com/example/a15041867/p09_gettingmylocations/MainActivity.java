package com.example.a15041867.p09_gettingmylocations;

import android.Manifest;
import android.content.Intent;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

public class MainActivity extends AppCompatActivity  {
//    implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener
    TextView tvLat,tvLong;
    Button btnStart,btnStop,btnCheck;
    private GoogleApiClient mGoogleApiClient;
    private Location mLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvLat = (TextView)findViewById(R.id.textViewLat);
        tvLong = (TextView)findViewById(R.id.textViewLong);
        btnCheck = (Button)findViewById(R.id.btnCheck);
        btnStart = (Button)findViewById(R.id.btnStart);
        btnStop = (Button)findViewById(R.id.btnStop);

//        mGoogleApiClient = new GoogleApiClient.Builder(this)
//                .addConnectionCallbacks(this)
//                .addOnConnectionFailedListener(this)
//                .addApi(LocationServices.API)
//                .build();

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, MyService.class);
                startService(i);
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, MyService.class);
                stopService(i);
            }
        });
    }
//    @Override
//    public void onConnected(@Nullable Bundle bundle) {
//        int permissionCheck_Coarse = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION);
//        int permissionCheck_Fine = ContextCompat.checkSelfPermission(
//                MainActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION);
//
//
//        if (permissionCheck_Coarse == PermissionChecker.PERMISSION_GRANTED
//                ||  permissionCheck_Fine  == PermissionChecker.PERMISSION_GRANTED){
//            mLocation = LocationServices.FusedLocationApi.getLastLocation(
//                    mGoogleApiClient);
//
//            LocationRequest mLocationRequest = LocationRequest.create();
//            mLocationRequest.setPriority(LocationRequest
//                    .PRIORITY_BALANCED_POWER_ACCURACY);
//            mLocationRequest.setInterval(10000);
//            mLocationRequest.setFastestInterval(5000);
//            mLocationRequest.setSmallestDisplacement(100);
//            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient,
//                    mLocationRequest, this);
//
//        } else {
//            mLocation = null;
//            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},2);
//            Toast.makeText(MainActivity.this,
//                    "Permission not granted to retrieve location info",
//                    Toast.LENGTH_SHORT).show();
//        }
//
//        if (mLocation != null) {
//            tvLat.setText("Latitude: "+ mLocation.getLatitude());
//            tvLong.setText("Longtitude: "+ mLocation.getLongitude());
////            Toast.makeText(this, "Lat : " + mLocation.getLatitude() +
////                            " Lng : " + mLocation.getLongitude(),
////                    Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(this, "Location not Detected",
//                    Toast.LENGTH_SHORT).show();
//        }
//    }
//
//
//    @Override
//    public void onLocationChanged(Location location) {
//        //the detected location is given by the variable location in the signature
//        tvLat.setText("Latitude: "+ mLocation.getLatitude());
//        tvLong.setText("Longtitude: "+ mLocation.getLongitude());
////        Toast.makeText(this, "Lat : " + location.getLatitude() + " Lng : " +
////                location.getLongitude(), Toast.LENGTH_SHORT).show();
//    }
//
//
//    @Override
//    public void onConnectionSuspended(int i) {
//
//    }
//
//    @Override
//    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
//
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        mGoogleApiClient.connect();
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        if(mGoogleApiClient.isConnected()){
//            mGoogleApiClient.disconnect();
//        }
//    }
}
