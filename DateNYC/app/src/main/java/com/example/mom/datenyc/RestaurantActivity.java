package com.example.mom.datenyc;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
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
import android.widget.TextView;

import com.example.mom.datenyc.GoogleMaps.Data.Result;
import com.example.mom.datenyc.GoogleMaps.remote.GooglePlacesAPI;
import com.example.mom.datenyc.VenueTypePackage.FunActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestaurantActivity extends AppCompatActivity {

    private final String consumerKey = "biD2L5UtLWUB3aYjEegIyw";
    private final String consumerSecret = "RKwhAlFerfB2NwdFG9_9SAE7p3Y";
    private final String token = "I_tC-YrL1nA4QfK7NFPJrIIrqqoGodNz";
    private final String tokenSecret = "eua-2OsRU8dnbK7P7YyDdGLuKJ0";
    GoogleAdapter mGoogleAdapter;
    ListView mList;
    String url= "https://maps.googleapis.com/maps/api/place/textsearch/json?key=AIzaSyBujaBYaHW0oG7NYeqgKLhElZ7FkI69ffs&query=best%20Bars%2Bin%2Bbrooklyn";

    String BASE_URL="https://maps.googleapis.com/maps/api/place/textsearch/json?key=AIzaSyBujaBYaHW0oG7NYeqgKLhElZ7FkI69ffs&query=";
    ArrayList<Result> mPlaces;

    private GoogleAsyncTask mGoogleAsync;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        setTitle("Choose Restaurant");


        mList=(ListView)findViewById(R.id.restaurantlistView);

        Intent intent= getIntent();
        final MyDateItems myDate= intent.getParcelableExtra(MyDateItems.MY_ITEMS);

        mGoogleAdapter= new GoogleAdapter(this,mPlaces);
        mList.setAdapter(mGoogleAdapter);


        String googleRequest= null;
        try {
            googleRequest = BASE_URL+myDate.getFormattedCuisine()+"+in+"+myDate.getLocation();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        mGoogleAsync= new GoogleAsyncTask();
        GoogleAsyncTask googleAsyncTask= new GoogleAsyncTask();
        googleAsyncTask.execute(googleRequest);


        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder alert = new AlertDialog.Builder(RestaurantActivity.this);
                alert.setTitle(myDate.getVenueType());
                WebView wv = new WebView(RestaurantActivity.this);

                final Result placeSelect= mPlaces.get(position);
                String urlGoogle= placeSelect.getWebsite();


                wv.setWebViewClient(new MyWebViewClient());
                wv.getSettings().setJavaScriptEnabled(true);
                wv.loadUrl(urlGoogle);
                alert.setView(wv);
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent sendRest = new Intent(RestaurantActivity.this, FunActivity.class);
                        myDate.setRestaurant(placeSelect.getName());
                        sendRest.putExtra(MyDateItems.MY_ITEMS, myDate);
                        startActivity(sendRest);

                    }
                });
                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
                alert.show();

            }
        });
    }
    private String getInputData(InputStream inputStream) throws IOException {
        StringBuilder stringBuilder= new StringBuilder();
        BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(inputStream));
        String data;

        while ((data=bufferedReader.readLine()) !=null){
            stringBuilder.append(data);
        }
        bufferedReader.close();

        return stringBuilder.toString();
    }

    public class GoogleAsyncTask extends AsyncTask<String,Void,String> {
        String data= " ";

        @Override
        protected String doInBackground(String... urls) {

            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream inputStream = connection.getInputStream();
                data = getInputData(inputStream);
            } catch (Throwable thr) {
                thr.fillInStackTrace();
            }
            return data;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                JSONObject dataObject = new JSONObject(data);
                JSONArray placesArray = dataObject.getJSONArray("results");
                 mPlaces= new ArrayList<>();

                for (int i = 0; i < placesArray.length(); i++) {
                    JSONObject object = placesArray.optJSONObject(i);
                    String title = object.optString("name");

                    Log.d("name",title);

                    Gson gson = new GsonBuilder().create();
                    Result place= gson.fromJson(String.valueOf(placesArray.get(i)), Result.class);

                    mPlaces.add(place);

                }
            } catch (JSONException e) {

                e.printStackTrace();
            }
            mGoogleAdapter.setResults(mPlaces);
            mGoogleAdapter.notifyDataSetChanged();
        }
    }




    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            return false;
        }
    }



}
