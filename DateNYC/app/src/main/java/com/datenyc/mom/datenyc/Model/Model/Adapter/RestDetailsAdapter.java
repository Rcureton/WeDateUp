package com.datenyc.mom.datenyc.Model.Model.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.datenyc.mom.datenyc.Model.Model.Data.Result;
import com.datenyc.mom.datenyc.Model.Model.Data.Review;
import com.datenyc.mom.datenyc.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Ra on 4/1/16.
 */
public class RestDetailsAdapter extends ArrayAdapter<Review> {
    ArrayList<Result> mResults;
    ArrayList<Review> mReviews;
    String baseURL="https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=";
    String apiKey="&key=AIzaSyBujaBYaHW0oG7NYeqgKLhElZ7FkI69ffs";

    public RestDetailsAdapter(Context context, ArrayList<Review> mNewResults) {
        super(context, -1);

        mReviews= new ArrayList<>();
        if(mNewResults != null){
            mReviews.addAll(mNewResults);
        }
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemLayout = inflater.inflate(R.layout.rest_details_adapter, parent, false);

        TextView reviewUser=(TextView)itemLayout.findViewById(R.id.restdetails_author);
        TextView reviewRating= (TextView)itemLayout.findViewById(R.id.restdetails_rating);
        TextView reviewComments=(TextView)itemLayout.findViewById(R.id.restdetails_reviews);

        Review resultItems= mReviews.get(position);
        String author= resultItems.getAuthorName();
        String comment= resultItems.getText();
        int rating= resultItems.getRating();
        Log.d("AUTHOR", author);

        reviewUser.setText(author);
        reviewRating.setText(String.valueOf(rating));
        reviewComments.setText(comment);

        return itemLayout;
    }

    @Override
    public int getCount() {
        return mReviews.size();
    }

    public void setResults(ArrayList<Review> results) {
        mReviews.clear();
        if(results != null){
            mReviews.addAll(results);
        }
    }
}
