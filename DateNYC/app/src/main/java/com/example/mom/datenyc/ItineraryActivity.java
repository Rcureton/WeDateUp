package com.example.mom.datenyc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

import com.squareup.picasso.Picasso;


public class ItineraryActivity extends AppCompatActivity {

    static String businessName = "";
    static double rating = 0.0;
    ImageView mBackground;
    ListView mItineraryList;


    private final String consumerKey = "biD2L5UtLWUB3aYjEegIyw";
    private final String consumerSecret = "RKwhAlFerfB2NwdFG9_9SAE7p3Y";
    private final String token = "I_tC-YrL1nA4QfK7NFPJrIIrqqoGodNz";
    private final String tokenSecret = "eua-2OsRU8dnbK7P7YyDdGLuKJ0";

    String loadUrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itinerary);
        setTitle("Completed Date");

        mItineraryList = (ListView) findViewById(R.id.itineraryList);
        mBackground = (ImageView) findViewById(R.id.itineraryBackground);
        Picasso.with(ItineraryActivity.this).load("http://www.cultivatingculture.com/wp-content/uploads/2013/07/shutterstock_104202650-copy.jpg").fit().into(mBackground);


        Intent intent = getIntent();
        final MyDateItems myDate = intent.getParcelableExtra(MyDateItems.MY_ITEMS);


    }
}