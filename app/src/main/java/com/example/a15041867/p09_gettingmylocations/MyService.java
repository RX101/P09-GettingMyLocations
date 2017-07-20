package com.example.a15041867.p09_gettingmylocations;

import android.Manifest;
import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

public class MyService extends Service implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {
    boolean started;
    private GoogleApiClient mGoogleApiClient;
    private Location mLocation;
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("Service","Created");
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

    }

    @Override
    public int onStartCommand(Intent intent,int flags, int startId) {
        if(started == false){
            started = true;
            Log.d("Service", "Started");
            mGoogleApiClient.connect();
        }else{
            Log.d("Service","Still running");
            Toast.makeText(getApplicationContext(),"Service, Still Running",Toast.LENGTH_SHORT).show();
            mGoogleApiClient.connect();
        }
        return Service.START_STICKY;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Service","Exited");
        mGoogleApiClient.disconnect();

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        int permissionCheck_Coarse = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
        int permissionCheck_Fine = ContextCompat.checkSelfPermission(
                this, android.Manifest.permission.ACCESS_FINE_LOCATION);


        if (permissionCheck_Coarse == PermissionChecker.PERMISSION_GRANTED
                ||  permissionCheck_Fine  == PermissionChecker.PERMISSION_GRANTED){
            mLocation = LocationServices.FusedLocationApi.getLastLocation(
                    mGoogleApiClient);

            LocationRequest mLocationRequest = LocationRequest.create();
            mLocationRequest.setPriority(LocationRequest
                    .PRIORITY_BALANCED_POWER_ACCURACY);
            mLocationRequest.setInterval(10000);
            mLocationRequest.setFastestInterval(5000);
            mLocationRequest.setSmallestDisplacement(100);
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient,
                    mLocationRequest, this);

        } else {
            mLocation = null;
            Toast.makeText(this,
                    "Permission not granted to retrieve location info",
                    Toast.LENGTH_SHORT).show();
//            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},2);
        }

        if (mLocation != null) {
//            tvLat.setText("Latitude: "+ mLocation.getLatitude());
//            tvLong.setText("Longtitude: "+ mLocation.getLongitude());
            Toast.makeText(this, "Lat : " + mLocation.getLatitude() +
                            " Lng : " + mLocation.getLongitude(),
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Location not Detected",
                    Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onLocationChanged(Location location) {
        //the detected location is given by the variable location in the signature
//        tvLat.setText("Latitude: "+ mLocation.getLatitude());
//        tvLong.setText("Longtitude: "+ mLocation.getLongitude());
        Toast.makeText(this, "Lat : " + location.getLatitude() + " Lng : " +
                location.getLongitude(), Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


}
