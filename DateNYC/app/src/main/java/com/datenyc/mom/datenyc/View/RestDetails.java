package com.datenyc.mom.datenyc.View;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
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

import com.datenyc.mom.datenyc.MyDateItems;
import com.datenyc.mom.datenyc.R;
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

import butterknife.Bind;
import butterknife.ButterKnife;

public class RestDetails extends AppCompatActivity {

    String BASE_URL="https://maps.googleapis.com/maps/api/place/details/json?key=AIzaSyBujaBYaHW0oG7NYeqgKLhElZ7FkI69ffs&placeid=";
    ListView mDetailsList;
    ProgressBar pb;
    Button mButton;
    RestAsyncTask mRestAsync;
    TextView placeName, mAddress, mPhone, mReview;
    ArrayList<String> mReviewsList;
    ArrayAdapter<String> mReviewAdapter;
    String phone;
    ImageView mBackground;
    String website;

    @Bind(R.id.website)TextView mWebText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_details);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Intent intent= getIntent();
        final MyDateItems myDate= intent.getParcelableExtra(MyDateItems.MY_ITEMS);

        final String location= myDate.getLocation();

        mBackground=(ImageView)findViewById(R.id.restDetailsBackground);
        mReview=(TextView)findViewById(R.id.reviewText);
        mButton= (Button)findViewById(R.id.button2);

        setTitle(myDate.getRestaurant());


        Picasso.with(RestDetails.this).load("http://novayorkevoce.com/wp-content/uploads/2014/09/the-chester-nova-york-e-voce-3.jpg").fit().into(mBackground);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder= new AlertDialog.Builder(RestDetails.this);
                builder.setTitle(myDate.getRestaurant());
                LayoutInflater inflater = RestDetails.this.getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.custom_dialog_new_details, null);
                builder.setView(dialogView);

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent goToActivity= new Intent(RestDetails.this, ActivityType.class);
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
                website= me.optString("website");

                mWebText.setText(website);

                mReviewsList= new ArrayList<>();

                JSONArray array= me.getJSONArray("reviews");

                for(int i= 0; i< array.length();i++){
                    JSONObject object= array.optJSONObject(i);
                    String review= object.optString("text");

                    mReviewsList.add(review);

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
