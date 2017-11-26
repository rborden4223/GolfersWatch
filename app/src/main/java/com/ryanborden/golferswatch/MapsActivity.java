package com.ryanborden.golferswatch;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;


import static com.google.android.gms.location.places.ui.PlacePicker.*;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    public GoogleMap mMap;
    PlacePicker.IntentBuilder builder1 = new PlacePicker.IntentBuilder();
    Context context = this;
    int PLACE_PICKER_REQUEST = 1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps2);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }





        // Add a marker in Sydney and move the camera
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Intent intent = getIntent();
        double lat = intent.getDoubleExtra("Lat",10);
        double lon = intent.getDoubleExtra("Long",10);

        Log.i("lat", String.valueOf(lat));
        LatLng userLocation = new LatLng(lat ,lon);

        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        mMap.addMarker(new MarkerOptions().position(userLocation).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(userLocation));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation,15));

        LatLngBounds boundary = new LatLngBounds(new LatLng(lat, lon), new LatLng(lat , lon));
        builder1.setLatLngBounds(boundary);
       try {
           startActivityForResult(builder1.build(this),PLACE_PICKER_REQUEST);
       }catch (Exception e ){
           return ;
       }


        }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data,this);
                //String toastmsg = String.format("Place: %s", place.getName());
                //Toast.makeText(this,toastmsg,Toast.LENGTH_LONG).show();
            }
        }
    }
}



                // Add a marker in Sydney and move the camera
