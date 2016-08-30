package com.datenyc.mom.datenyc.Model.Model.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.datenyc.mom.datenyc.Model.Model.Data.Result;
import com.datenyc.mom.datenyc.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Ra on 3/29/16.
 */
public class GoogleAdapter extends ArrayAdapter<Result> {
    ArrayList<Result> mResults;
    Context appContext;

    String baseURL="https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=";
    String apiKey="&key=AIzaSyBujaBYaHW0oG7NYeqgKLhElZ7FkI69ffs";

    public GoogleAdapter(Context context, ArrayList<Result> newResults) {
        super(context, -1);

        mResults= new ArrayList<>();
        if(newResults != null){
            mResults.addAll(newResults);
        }

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemLayout = inflater.inflate(R.layout.google_places_adapter_layout, parent, false);

        ImageView placesImage= (ImageView)itemLayout.findViewById(R.id.google_card_image);
        TextView placesName=(TextView)itemLayout.findViewById(R.id.google_place_info);
        TextView placesAddress= (TextView)itemLayout.findViewById(R.id.google_address);
        TextView placesRating=(TextView)itemLayout.findViewById(R.id.googleRating);

        Result resultItems= mResults.get(position);
        String phone =resultItems.getFormattedPhoneNumber();
        double lat= resultItems.getGeometry().getLocation().getLat();
        double lon= resultItems.getGeometry().getLocation().getLng();


        if(resultItems.getPhotos().size()>0){
            if(resultItems.getPhotos().get(0)!=null){
                if(resultItems.getPhotos().get(0).getPhotoReference()!=null){
                    String photoRef= resultItems.getPhotos().get(0).getPhotoReference();
//                    Log.d("photos", photoRef);
                    String finalPhoto=baseURL+photoRef+apiKey;
                    Picasso.with(parent.getContext()).load(finalPhoto).into(placesImage);
                }else{
                    Picasso.with(parent.getContext()).load("https://randolphmase.files.wordpress.com/2014/10/tavern-on-the-green-2014.jpg").into(placesImage);

                }

            }
        }
        placesName.setText(resultItems.getName());
        placesAddress.setText(resultItems.getFormattedAddress());
        placesRating.setText(String.valueOf(resultItems.getRating()));

     return itemLayout;
    }

    @Override
    public int getCount() {
        return mResults.size();
    }

    public void setResults(ArrayList<Result> results) {
        mResults.clear();
        if(results != null){
            mResults.addAll(results);
        }
    }
}
