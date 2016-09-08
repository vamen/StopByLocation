package com.vivekbalachandra.android.stopbylocation;

import android.*;
import android.Manifest;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;


public class MainActivity extends AppCompatActivity {
   String TAG=MainActivity.class.getSimpleName();
    LocationManager lm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get Location service manager
        lm= (LocationManager) getSystemService(Context.LOCATION_SERVICE);


        //google place api Api that suggest user about the locations
        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {

                Log.i(TAG, "Place: " + place.getName());
                //check for LOCATION permission at runtime of android sdk 23 and above
                if (ContextCompat.checkSelfPermission(getBaseContext(),android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                        || ContextCompat.checkSelfPermission(getBaseContext(),android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                    Intent intent=new Intent(MainActivity.this,NotificationService.class);
                    PendingIntent pn=PendingIntent.getService(getBaseContext(),100,intent,PendingIntent.FLAG_CANCEL_CURRENT);
                    lm.addProximityAlert(place.getLatLng().latitude,place.getLatLng().longitude,1000,-1,pn);
                }

            }

            @Override
            public void onError(Status status) {

                Log.i(TAG, "An error occurred: " + status);
            }
        });
    }
}