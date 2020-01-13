//package com.example.lasthope.util;
//
//
//
//import android.app.Activity;
//
//
//import android.location.Location;
//
//
//import android.util.Log;
//
//import com.google.android.gms.location.FusedLocationProviderClient;
//import com.google.android.gms.location.LocationCallback;
//import com.google.android.gms.location.LocationRequest;
//import com.google.android.gms.location.LocationResult;
//import com.google.android.gms.location.LocationServices;
//import com.google.android.gms.tasks.Task;
//
//import java.util.List;
//
//Class LocationHandler(){
//
//private synchronized void buildGoogleApiClient(){
//        Log.i("TAG","Building GoogleApiClient");
//        mGoogleApiClient=new GoogleApiClient.Builder(getActivity().getApplicationContext())
//        .addConnectionCallbacks(this)
//        .addOnConnectionFailedListener(this)
//        .addApi(LocationServices.API)
//        .build();
//        createLocationRequest();}
//
//private void createLocationRequest(){
//        Log.i("TAG","CreateLocationRequest");
//        mLocationRequest=new LocationRequest();
//        long UPDATE_INTERVAL=10*1000;
//        mLocationRequest.setInterval(UPDATE_INTERVAL);
//        long FASTEST_INTERVAL=10000;
//        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);
//        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
//        builder=new LocationSettingsRequest.Builder()
//        .addLocationRequest(mLocationRequest);
//        //**************************
//        builder.setAlwaysShow(true); //this is the key ingredient
//        //**************************
//
//        }
//
//private void startLocationUpdates(){
//
//        Log.i("TAG","StartLocationUpdates");
//
//        if(Build.VERSION.SDK_INT>=23){
//        if(ContextCompat.checkSelfPermission(getActivity().getApplicationContext(),Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED&&ContextCompat.checkSelfPermission(getActivity().getApplicationContext(),Manifest.permission.ACCESS_COARSE_LOCATION)==PackageManager.PERMISSION_GRANTED){
//        // TODO: Consider calling
//        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient,
//        mLocationRequest,this);
//
//        }
//        }else{
//        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient,
//        mLocationRequest,this);
//        }
//
//        }
//
//private void stopLocationUpdates(){
//        Log.i("TAG","StopLocationUpdates");
//        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient,this);
//
//        }
//@Override
//public void onConnectionSuspended(int i){
//        Log.i("TAG","onConnectionSuspended");
//        if(i==CAUSE_SERVICE_DISCONNECTED){
//        Toast.makeText(getActivity().getApplicationContext(),"Disconnected. Please re-connect.",Toast.LENGTH_SHORT).show();
//        }else if(i==CAUSE_NETWORK_LOST){
//        Toast.makeText(getActivity().getApplicationContext(),"Network lost. Please re-connect.",Toast.LENGTH_SHORT).show();
//        }
//        mGoogleApiClient.connect();
//        }
//
//@Override
//public void onLocationChanged(Location location){
//        Log.i("TAG","OnLocationChanged");
//        Log.i("TAG","Current Location==>"+location);
//        currentlatitude=location.getLatitude();
//        currentlongitude=location.getLongitude();
//        }
//
//
//@Override
//public void onConnectionFailed(ConnectionResult connectionResult){
//        if(connectionResult.hasResolution()){
//        try{
//        // Start an Activity that tries to resolve the error
//        connectionResult.startResolutionForResult(getActivity(),connectionResult.RESOLUTION_REQUIRED);
//        }catch(IntentSender.SendIntentException e){
//        e.printStackTrace();
//        }
//        }else{
//        Log.e("TAG","Location services connection failed with code==>"+connectionResult.getErrorCode());
//        Log.e("TAG","Location services connection failed Because of==> "+connectionResult.getErrorMessage());
//        }
//
//        }
//
//@Override
//public void onDestroy(){
//        super.onDestroy();
//        if(mGoogleApiClient!=null)
//        mGoogleApiClient.disconnect();
//        }
//@Override
//public void onConnected(Bundle bundle){
//        Location mCurrentLocation;
//
//        Log.i("TAG","OnConnected");
//        if(Build.VERSION.SDK_INT>=23){
//        if(ContextCompat.checkSelfPermission(getActivity().getApplicationContext(),Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED&&ContextCompat.checkSelfPermission(getActivity().getApplicationContext(),Manifest.permission.ACCESS_COARSE_LOCATION)==PackageManager.PERMISSION_GRANTED){
//        // TODO: Consider calling
//        mCurrentLocation=LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
//
//        // Note that this can be NULL if last location isn't already known.
//        if(mCurrentLocation!=null){
//        // Print current location if not null
//        Log.d("DEBUG","current location: "+mCurrentLocation.toString());
//        currentlatitude=mCurrentLocation.getLatitude();
//        currentlongitude=mCurrentLocation.getLongitude();
//        }else{
//        startLocationUpdates();
//        }
//
//
//        }
//        }else{
//        mCurrentLocation=LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
//
//        // Note that this can be NULL if last location isn't already known.
//        if(mCurrentLocation!=null){
//        // Print current location if not null
//        Log.d("DEBUG","current location: "+mCurrentLocation.toString());
//        currentlatitude=mCurrentLocation.getLatitude();
//        currentlongitude=mCurrentLocation.getLongitude();
//        }
//        // Begin polling for new location updates.
//        startLocationUpdates();
//        }
//        }
//        }