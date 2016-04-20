package com.datenyc.mom.datenyc.Movies;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.datenyc.mom.datenyc.Movies.MovieData.Result;
import com.datenyc.mom.datenyc.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RottenTomatoes extends AppCompatActivity {

    private final static String URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=7d730c3db3895e60a51aa8c185c736bd";
    @Bind(R.id.moviesListView)
    ListView mMoviesList;
    @Bind(R.id.moviesTextView)TextView mText;
    ArrayList<Result> mMovies;
    MoviesCustomAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotten_tomatoes);
        ButterKnife.bind(this);


        mAdapter= new MoviesCustomAdapter(this,mMovies);
        mMoviesList.setAdapter(mAdapter);

        MoviesAsyncTask moviesAsyncTask= new MoviesAsyncTask();
        moviesAsyncTask.execute();

    }

    public class MoviesAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url(URL)
                        .build();

                Response response = null;
                try {
                    response = client.newCall(request).execute();
                    String result = response.body().string();
                    JSONObject object = new JSONObject(result);
                    JSONArray array = object.getJSONArray("results");


                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object3 = array.optJSONObject(i);
                        String title = object3.optString("original_title");
                        Log.d("NAME", title);
                        if(mMovies ==null){
                            mMovies= new ArrayList<>();
                        }

                        Gson gson= new GsonBuilder().create();
                        Result result1= gson.fromJson(String.valueOf(array.get(i)), Result.class);


                        mMovies.add(result1);
                    }


                    return result;
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            mAdapter.notifyDataSetChanged();
        }
    }
}