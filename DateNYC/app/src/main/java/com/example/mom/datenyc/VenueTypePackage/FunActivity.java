package com.example.mom.datenyc.VenueTypePackage;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.mom.datenyc.GoogleAdapter;
import com.example.mom.datenyc.GoogleMaps.Data.Result;
import com.example.mom.datenyc.GoogleMaps.MapActivity;
import com.example.mom.datenyc.MyDateItems;
import com.example.mom.datenyc.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class FunActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

   GoogleAdapter mAdapter;
    ProgressBar pb;
    ListView mList;
     String loadUrl;
    ArrayList<Result> mPlaces;
    GoogleAsyncTask mGoogleAsync;

    String BASE_URL="https://maps.googleapis.com/maps/api/place/textsearch/json?key=AIzaSyBujaBYaHW0oG7NYeqgKLhElZ7FkI69ffs&query=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fun);
        setTitle("Choose Activity");

        mList = (ListView) findViewById(R.id.listView);
        pb = (ProgressBar) findViewById(R.id.pbLoading);

        Intent intent = getIntent();
        final MyDateItems myDate = intent.getParcelableExtra(MyDateItems.MY_ITEMS);

        String fun="fun+attractions";

        String googleRequest = BASE_URL+fun+"+in+"+myDate.getLocation();

        mGoogleAsync= new GoogleAsyncTask();
        mGoogleAsync.execute(googleRequest);

        mAdapter= new GoogleAdapter(this, mPlaces);
        mList.setAdapter(mAdapter);

        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                AlertDialog.Builder alert = new AlertDialog.Builder(FunActivity.this);
                alert.setTitle(myDate.getFunActivity());
                LayoutInflater inflater = FunActivity.this.getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.custom_alert_dialog, null);
                alert.setView(dialogView);

                final Result placeSelect = mPlaces.get(position);

                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent sendFun = new Intent(FunActivity.this, MapActivity.class);
                        myDate.setFunActivity(placeSelect.getName());
                        myDate.setFunAddress(placeSelect.getFormattedAddress());
                        myDate.setFunRating(String.valueOf(placeSelect.getRating()));
                        sendFun.putExtra(MyDateItems.MY_ITEMS, myDate);
                        startActivity(sendFun);

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
        protected void onPreExecute() {

            pb.setVisibility(ProgressBar.VISIBLE);
            super.onPreExecute();
        }

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
            pb.setVisibility(ProgressBar.GONE);

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
            mAdapter.setResults(mPlaces);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }


}
