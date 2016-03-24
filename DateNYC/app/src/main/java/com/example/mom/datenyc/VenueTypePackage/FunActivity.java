package com.example.mom.datenyc.VenueTypePackage;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mom.datenyc.ItineraryActivity;
import com.example.mom.datenyc.MyDateItems;
import com.example.mom.datenyc.R;
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

public class FunActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

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
        setContentView(R.layout.activity_fun);
        setTitle("Choose Activity");

        mList=(ListView)findViewById(R.id.listView);
        YelpAPIFactory apiFactory = new YelpAPIFactory(consumerKey, consumerSecret, token, tokenSecret);
        yelpAPI = apiFactory.createAPI();

        Intent intent= getIntent();
        final MyDateItems myDate= intent.getParcelableExtra(MyDateItems.MY_ITEMS);

        final ArrayList<Business> funPlaces= new ArrayList<>();

        final CustomAdapter customAdapter= new CustomAdapter(this,funPlaces);
        mList.setAdapter(customAdapter);

        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ProgressBar pb = (ProgressBar) findViewById(R.id.pbLoading);
                pb.setVisibility(ProgressBar.VISIBLE);

                AlertDialog.Builder alert = new AlertDialog.Builder(FunActivity.this);
                    alert.setTitle(myDate.getFunActivity());
                    WebView wv = new WebView(FunActivity.this);

                final Business business = funPlaces.get(position);

                    wv.setWebViewClient(new MyWebViewClient());
                    wv.getSettings().setJavaScriptEnabled(true);
                    wv.loadUrl(business.url());
                    alert.setView(wv);
                    alert.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int id){
                            Intent sendFun= new Intent(FunActivity.this, ItineraryActivity.class);
                            myDate.setFunActivity(business.name());
                            sendFun.putExtra(MyDateItems.MY_ITEMS,myDate);
                            startActivity(sendFun);

                        }
                    });
                    alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int id){

                        }
                    });
                    alert.show();

            }
        });

        Map<String, String> params = new HashMap<>();

        // general params
        params.put("term", "activities");
        params.put("limit", "15");


        Callback<SearchResponse> callback = new Callback<SearchResponse>() {
            @Override
            public void onResponse(Response<SearchResponse> response, Retrofit retrofit) {
                SearchResponse searchResponse = response.body();
                // Update UI text with the searchResponse.
                for(int i= 0; i<searchResponse.businesses().size();i++){
//                    names.add(searchResponse.businesses().get(i).name());

                    loadUrl = searchResponse.businesses().get(i).url();

                    funPlaces.add(searchResponse.businesses().get(i));
                }
                customAdapter.notifyDataSetChanged();
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            return false;
        }
    }

}
