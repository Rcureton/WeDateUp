package com.datenyc.mom.datenyc.ui;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.datenyc.mom.datenyc.MyDateItems;
import com.datenyc.mom.datenyc.R;
import com.datenyc.mom.datenyc.databinding.ActivityMapBinding;
import com.datenyc.mom.datenyc.ui.launch.LaunchActivity;
import com.uber.sdk.android.rides.RideParameters;


public class ItineraryActivity extends AppCompatActivity {
    private ActivityMapBinding binding;

    private String UBER_CLIENT_ID;
    private ShareActionProvider mShareActionProvider;
    String restName, restAdd, funName, funAdd, email, phone;
    private static final String TAG = "lyft:Example";
    private static final String LYFT_PACKAGE = "me.lyft.android";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_map);
        setTitle("WeDateUp");

        binding.sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();

            }
        });

        binding.homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(ItineraryActivity.this);
                LayoutInflater inflater = ItineraryActivity.this.getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.custom_home_alert_dialog, null);
                alert.setView(dialogView);

                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent home = new Intent(ItineraryActivity.this, LaunchActivity.class);
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

        binding.restaurantInfoText.setText(myDate.getRestaurant());
        binding.restaurantInfoTextAddress.setText(myDate.getAddress());
        binding.restaurantInfoTextRating.setText(myDate.getRating());
        binding.funInfoText.setText(myDate.getFunActivity());
        binding.funInfoTextAddress.setText(myDate.getFunAddress());
        binding.funInfoTextRating.setText(myDate.getFunRating());
        binding.restaurantPhone.setText(phone);

        //TODO: UBER RIDE REQUEST BUTTON
        UBER_CLIENT_ID=getResources().getString(R.string.uber_client_id);
        binding.uber.setClientId(UBER_CLIENT_ID);
        RideParameters rideParams = new RideParameters.Builder()
                .setProductId("DateNYC")
                .setDropoffLocation((float) myDate.getLat(), (float) myDate.getLon(), myDate.getRestaurant(), myDate.getAddress())
                .build();
        binding.uber.setRideParameters(rideParams);

        binding.lyftButton.setOnClickListener(new View.OnClickListener() {
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
            Toast.makeText(ItineraryActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
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


