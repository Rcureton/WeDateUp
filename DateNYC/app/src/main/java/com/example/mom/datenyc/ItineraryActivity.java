package com.example.mom.datenyc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mom.datenyc.VenueTypePackage.CustomAdapter;
import com.squareup.picasso.Picasso;
import com.yelp.clientlib.connection.YelpAPI;
import com.yelp.clientlib.connection.YelpAPIFactory;
import com.yelp.clientlib.entities.Business;
import com.yelp.clientlib.entities.SearchResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class ItineraryActivity extends AppCompatActivity {
    static ArrayList<Business> mBusinesses;
    static String businessName = "";
    static double rating = 0.0;
    ImageView mBackground;
    ListView mItineraryList;


    private final String consumerKey = "biD2L5UtLWUB3aYjEegIyw";
    private final String consumerSecret = "RKwhAlFerfB2NwdFG9_9SAE7p3Y";
    private final String token = "I_tC-YrL1nA4QfK7NFPJrIIrqqoGodNz";
    private final String tokenSecret = "eua-2OsRU8dnbK7P7YyDdGLuKJ0";
    YelpAPI yelpAPI;
    String loadUrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itinerary);
        setTitle("Completed Date");

        mItineraryList=(ListView)findViewById(R.id.itineraryList);
        mBackground= (ImageView)findViewById(R.id.itineraryBackground);
        Picasso.with(ItineraryActivity.this).load("http://www.cultivatingculture.com/wp-content/uploads/2013/07/shutterstock_104202650-copy.jpg").fit().into(mBackground);


        YelpAPIFactory apiFactory = new YelpAPIFactory(consumerKey, consumerSecret, token, tokenSecret);
        yelpAPI = apiFactory.createAPI();

        Intent intent= getIntent();
        final MyDateItems myDate= intent.getParcelableExtra(MyDateItems.MY_ITEMS);

        mBusinesses= new ArrayList<>();

//        final CustomAdapter customAdapter= new CustomAdapter(this,mBusinesses);
        final ItineraryCustomAdapter customAdapter1= new ItineraryCustomAdapter(this,mBusinesses);

        mItineraryList.setAdapter(customAdapter1);


        Map<String, String> params = new HashMap<>();

        // general params
        params.put("term", "food");
        params.put("limit", "5");
        params.put("category_filter",myDate.getCuisine());


        Callback<SearchResponse> callback = new Callback<SearchResponse>() {
            @Override
            public void onResponse(Response<SearchResponse> response, Retrofit retrofit) {
                SearchResponse searchResponse = response.body();
                // Update UI text with the searchResponse.
                for(int i= 0; i<searchResponse.businesses().size();i++){
//                    names.add(searchResponse.businesses().get(i).name());

                    loadUrl = searchResponse.businesses().get(i).url();

                    mBusinesses.add(searchResponse.businesses().get(i));
                }
                customAdapter1.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Throwable t) {
                // HTTP error happened, do something to handle it.
            }

        };
        //TODO: NEED TO GET THE PARCEABLE EXTRA LOCATION AND PASS IT INTO THE CALL
        //TODO: YELP DOESN'T ALLOW TO CALL BY PRICE.

        Call<SearchResponse> call = yelpAPI.search(myDate.getCuisine(), params);
        call.enqueue(callback);

    }







}
