package com.datenyc.mom.datenyc.VenueTypePackage;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.datenyc.mom.datenyc.GoogleAdapter;
import com.datenyc.mom.datenyc.GoogleMaps.Data.Result;
import com.datenyc.mom.datenyc.GoogleMaps.MapActivity;
import com.datenyc.mom.datenyc.MyDateItems;
import com.datenyc.mom.datenyc.OnScrollListener;
import com.datenyc.mom.datenyc.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jpardogo.android.googleprogressbar.library.GoogleProgressBar;

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

import butterknife.Bind;
import butterknife.ButterKnife;


public class FunActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

   GoogleAdapter mAdapter;
    @Bind(R.id.google_progress)GoogleProgressBar mProgress;
    ListView mList;
    ArrayList<Result> mPlaces;
    GoogleAsyncTask mGoogleAsync;
    private int pageCount = 0;
    String PAGE_TOKEN;
    String SECOND_CALL;

    String BASE_URL="https://maps.googleapis.com/maps/api/place/textsearch/json?key=AIzaSyBujaBYaHW0oG7NYeqgKLhElZ7FkI69ffs&query=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fun);
        setTitle("Choose Activity");
        ButterKnife.bind(this);

        mList = (ListView) findViewById(R.id.listView);


        Intent intent = getIntent();
        final MyDateItems myDate = intent.getParcelableExtra(MyDateItems.MY_ITEMS);

        String location= "&location="+myDate.getLat()+","+myDate.getLon()+"&radius=16100";

        String googleRequest = null;
        try {
            googleRequest = BASE_URL+myDate.getFormattedFunType()+"+in+"+location;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        mGoogleAsync= new GoogleAsyncTask();
        mGoogleAsync.execute(googleRequest);

        mAdapter= new GoogleAdapter(this, mPlaces);
        mList.setAdapter(mAdapter);

        mList.setOnScrollListener(onScrollListener());
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                AlertDialog.Builder alert = new AlertDialog.Builder(FunActivity.this);
                alert.setTitle(myDate.getFunActivity());
                LayoutInflater inflater = FunActivity.this.getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.custom_dialog_new_details, null);
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

    private void getNewData(String url){
        SECOND_CALL=BASE_URL+"&pagetoken="+PAGE_TOKEN;
        new GoogleAsyncTask().execute(SECOND_CALL);
    }

    private OnScrollListener onScrollListener() {
        return new OnScrollListener(20) {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                int threshold = 1;
                int count = mList.getCount();

                if (scrollState == SCROLL_STATE_IDLE) {
                    if (mList.getLastVisiblePosition() >= count - threshold && pageCount < 2) {
                        // Execute LoadMoreDataTask AsyncTask
                        getNewData(SECOND_CALL);
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                                 int totalItemCount) {
            }

        };
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
            mProgress.setVisibility(GoogleProgressBar.VISIBLE);

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

            mProgress.setVisibility(GoogleProgressBar.GONE);

            super.onPostExecute(s);

            try {
                JSONObject dataObject = new JSONObject(data);
                JSONArray placesArray = dataObject.getJSONArray("results");
                PAGE_TOKEN= dataObject.optString("next_page_token");
                if(mPlaces ==null|| mPlaces.isEmpty()){
                    mPlaces= new ArrayList<>();
                }

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
