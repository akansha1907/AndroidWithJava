package com.example.relativelayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.relativelayout.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.model.PolygonOptions;

import java.io.IOException;
import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
            mMap = googleMap;
            LatLng latLng = new LatLng(28.5801,77.3635);
            MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("Block A Sector 34");
            mMap.addMarker(markerOptions);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));      //to move focus of user at particular location without this line user's focus will be on south africa location
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,16f));  //this line is useful to zoom map to specify location

        //the value of zoom is lies between 16f to 20f

        /* to draw overlay(i.e. circle or other shape) on location*/
        mMap.addCircle(new CircleOptions()
                .center(latLng)
                .radius(500)       //1000 which is value of radius in meter which i.e. of 2km diameter
                .fillColor(R.color.map_background)            //we can also specify color like this R.color.white from our colors file but don't give direct hexcode here
                .strokeColor(Color.DKGRAY)
                .strokeWidth(1));
        //use of Geocoder and it's implementation
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull LatLng latLng) {
                mMap.addMarker(new MarkerOptions().position(latLng).title("clicked here"));
                Geocoder geocoder = new Geocoder(MapsActivity.this);
                try {
                    ArrayList<Address> addresses = (ArrayList<Address>)geocoder.getFromLocation(latLng.latitude,latLng.longitude,4);
                    Log.d("Addresses",addresses.get(0).getAddressLine(0));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
}