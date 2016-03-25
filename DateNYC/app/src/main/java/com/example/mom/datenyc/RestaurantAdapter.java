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
 * Created by Ra on 3/24/16.
 */
public class RestaurantAdapter extends ArrayAdapter<Business> {
    ArrayList<Business> mBusinessItems;

    public RestaurantAdapter(Context context, ArrayList<Business> newBusiness) {
        super(context, -1);

        mBusinessItems= new ArrayList<Business>();
        mBusinessItems= newBusiness;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemLayout = inflater.inflate(R.layout.restaurant_adapter_layout, parent, false);

        ImageView restThumbnail= (ImageView)itemLayout.findViewById(R.id.card_image);
        TextView restTitle=(TextView)itemLayout.findViewById(R.id.card_info);


        Business currentActivty= mBusinessItems.get(position);

        Picasso.with(parent.getContext()).load(currentActivty.imageUrl()).into(restThumbnail);

        restTitle.setText(currentActivty.name());


        return itemLayout;
    }

    @Override
    public int getCount() {
        return mBusinessItems.size();
    }
}
