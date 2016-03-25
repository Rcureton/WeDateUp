package com.example.mom.datenyc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

public class RestaurantActivity extends AppCompatActivity {

    private final String consumerKey = "biD2L5UtLWUB3aYjEegIyw";
    private final String consumerSecret = "RKwhAlFerfB2NwdFG9_9SAE7p3Y";
    private final String token = "I_tC-YrL1nA4QfK7NFPJrIIrqqoGodNz";
    private final String tokenSecret = "eua-2OsRU8dnbK7P7YyDdGLuKJ0";
    static ArrayList<Business> mBusinesses;
    ArrayAdapter<String> mAdapter;
    List<String> names;
    ListView mList;
    String loadUrl;

    YelpAPI yelpAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        setTitle("Choose Restaurant");

        mList=(ListView)findViewById(R.id.restaurantlistView);
        YelpAPIFactory apiFactory = new YelpAPIFactory(consumerKey, consumerSecret, token, tokenSecret);
        yelpAPI = apiFactory.createAPI();

        Intent intent= getIntent();
        final MyDateItems myDate= intent.getParcelableExtra(MyDateItems.MY_ITEMS);

        mBusinesses= new ArrayList<>();

        final RestaurantAdapter restaurantAdapter = new RestaurantAdapter(this,mBusinesses);
        mList.setAdapter(restaurantAdapter);


        Map<String, String> params = new HashMap<>();

        // general params
        params.put("term",myDate.getCuisine());
        params.put("limit", "15");
        params.put("sort","0");
        params.put("category_filter", "food");
//        params.put("actionlinks","true");


        Callback<SearchResponse> callback = new Callback<SearchResponse>() {
            @Override
            public void onResponse(Response<SearchResponse> response, Retrofit retrofit) {
                SearchResponse searchResponse = response.body();
                // Update UI text with the searchResponse.
                for(int i= 0; i<searchResponse.businesses().size();i++){
//                    names.add(searchResponse.businesses().get(i).name());

                    loadUrl = searchResponse.businesses().get(i).url();

                    mBusinesses.add(searchResponse.businesses().get(i));
//                    mBusinesses.get(0).location().coordinate().latitude();
                }
                restaurantAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Throwable t) {
                // HTTP error happened, do something to handle it.
            }

        };
        //TODO: NEED TO GET THE PARCEABLE EXTRA LOCATION AND PASS IT INTO THE CALL
        //TODO: YELP DOESN'T ALLOW TO CALL BY PRICE.

        Call<SearchResponse> call = yelpAPI.search(myDate.getLocation(), params);
        call.enqueue(callback);

    }
}
