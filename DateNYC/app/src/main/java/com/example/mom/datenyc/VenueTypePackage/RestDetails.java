package com.example.mom.datenyc.VenueTypePackage;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mom.datenyc.GoogleMaps.Data.*;
import com.example.mom.datenyc.MyDateItems;
import com.example.mom.datenyc.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

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

public class RestDetails extends AppCompatActivity {

    String BASE_URL="https://maps.googleapis.com/maps/api/place/details/json?key=AIzaSyBujaBYaHW0oG7NYeqgKLhElZ7FkI69ffs&placeid=";
    ListView mDetailsList;
    ProgressBar pb;
    FloatingActionButton mFab;
    Button mButton;
    RestAsyncTask mRestAsync;
    TextView placeName, mAddress, mPhone, mReview;
    ArrayList<String> mReviewsList;
    ArrayAdapter<String> mReviewAdapter;
    String phone;
    ImageView mBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Restaurant Details");

        Intent intent= getIntent();
        final MyDateItems myDate= intent.getParcelableExtra(MyDateItems.MY_ITEMS);

        final String location= myDate.getLocation();

        mBackground=(ImageView)findViewById(R.id.restDetailsBackground);
        placeName= (TextView)findViewById(R.id.example);
        mAddress=(TextView)findViewById(R.id.formaddress);
        mPhone=(TextView)findViewById(R.id.formphone);
        mReview=(TextView)findViewById(R.id.reviewText);
        mButton= (Button)findViewById(R.id.button2);
        mFab = (FloatingActionButton) findViewById(R.id.fabDetails);

        Picasso.with(RestDetails.this).load("http://cdn.lifebuzz.com/images/3891/lifebuzz-2f3699e8b8831bcb43fbb778a69ce01f-limit_2000.jpg").fit().into(mBackground);



        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder= new AlertDialog.Builder(RestDetails.this);
                builder.setTitle(myDate.getVenueType());
                LayoutInflater inflater = RestDetails.this.getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.custom_alert_dialog, null);
                builder.setView(dialogView);

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent goToActivity= new Intent(RestDetails.this, FunActivity.class);
                        myDate.setLocation(location);
                        myDate.setPhoneNumber(phone);
                        goToActivity.putExtra(MyDateItems.MY_ITEMS,myDate);
                        startActivity(goToActivity);
                    }
                });
                builder.show();
            }
        });
        mDetailsList=(ListView)findViewById(R.id.restdetails_list);
        pb=(ProgressBar)findViewById(R.id.progress);

        String googleRequest= null;
        googleRequest = BASE_URL+myDate.getPlaceId();

        mRestAsync= new RestAsyncTask();
        RestAsyncTask restAsyncTask= new RestAsyncTask();
        restAsyncTask.execute(googleRequest);

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

    public class RestAsyncTask extends AsyncTask<String,Void,String> {
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
                JSONObject result= new JSONObject(s);
                JSONObject me= result.getJSONObject("result");
                String name= me.optString("name");
                String address= me.optString("formatted_address");
                phone= me.optString("formatted_phone_number");

                placeName.setText(name);
                mAddress.setText(address);
                mPhone.setText(phone);

                mReviewsList= new ArrayList<>();

                JSONArray array= me.getJSONArray("reviews");

                for(int i= 0; i< array.length();i++){
                    JSONObject object= array.optJSONObject(i);
                    String review= object.optString("text");

                    mReviewsList.add(review);

//                    mReview.setText(review);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            mReviewAdapter= new ArrayAdapter<String>(RestDetails.this, android.R.layout.simple_list_item_1,mReviewsList);
            mDetailsList.setAdapter(mReviewAdapter);

            mReviewAdapter.notifyDataSetChanged();

        }
    }

}
