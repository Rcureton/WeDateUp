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
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.datenyc.mom.datenyc.VenueTypePackage.VenueType;
import com.datenyc.mom.datenyc.MyDateItems;
import com.datenyc.mom.datenyc.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LocationPage extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener {

    FloatingActionButton mBk, mQu, mMa, mSi, mBx;
    LocationManager locationManager;
    LocationListener locationListener;
    @Bind(R.id.imageButton)ImageButton mLocation;
    @Bind(R.id.locationText)
    TextView mText;
    double lon;
    double lat;
    MyDateItems myDate;

    private static final String TAG= LocationPage.class.getSimpleName();
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST= 1000;
    private Location mLastLocationCoordinates;
    private GoogleApiClient mGoogleApiClient;
    private boolean mRequestLocationUpdates = false;
    private LocationRequest mLocationRequest;
    private static int UPDATE_INTERVAL= 10000;
    private static int FASTEST_INTERVAL= 5000;
    private static int DISPLACEMENT= 10;



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


//        View.OnClickListener setLocation = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int id = v.getId();
//
//                switch (id){
//                    case R.id.brooklyn:
//                        myDate.setLocation("Brooklyn");
//                        break;
//                    case R.id.bronx:
//                        myDate.setLocation("Bronx,NY");
//                        break;
//                    case R.id.queens:
//                        myDate.setLocation("Queens,NY");
//                        break;
//                    case R.id.staten:
//                        myDate.setLocation("Staten+Island,NY");
//                        break;
//                    case R.id.manhattan:
//                        myDate.setLocation("Manhattan,NY");
//                        break;
//                }
//                Intent sendLocation= new Intent(LocationPage.this, VenueType.class);
//                sendLocation.putExtra(MyDateItems.MY_ITEMS,myDate);
//                startActivity(sendLocation);
//
//            }
//        };

//
//        mBk = (FloatingActionButton) findViewById(R.id.brooklyn);
//        mBk.setImageResource(R.drawable.bk);
//        mBk.setOnClickListener(setLocation);
//
//        mBx = (FloatingActionButton) findViewById(R.id.bronx);
//        mBx.setImageResource(R.drawable.bx);
//        mBx.setOnClickListener(setLocation);
//
//        mQu = (FloatingActionButton) findViewById(R.id.queens);
//        mQu.setImageResource(R.drawable.qu);
//        mQu.setOnClickListener(setLocation);
//
//        mMa = (FloatingActionButton) findViewById(R.id.manhattan);
//        mMa.setImageResource(R.drawable.ma);
//        mMa.setOnClickListener(setLocation);
//
//        mSi = (FloatingActionButton) findViewById(R.id.staten);
//        mSi.setImageResource(R.drawable.si);
//        mSi.setOnClickListener(setLocation);


        if(checkPlayServices() ){
            buildGoogleApiClient();
            createLocationRequest();
        }
        mLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayLocation();
                Intent intent1= new Intent(LocationPage.this,VenueType.class);
                myDate.setLat(lat);
                myDate.setLon(lon);
                intent1.putExtra(MyDateItems.MY_ITEMS, myDate);
                startActivity(intent1);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mGoogleApiClient !=null){
            mGoogleApiClient.connect();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        checkPlayServices();
        if(mGoogleApiClient.isConnected() && mRequestLocationUpdates){
            startLocationUpdates();

        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mGoogleApiClient.isConnected()){
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopLocationUpdates();

    }

    private void displayLocation(){
        mLastLocationCoordinates= LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if(mLastLocationCoordinates !=null){
            lat= mLastLocationCoordinates.getLatitude();
            lon= mLastLocationCoordinates.getLongitude();

            mText.setText(lat + " " + lon);
        }else{
            mText.setText("Couldn't get the location");
        }
    }

    private void togglePeriodLocation(){
        if(!mRequestLocationUpdates){
            mRequestLocationUpdates=true;
            startLocationUpdates();
        }else{
            mRequestLocationUpdates= false;

            stopLocationUpdates();
        }
    }

    protected synchronized void buildGoogleApiClient(){
        mGoogleApiClient= new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();
    }

    protected void createLocationRequest(){
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(UPDATE_INTERVAL);
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setSmallestDisplacement(DISPLACEMENT);

    }

    private boolean checkPlayServices(){
        int resultCode= GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if(resultCode != ConnectionResult.SUCCESS){
            if(GooglePlayServicesUtil.isUserRecoverableError(resultCode)){
                GooglePlayServicesUtil.getErrorDialog(resultCode,this, PLAY_SERVICES_RESOLUTION_REQUEST).show();
            }else{
                Toast.makeText(LocationPage.this, "This device is not supported", Toast.LENGTH_SHORT).show();
                finish();
            }
            return false;
        }
        return true;
    }

    protected void startLocationUpdates(){
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
    }

    protected void stopLocationUpdates(){
        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient,this);
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
//        mLocation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                locationManager.requestLocationUpdates("gps", 5000, 0, locationListener);
//                Toast.makeText(LocationPage.this, "Currently getting your location", Toast.LENGTH_LONG).show();
//                myDate.setLon(lon);
//                myDate.setLat(lat);
//                Intent intent = new Intent(LocationPage.this, VenueType.class);
//                intent.putExtra(MyDateItems.MY_ITEMS, myDate);
//                startActivity(intent);
//
//            }
//        });

    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {

        displayLocation();
        if(mRequestLocationUpdates){
            startLocationUpdates();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        mGoogleApiClient.connect();
    }

    @Override
    public void onLocationChanged(Location location) {
        mLastLocationCoordinates= location;
        Toast.makeText(LocationPage.this, "Location Changed", Toast.LENGTH_SHORT).show();
        displayLocation();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

        Log.i(TAG,"Connection failed" + connectionResult.getErrorMessage());
    }
}

