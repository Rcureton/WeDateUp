package com.datenyc.mom.datenyc.YelpAPI;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.datenyc.mom.datenyc.VenueTypePackage.VenueType;
import com.datenyc.mom.datenyc.MyDateItems;
import com.datenyc.mom.datenyc.R;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LocationPage extends AppCompatActivity {

    FloatingActionButton mBk, mQu, mMa, mSi, mBx;
    LocationManager locationManager;
    LocationListener locationListener;
    @Bind(R.id.imageButton)ImageButton mLocation;
    double lon;
    double lat;
    MyDateItems myDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Location");
        ButterKnife.bind(this);


        ImageView backgroundTwo = (ImageView) findViewById(R.id.backgroundTwo);

        Picasso.with(LocationPage.this).load("http://blog.tourcontrastesdenuevayork.com/wp-content/uploads/2014/04/FOTO-NYC-765.jpg").fit().into(backgroundTwo);

        final Intent intent = getIntent();
        myDate = intent.getParcelableExtra(MyDateItems.MY_ITEMS);
        Log.d("price", myDate.getPrice());

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                lon= location.getLongitude();
                lat= location.getLatitude();
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {
                Intent intent= new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        };
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            ActivityCompat.requestPermissions(LocationPage.this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.INTERNET
            }, 10 );

            return;

        }else{
            configureButton();

        }


        View.OnClickListener setLocation = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();

                switch (id){
                    case R.id.brooklyn:
                        myDate.setLocation("Brooklyn");
                        break;
                    case R.id.bronx:
                        myDate.setLocation("Bronx,NY");
                        break;
                    case R.id.queens:
                        myDate.setLocation("Queens,NY");
                        break;
                    case R.id.staten:
                        myDate.setLocation("Staten+Island,NY");
                        break;
                    case R.id.manhattan:
                        myDate.setLocation("Manhattan,NY");
                        break;
                }
                Intent sendLocation= new Intent(LocationPage.this, VenueType.class);
                sendLocation.putExtra(MyDateItems.MY_ITEMS,myDate);
                startActivity(sendLocation);

            }
        };


        mBk = (FloatingActionButton) findViewById(R.id.brooklyn);
        mBk.setImageResource(R.drawable.bk);
        mBk.setOnClickListener(setLocation);

        mBx = (FloatingActionButton) findViewById(R.id.bronx);
        mBx.setImageResource(R.drawable.bx);
        mBx.setOnClickListener(setLocation);

        mQu = (FloatingActionButton) findViewById(R.id.queens);
        mQu.setImageResource(R.drawable.qu);
        mQu.setOnClickListener(setLocation);

        mMa = (FloatingActionButton) findViewById(R.id.manhattan);
        mMa.setImageResource(R.drawable.ma);
        mMa.setOnClickListener(setLocation);

        mSi = (FloatingActionButton) findViewById(R.id.staten);
        mSi.setImageResource(R.drawable.si);
        mSi.setOnClickListener(setLocation);


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 10:
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    configureButton();
        }
    }

    private void configureButton() {
        mLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationManager.requestLocationUpdates("gps", 5000, 0, locationListener);
                Toast.makeText(LocationPage.this, "Currently getting your location", Toast.LENGTH_LONG).show();
                myDate.setLon(lon);
                myDate.setLat(lat);
                Intent intent = new Intent(LocationPage.this, VenueType.class);
                intent.putExtra(MyDateItems.MY_ITEMS, myDate);
                startActivity(intent);

            }
        });

    }


}

