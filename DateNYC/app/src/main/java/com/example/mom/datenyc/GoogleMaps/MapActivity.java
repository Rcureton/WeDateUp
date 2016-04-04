package com.example.mom.datenyc.GoogleMaps;

import android.Manifest;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;

import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.squareup.picasso.Picasso;
import com.uber.sdk.android.rides.RequestButton;
import com.uber.sdk.android.rides.RideParameters;


public class MapActivity extends AppCompatActivity {
    RequestButton requestButton;
    Button mSendEmail;
    FloatingActionButton mHome;
    TextView mName, mAddress, mRating, mFunName, mFunAddress, mFunRating, mPhone;
    private String UBER_CLIENT_ID = "BVqdBZ0dfixZGZTpblKOgrGAX5pjCEpZ";
    private int MY_LOCATION_REQUEST_CODE = 1;
    private int MY_PERMISSIONS_REQUEST_MAP_LOCATION = 2;
    private ShareActionProvider mShareActionProvider;
    String restName, restAdd, funName, funAdd, email, phone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        ImageView itineraryBackground = (ImageView) findViewById(R.id.itineraryBackground);

        Picasso.with(MapActivity.this).load("https://cdn1.vox-cdn.com/uploads/chorus_image/image/47979575/1110_Park_roof_0903_5B1_5D.0.JPG").fit().into(itineraryBackground);


        mName= (TextView)findViewById(R.id.restaurant_info_text);
        mAddress= (TextView)findViewById(R.id.restaurant_info_text_address);
        mRating= (TextView)findViewById(R.id.restaurant_info_text_rating);
        mFunName= (TextView)findViewById(R.id.fun_info_text);
        mFunAddress= (TextView)findViewById(R.id.fun_info_text_address);
        mFunRating= (TextView)findViewById(R.id.fun_info_text_rating);
        mSendEmail=(Button)findViewById(R.id.sendButton);
        mHome=(FloatingActionButton)findViewById(R.id.homeButton);
        mPhone=(TextView)findViewById(R.id.restaurant_phone);

        mSendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
//                Intent stay= new Intent(MapActivity.this,MapActivity.class);
//                startActivity(stay);
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

        restName= myDate.getRestaurant();
        restAdd= myDate.getAddress();
        funName=myDate.getFunActivity();
        funAdd= myDate.getFunAddress();
        phone= myDate.getPhoneNumber();

        mName.setText(myDate.getRestaurant());
        mAddress.setText(myDate.getAddress());
        mRating.setText(myDate.getRating());
        mFunName.setText(myDate.getFunActivity());
        mFunAddress.setText(myDate.getFunAddress());
        mFunRating.setText(myDate.getFunRating());
        mPhone.setText(phone);

        //TODO: UBER RIDE REQUEST BUTTON
        requestButton = (RequestButton) findViewById(R.id.uber);
        requestButton.setClientId(UBER_CLIENT_ID);
        RideParameters rideParams = new RideParameters.Builder()
                .setProductId("DateNYC")
                .setDropoffLocation((float) myDate.getLat(), (float) myDate.getLon(), myDate.getRestaurant(), myDate.getAddress())
                .build();
        requestButton.setRideParameters(rideParams);

    }

    //TODO: This is the code to launch the EMail Intent to the provider of the user's choice

    protected void sendEmail() {
        Log.i("Send email", "");
        String [] TO = {""};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        //TODO: To field in email isn't receiving the input of email Address

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL,TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "DateNYC Itinerary");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Thanks for planning your date with DateNYC. \n\n" +
                "Here is your Itinerary, " +"\n\n "+ restName +"\n"+ phone +"\n"+ restAdd +" \n\n"+ funName+ "\n"+ funAdd+ "\n\n "+ "Please enjoy $15 off of your first UBER Ride with us by clicking " +
                "this link https://www.uber.com/invite/DateNYC or using promo code fptg5. ");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();

        }
        catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MapActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate menu resource file.
        getMenuInflater().inflate(R.menu.share_actions, menu);

        // Locate MenuItem with ShareActionProvider
        MenuItem item = menu.findItem(R.id.menu_item_share);

        // Fetch and store ShareActionProvider
        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);

        // Return true so Android will know we want to display the menu

        Intent mShareIntent = new Intent();
        mShareIntent.setAction(Intent.ACTION_SEND);
        mShareIntent.setType("text/plain");
        mShareIntent.putExtra(Intent.EXTRA_TEXT, "I planned my date with DATENYC");
        setShareIntent(mShareIntent);
        return true;
    }

    // Call to update the share intent
    private void setShareIntent(Intent shareIntent) {
        if (mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(shareIntent);
        }
    }

}


