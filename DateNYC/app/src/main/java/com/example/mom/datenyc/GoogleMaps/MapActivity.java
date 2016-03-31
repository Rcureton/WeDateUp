package com.example.mom.datenyc.GoogleMaps;

import android.Manifest;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.pdf.PdfDocument;
import android.graphics.pdf.PdfRenderer;
import android.net.Uri;
import android.os.Environment;
import android.print.pdf.PrintedPdfDocument;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mom.datenyc.GoogleMaps.Data.Result;
import com.example.mom.datenyc.MainActivity;
import com.example.mom.datenyc.MyDateItems;
import com.example.mom.datenyc.R;
import com.example.mom.datenyc.VenueTypePackage.FunActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.PlaceDetectionApi;
import com.google.android.gms.location.places.PlaceFilter;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.uber.sdk.android.rides.RequestButton;
import com.uber.sdk.android.rides.RideParameters;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

public class MapActivity extends AppCompatActivity {
    MapFragment mMapFragment;
    RequestButton requestButton;
    Button mSendEmail;
    FloatingActionButton mHome;
    TextView mName, mAddress, mRating, mFunName, mFunAddress, mFunRating;
    GoogleMap mMap;
    private String UBER_CLIENT_ID = "BVqdBZ0dfixZGZTpblKOgrGAX5pjCEpZ";
    private int MY_LOCATION_REQUEST_CODE = 1;
    GoogleApiClient mGoogleClient;
    PlaceDetectionApi mCurrentP;
    private int MY_PERMISSIONS_REQUEST_MAP_LOCATION = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        mName= (TextView)findViewById(R.id.restaurant_info_text);
        mAddress= (TextView)findViewById(R.id.restaurant_info_text_address);
        mRating= (TextView)findViewById(R.id.restaurant_info_text_rating);
        mFunName= (TextView)findViewById(R.id.fun_info_text);
        mFunAddress= (TextView)findViewById(R.id.fun_info_text_address);
        mFunRating= (TextView)findViewById(R.id.fun_info_text_rating);
        mSendEmail=(Button)findViewById(R.id.sendButton);
        mHome=(FloatingActionButton)findViewById(R.id.homeButton);

        mSendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeScreenshot();
            }
        });

        mHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(MapActivity.this);
                LayoutInflater inflater = MapActivity.this.getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.custom_home_alert_dialog, null);
                alert.setView(dialogView);

                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent home = new Intent(MapActivity.this, MainActivity.class);
                        startActivity(home);

                    }
                });
                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
                alert.show();

            }
        });


        //TODO: GETTING INTENT FROM PREVIOUS ACTIVITIES
        Intent intent = getIntent();
        MyDateItems myDate = intent.getParcelableExtra(MyDateItems.MY_ITEMS);

        mName.setText(myDate.getRestaurant());
        mAddress.setText(myDate.getAddress());
        mRating.setText(myDate.getRating());
        mFunName.setText(myDate.getFunActivity());
        mFunAddress.setText(myDate.getFunAddress());
        mFunRating.setText(myDate.getFunRating());

        //TODO: UBER RIDE REQUEST BUTTON
        requestButton = (RequestButton) findViewById(R.id.uber);
        requestButton.setClientId(UBER_CLIENT_ID);
        RideParameters rideParams = new RideParameters.Builder()
                .setProductId("DateNYC")
                .setDropoffLocation((float) myDate.getLat(), (float) myDate.getLon(), myDate.getRestaurant(), myDate.getAddress())
                .build();
        requestButton.setRideParameters(rideParams);

    }

    private void takeScreenshot() {
        Date now = new Date();
        android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);

        try {
            // image naming and path  to include sd card  appending name you choose for file
            String mPath = Environment.getExternalStorageDirectory().toString() + "/" + now + ".jpg";

            // create bitmap screen capture
            View v1 = getWindow().getDecorView().getRootView();
            v1.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
            v1.setDrawingCacheEnabled(false);

            File imageFile = new File(mPath);

            FileOutputStream outputStream = new FileOutputStream(imageFile);
            int quality = 100;
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            outputStream.flush();
            outputStream.close();

            openScreenshot(imageFile);
        } catch (Throwable e) {
            // Several error may come out with file handling or OOM
            e.printStackTrace();
        }
    }

    private void openScreenshot(File imageFile) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = Uri.fromFile(imageFile);
        intent.setDataAndType(uri, "image/*");
        startActivity(intent);
    }
}


