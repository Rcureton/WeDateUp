package com.example.mom.datenyc.VenueTypePackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mom.datenyc.R;
import com.squareup.picasso.Picasso;
import com.yelp.clientlib.entities.Business;

import java.util.ArrayList;

/**
 * Created by Ra on 3/22/16.
 */
public class CustomAdapter extends ArrayAdapter<Business> {
    ArrayList<Business> mItems;
    FunActivity funHelper;

    public CustomAdapter(Context context,  ArrayList<Business> newPlaces) {
        super(context, -1);

        mItems= new ArrayList<Business>();
        mItems= newPlaces;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemLayout = inflater.inflate(R.layout.funactivity_layout, parent, false);

        ImageView funThumbnail= (ImageView)itemLayout.findViewById(R.id.funImage);
        TextView funTitle=(TextView)itemLayout.findViewById(R.id.funName);


        Business currentActivty= mItems.get(position);

        Picasso.with(parent.getContext()).load(currentActivty.imageUrl()).into(funThumbnail);

        funTitle.setText(currentActivty.name());


        return itemLayout;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }
}
