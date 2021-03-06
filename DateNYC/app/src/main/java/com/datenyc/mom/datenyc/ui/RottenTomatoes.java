package com.datenyc.mom.datenyc.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;

import com.datenyc.mom.datenyc.Model.Model.Adapter.MoviesCustomAdapter;
import com.datenyc.mom.datenyc.Model.Model.MovieData.Example;
import com.datenyc.mom.datenyc.Model.Model.MovieData.Result;
import com.datenyc.mom.datenyc.Model.Model.Service.MovieClient;
import com.datenyc.mom.datenyc.Model.Model.Service.RestAPI;
import com.datenyc.mom.datenyc.MyDateItems;
import com.datenyc.mom.datenyc.R;
import com.datenyc.mom.datenyc.databinding.ActivityRottenTomatoesBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RottenTomatoes extends AppCompatActivity {
    private ActivityRottenTomatoesBinding binding;

    private final static String URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=7d730c3db3895e60a51aa8c185c736bd";
    ArrayList<Result> mMovies;
    MoviesCustomAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_rotten_tomatoes);
        setTitle("Now Playing");

        Intent intent = getIntent();
        final MyDateItems myDate = intent.getParcelableExtra(MyDateItems.MY_ITEMS);

        mAdapter= new MoviesCustomAdapter(this,mMovies);
        binding.moviesListView.setAdapter(mAdapter);


        RestAPI restAPI= MovieClient.getMovieClient().create(RestAPI.class);
        Call<Example> movieCall= restAPI.getMovies(getString(R.string.movie_api_key));
        movieCall.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                Log.d("ALL", call.request().url().toString());
                mMovies=response.body().getResults();
                mAdapter.setResults(mMovies);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });


        binding.moviesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder alert = new AlertDialog.Builder(RottenTomatoes.this);
                alert.setTitle("Choose This Current Movie?");
                LayoutInflater inflater = RottenTomatoes.this.getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.custom_dialog_new_details, null);
                alert.setView(dialogView);

               final Result placeSelect = mMovies.get(position);

                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent sendFunAct = new Intent(RottenTomatoes.this, ItineraryActivity.class);
                        String media= placeSelect.getOriginalTitle();
                        Log.d("****", media);

                        myDate.setFunActivity(media);
                        myDate.setFunAddress(placeSelect.getOverview());
                        sendFunAct.putExtra(MyDateItems.MY_ITEMS, myDate);
                        startActivity(sendFunAct);

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

}
