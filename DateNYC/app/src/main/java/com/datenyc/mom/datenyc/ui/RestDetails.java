package com.datenyc.mom.datenyc.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.datenyc.mom.datenyc.Model.Model.Adapter.RestDetailsAdapter;
import com.datenyc.mom.datenyc.Model.Model.Data.Model;
import com.datenyc.mom.datenyc.Model.Model.Data.Result;
import com.datenyc.mom.datenyc.Model.Model.Data.Review;
import com.datenyc.mom.datenyc.Model.Model.Service.ApiClient;
import com.datenyc.mom.datenyc.Model.Model.Service.RestAPI;
import com.datenyc.mom.datenyc.MyDateItems;
import com.datenyc.mom.datenyc.R;
import com.datenyc.mom.datenyc.databinding.ActivityRestDetailsBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestDetails extends AppCompatActivity {

    private ActivityRestDetailsBinding binding;

    ListView mDetailsList;
    Button mButton;
    TextView mReview;
    ArrayList<Result> mResults;
    ArrayList<Review> mComments;
    String phone;
    ImageView mBackground;
    RestDetailsAdapter mAdapter;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_rest_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mResults = new ArrayList<>();
        context = this;

        Intent intent = getIntent();
        final MyDateItems myDate = intent.getParcelableExtra(MyDateItems.MY_ITEMS);

        final String location = myDate.getLocation();
        mBackground = findViewById(R.id.restDetailsBackground);
        mReview = (TextView) findViewById(R.id.reviewText);
        mButton = (Button) findViewById(R.id.button2);
        mDetailsList = (ListView) findViewById(R.id.restdetails_list);

        mAdapter = new RestDetailsAdapter(this, mComments);
        mDetailsList.setAdapter(mAdapter);

        setTitle(myDate.getRestaurant());

        RestAPI restAPI = ApiClient.getClient().create(RestAPI.class);
        Call<Model> call = restAPI.getDeets(getString(R.string.places_api_key), myDate.getPlaceId());
        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                Log.d("CALL", call.request().url().toString());
                Result result = response.body().getResult();
                mComments = result.getReviews();
                mAdapter.setResults(mComments);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {

            }
        });


        Picasso.with(RestDetails.this).load("http://novayorkevoce.com/wp-content/uploads/2014/09/the-chester-nova-york-e-voce-3.jpg").fit().into(mBackground);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(RestDetails.this);
                builder.setTitle(myDate.getRestaurant());
                LayoutInflater inflater = RestDetails.this.getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.custom_dialog_new_details, null);
                builder.setView(dialogView);

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent goToActivity = new Intent(RestDetails.this, ActivityType.class);
                        myDate.setLocation(location);
                        myDate.setPhoneNumber(phone);
                        goToActivity.putExtra(MyDateItems.MY_ITEMS, myDate);
                        startActivity(goToActivity);
                    }
                });
                builder.show();
            }
        });
    }

}
