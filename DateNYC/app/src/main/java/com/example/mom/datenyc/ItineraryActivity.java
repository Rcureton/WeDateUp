package com.example.mom.datenyc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

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
    private String businessId = "joju-elmhurst";
    static ArrayList<Business> mBusinesses;
    static String businessName = "";
    static double rating = 0.0;
    TextView textView, search;

    private final String consumerKey = "biD2L5UtLWUB3aYjEegIyw";
    private final String consumerSecret = "RKwhAlFerfB2NwdFG9_9SAE7p3Y";
    private final String token = "I_tC-YrL1nA4QfK7NFPJrIIrqqoGodNz";
    private final String tokenSecret = "eua-2OsRU8dnbK7P7YyDdGLuKJ0";
    YelpAPI yelpAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itinerary);

        textView= (TextView)findViewById(R.id.tvMessage);
        search= (TextView)findViewById(R.id.textView);

        YelpAPIFactory apiFactory = new YelpAPIFactory(consumerKey, consumerSecret, token, tokenSecret);
        yelpAPI = apiFactory.createAPI();
    }

    public void searchBusinesses(){

        Map<String, String> params = new HashMap<>();

        // general params
        params.put("term", "food");
        params.put("limit", "5");
        params.put("category_filter","bars");

        Callback<SearchResponse> callback = new Callback<SearchResponse>() {
            @Override
            public void onResponse(Response<SearchResponse> response, Retrofit retrofit) {
                SearchResponse searchResponse = response.body();
                // Update UI text with the searchResponse.
                String bName= searchResponse.businesses().get(4).name();
                List<String> names = new ArrayList<>();
                int i = 0;
                while (i < 5) {
                    names.add(searchResponse.businesses().get(i).name());
                    ++i;
                }

                int totalNumberOfResult = searchResponse.total();
                mBusinesses = searchResponse.businesses();
                String businessName = mBusinesses.get(4).name();  // "JapaCurry Truck"
                Double rating = mBusinesses.get(0).rating();  // 4.0

                search.setText(businessName+ " " +rating);
                for (Business bussiness : mBusinesses) {
                    Log.d("GOT THIS", bussiness.toString());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                // HTTP error happened, do something to handle it.
            }

        };
        Call<SearchResponse> call = yelpAPI.search("San Francisco", params);
        call.enqueue(callback);

    }


    public void getBusinesses() {

        Callback<Business> callback = new Callback<Business>() {
            @Override
            public void onResponse(Response<Business> response, Retrofit retrofit) {
                Business business = response.body();
                String name = business.name();
                double house= business.rating();
//                textView.setText(String.valueOf(house));
                textView.setText(name);
                // Update UI text with the Business object.
            }
            @Override
            public void onFailure(Throwable t) {
                // HTTP error happened, do something to handle it.
            }
        };

        Call<Business> call = yelpAPI.getBusiness(businessId);
        call.enqueue(callback);

    }

    public void businessInfo(int busNum) throws Exception
    {
        // Get name, rating, distance
        // Display one-by-one in order that Yelp returns data to us
        if(busNum >= 20) { //TODO: Dont hardcode this. init total results
            busNum = busNum % 20; //quick wrap around function
        }
        businessName = mBusinesses.get(busNum).name();
        rating = mBusinesses.get(busNum).rating();


    }

}
