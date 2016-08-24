package com.datenyc.mom.datenyc;

import android.content.Context;
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

import com.datenyc.mom.datenyc.GoogleMaps.Data.Model;
import com.datenyc.mom.datenyc.GoogleMaps.Data.Result;
import com.datenyc.mom.datenyc.Retrofit.ApiClient;
import com.datenyc.mom.datenyc.Retrofit.RestAPI;
import com.datenyc.mom.datenyc.VenueTypePackage.RestDetails;
import com.datenyc.mom.datenyc.VenueTypePackage.RestDetailsAdapter;
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
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RestaurantActivity extends AppCompatActivity {

    GoogleAdapter mGoogleAdapter;
    ArrayList<Result> mPlaces;
    String PAGE_TOKEN;
    String SECOND_CALL;
    String googleRequest;
    @Bind(R.id.google_progress)GoogleProgressBar mProgressGoogle;
    @Bind(R.id.restaurantlistView)ListView mList;
//    private GoogleAsyncTask mGoogleAsync;
    Context context;
    private int pageCount = 0;
    String TAG= MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        setTitle("Choose Restaurant");
        ButterKnife.bind(this);

        if(mPlaces ==null|| mPlaces.isEmpty()){
            mPlaces= new ArrayList<>();
        }
        mGoogleAdapter= new GoogleAdapter(this,mPlaces);
        context=this;

        Intent intent= getIntent();
        final MyDateItems myDate= intent.getParcelableExtra(MyDateItems.MY_ITEMS);

        String price= "&minprice="+myDate.getPrice();
        String location= String.valueOf(+myDate.getLat()+","+myDate.getLon());


        RestAPI restAPI= ApiClient.getClient().create(RestAPI.class);
        Observable<Model> restCall= restAPI.getResults(getString(R.string.places_api_key),myDate.getCuisine(),price,location,"8100");
        restCall.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Model>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                    Log.e("MESSAGE",e.getMessage());
                    }

                    @Override
                    public void onNext(Model result) {
                        List<Result> results= result.getResults();
                        PAGE_TOKEN= result.getPageToken();
                        Log.d("PAGE", PAGE_TOKEN);
                        mList.setAdapter(new GoogleAdapter(context,results));
                        mGoogleAdapter.setResults(results);
                        mGoogleAdapter.notifyDataSetChanged();

                    }
                });


        mList.setOnScrollListener(onScrollListener());

        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder alert = new AlertDialog.Builder(RestaurantActivity.this);
                alert.setTitle(myDate.getRestaurant());
                LayoutInflater inflater = RestaurantActivity.this.getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.custom_alert_dialog, null);
                alert.setView(dialogView);

                final Result placeSelect = mPlaces.get(position);

                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent sendRest = new Intent(RestaurantActivity.this, RestDetails.class);
                        myDate.setRestaurant(placeSelect.getName());
                        myDate.setAddress(placeSelect.getFormattedAddress());
                        myDate.setRating(String.valueOf(placeSelect.getRating()));
                        myDate.setLat(placeSelect.getGeometry().getLocation().getLat());
                        myDate.setLon(placeSelect.getGeometry().getLocation().getLng());
                        myDate.setPlaceId(placeSelect.getPlaceId());
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
    private void getNewData(){
        RestAPI restAPI= ApiClient.getClient().create(RestAPI.class);
        Call<Model> second= restAPI.getNextPage(getString(R.string.places_api_key),PAGE_TOKEN);
        second.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                Log.d("CALL", call.request().url().toString());
                List<Result> nextItems= response.body().getResults();
                mPlaces.add(nextItems);
                //Need to fix the call
                mGoogleAdapter.setResults(mPlaces);
                mGoogleAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {

            }
        });

    }

    private OnScrollListener onScrollListener() {
        return new OnScrollListener(20) {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                int threshold = 1;
                int count = mList.getCount();

                if (scrollState == SCROLL_STATE_IDLE) {
                    if (mList.getLastVisiblePosition() >= count - threshold && pageCount < 2) {
                        Log.i(TAG, "loading more data");
                        // Execute LoadMoreDataTask AsyncTask
                        getNewData();
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                                 int totalItemCount) {
            }

        };
    }


}
