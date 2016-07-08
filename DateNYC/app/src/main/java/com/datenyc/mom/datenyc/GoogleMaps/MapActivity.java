package com.datenyc.mom.datenyc.GoogleMaps;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.content.pm.PackageManager;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.datenyc.mom.datenyc.MainActivity;
import com.datenyc.mom.datenyc.MyDateItems;
import com.datenyc.mom.datenyc.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.squareup.picasso.Picasso;
import com.uber.sdk.android.rides.RequestButton;
import com.uber.sdk.android.rides.RideParameters;


public class MapActivity extends AppCompatActivity {
    RequestButton requestButton;
    Button mSendEmail;
    ImageButton mLyft;
    FloatingActionButton mHome;
    TextView mName, mAddress, mRating, mFunName, mFunAddress, mFunRating, mPhone;
    private String UBER_CLIENT_ID = "BVqdBZ0dfixZGZTpblKOgrGAX5pjCEpZ";
    private int MY_LOCATION_REQUEST_CODE = 1;
    private int MY_PERMISSIONS_REQUEST_MAP_LOCATION = 2;
    private ShareActionProvider mShareActionProvider;
    String restName, restAdd, funName, funAdd, email, phone;
    private static final String TAG = "lyft:Example";
    private static final String LYFT_PACKAGE = "me.lyft.android";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);


        mLyft=(ImageButton)findViewById(R.id.lyftButton);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mAdView.loadAd(adRequest);


        ImageView itineraryBackground = (ImageView) findViewById(R.id.itineraryBackground);

        Picasso.with(MapActivity.this).load("http://www.samsung.com/global/microsite/galaxycamera/mobile/images/nx/img_pic_LARGE_08.jpg").fit().into(itineraryBackground);


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

        mLyft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deepLinkIntoLyft();
            }
        });
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
                "this link https://www.uber.com/invite/DateNYC or using promo code fptg5. "+ "\n\n" + "Or if you prefer Lyft please feel free to use our Promo Code WEDATEUP "+ "\n\n " + "https://www.lyft.com/invite/WEDATEUP");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));


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

    private void deepLinkIntoLyft() {
        if (isPackageInstalled(this, LYFT_PACKAGE)) {
            //This intent will help you to launch if the package is already installed
            openLink(this, "lyft://payment?credits=WEDATEUP");

            Log.d(TAG, "Lyft is already installed on your phone, deeplinking.");
        } else {
            openLink(this, "https://play.google.com/store/apps/details?id=" + LYFT_PACKAGE);

            Log.d(TAG, "Lyft is not currently installed on your phone, opening Play Store.");
        }
    }

    static void openLink(Activity activity, String link) {
        Intent playStoreIntent = new Intent(Intent.ACTION_VIEW);
        playStoreIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        playStoreIntent.setData(Uri.parse(link));
        activity.startActivity(playStoreIntent);
    }

    static boolean isPackageInstalled(Context context, String packageId) {
        PackageManager pm = context.getPackageManager();
        try {
            pm.getPackageInfo(packageId, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            // ignored.
        }
        return false;
    }

}


