package com.datenyc.mom.datenyc;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.security.Permission;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.start)
    Button mStart;
    @Bind(R.id.background)
    ImageView mBackground;
    int MY_GPS_PERMISSIONS;
    @Bind(R.id.testButton)
    Button mGPS;
    @Bind(R.id.slogan)
    TextView mSlogan;

    LocationManager locationManager;
    LocationListener locationListener;
    double lon;
    double lat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Picasso.with(MainActivity.this).load("http://realestate-report.com/media/33.jpg").fit().into(mBackground);

//        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        locationListener = new LocationListener() {
//            @Override
//            public void onLocationChanged(Location location) {
//            mSlogan.append("\n "+ location.getLatitude() + " " +location.getLongitude());
//                lon= location.getLongitude();
//                lat= location.getLatitude();
//            }
//
//            @Override
//            public void onStatusChanged(String provider, int status, Bundle extras) {
//
//            }
//
//            @Override
//            public void onProviderEnabled(String provider) {
//
//            }
//
//            @Override
//            public void onProviderDisabled(String provider) {
//            Intent intent= new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//                startActivity(intent);
//            }
//        };
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
//                    Manifest.permission.ACCESS_FINE_LOCATION,
//                    Manifest.permission.ACCESS_COARSE_LOCATION,
//                    Manifest.permission.INTERNET
//            }, 10 );
//
//            return;
//
//        }else{
//            configureButton();
//
//        }
//        final MyDateItems myDate= new MyDateItems();
//            myDate.setLat(lat);
//            myDate.setLon(lon);
        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BudgetActivity.class);
//                intent.putExtra(MyDateItems.MY_ITEMS, myDate);
                startActivity(intent);
            }
        });

    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        switch (requestCode){
//            case 10:
//                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
//                    configureButton();
//        }
//    }
//
//    private void configureButton() {
//        mGPS.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                locationManager.requestLocationUpdates("gps", 5000, 0, locationListener);
//
//            }
//        });
//
//    }


}
