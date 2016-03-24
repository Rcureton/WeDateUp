package com.example.mom.datenyc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yelp.clientlib.entities.Business;

import java.util.ArrayList;

/**
 * Created by Ra on 3/23/16.
 */
public class ItineraryCustomAdapter extends ArrayAdapter<Business> {
    ArrayList<Business> mItineraryItems;


    public ItineraryCustomAdapter(Context context, ArrayList<Business> newRest) {
        super(context, -1);

        mItineraryItems= new ArrayList<Business>();
        mItineraryItems= newRest;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemLayout = inflater.inflate(R.layout.itinerary_adapter_layout, parent, false);

        ImageView restThumbnail= (ImageView)itemLayout.findViewById(R.id.itineraryImage);
        TextView restTitle=(TextView)itemLayout.findViewById(R.id.itineraryRestName);


        Business currentActivty= mItineraryItems.get(position);

        Picasso.with(parent.getContext()).load(currentActivty.imageUrl()).into(restThumbnail);

        restTitle.setText(currentActivty.name());


        return itemLayout;
    }

    @Override
    public int getCount() {
        return mItineraryItems.size();
    }
}
