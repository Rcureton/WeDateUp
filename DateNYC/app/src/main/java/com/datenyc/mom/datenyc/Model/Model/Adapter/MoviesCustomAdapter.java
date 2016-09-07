package com.datenyc.mom.datenyc.Model.Model.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.datenyc.mom.datenyc.Model.Model.MovieData.Result;
import com.datenyc.mom.datenyc.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by MOM on 4/19/16.
 */
public class MoviesCustomAdapter extends ArrayAdapter<Result> {
    ArrayList<Result> mMovieItems;

    public MoviesCustomAdapter(Context context, ArrayList<Result> items) {
        super(context, -1);

        mMovieItems= new ArrayList<>();
        if(items != null){
            mMovieItems.addAll(items);
        }

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemLayout = inflater.inflate(R.layout.custom_movie_adapter, parent, false);

        ImageView movieImage= (ImageView)itemLayout.findViewById(R.id.movie_card_image);
        TextView movieName=(TextView)itemLayout.findViewById(R.id.movieName);
        TextView movieDescription= (TextView)itemLayout.findViewById(R.id.movieDescription);
        TextView movieRating=(TextView)itemLayout.findViewById(R.id.movieRating);

        Result movieItems = mMovieItems.get(position);

        String name= movieItems.getTitle();
        String description= movieItems.getOverview();
        double rating= movieItems.getVoteAverage();
        String image= movieItems.getBackdropPath();
        int id= movieItems.getId();


        movieName.setText(name);
        movieDescription.setText(description);
        if(image !=null){
            Picasso.with(parent.getContext()).load("http://image.tmdb.org/t/p/w500/"+image).into(movieImage);
        }else{
            Picasso.with(parent.getContext()).load("http://c3240dd96f54819fb6f2-90846526673b19d9a04c27097b58cb86.r6.cf2.rackcdn.com/2011/01/amc-theaters.jpg").into(movieImage);
        }

        movieRating.setText(String.valueOf(movieItems.getPopularity()));

        return itemLayout;
    }

    @Override
    public int getCount() {
        return mMovieItems.size();
    }

    public void setResults(ArrayList<Result> results) {
        mMovieItems.clear();
        if(results != null){
            mMovieItems.addAll(results);
        }
    }
}
