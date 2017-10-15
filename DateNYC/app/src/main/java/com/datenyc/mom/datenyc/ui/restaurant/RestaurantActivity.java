package com.datenyc.mom.datenyc.ui.restaurant;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;

import com.datenyc.mom.datenyc.Model.Model.Adapter.GoogleAdapter;
import com.datenyc.mom.datenyc.Model.Model.Data.Model;
import com.datenyc.mom.datenyc.Model.Model.Data.Result;
import com.datenyc.mom.datenyc.Model.Model.Service.ApiClient;
import com.datenyc.mom.datenyc.Model.Model.Service.RestAPI;
import com.datenyc.mom.datenyc.MyDateItems;
import com.datenyc.mom.datenyc.OnScrollListener;
import com.datenyc.mom.datenyc.R;
import com.datenyc.mom.datenyc.databinding.ActivityRestaurantBinding;
import com.datenyc.mom.datenyc.ui.RestDetails;
import com.datenyc.mom.datenyc.ui.launch.LaunchActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RestaurantActivity extends AppCompatActivity {
    private ActivityRestaurantBinding binding;

    GoogleAdapter mGoogleAdapter;
    ArrayList<Result> mPlaces;
    ArrayList<Result> mResults;
    String PAGE_TOKEN;
    Context context;
    private int pageCount = 0;
    String TAG= LaunchActivity.class.getSimpleName();
    MyDateItems myDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        setTitle("Choose Restaurant");
        binding = DataBindingUtil.setContentView(this, R.layout.activity_restaurant);

        mResults= new ArrayList<>();

        mGoogleAdapter= new GoogleAdapter(this,mPlaces);
        context=this;
        binding.restaurantlistView.setAdapter(mGoogleAdapter);
        Intent intent= getIntent();
        myDate= intent.getParcelableExtra(MyDateItems.MY_ITEMS);
        getPlaces();


        binding.restaurantlistView.setOnScrollListener(onScrollListener());

        binding.restaurantlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
    private void getPlaces(){
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
                        mPlaces= result.getResults();
                        PAGE_TOKEN= result.getPageToken();
                        mGoogleAdapter.setResults(mPlaces);
                        mGoogleAdapter.notifyDataSetChanged();
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
                mResults=response.body().getResults();
                for (int i=0; i<mResults.size();i++){
                    Result result= mResults.get(i);
                    mPlaces.add(result);
                }
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
                int count = binding.restaurantlistView.getCount();

                if (scrollState == SCROLL_STATE_IDLE) {
                    if (binding.restaurantlistView.getLastVisiblePosition() >= count - threshold && pageCount < 2) {
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
