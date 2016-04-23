package com.datenyc.mom.datenyc.VenueTypePackage;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.datenyc.mom.datenyc.GoogleMaps.Data.Result;
import com.datenyc.mom.datenyc.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Ra on 4/1/16.
 */
public class RestDetailsAdapter extends ArrayAdapter<Result> {
    ArrayList<Result> mResults;
    String baseURL="https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=";
    String apiKey="&key=AIzaSyBujaBYaHW0oG7NYeqgKLhElZ7FkI69ffs";

    public RestDetailsAdapter(Context context, ArrayList<Result> mNewResults) {
        super(context, -1);

        mResults= new ArrayList<>();
        if(mNewResults != null){
            mResults.addAll(mNewResults);
        }
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemLayout = inflater.inflate(R.layout.rest_details_adapter, parent, false);

        ImageView placesImage= (ImageView)itemLayout.findViewById(R.id.restdetails_image);
        TextView placesName=(TextView)itemLayout.findViewById(R.id.restdetails_card_info);
        TextView placesAddress= (TextView)itemLayout.findViewById(R.id.restdetails_address);
        TextView placesPhone= (TextView)itemLayout.findViewById(R.id.restdetails_phoneNumber);
        TextView placesRating=(TextView)itemLayout.findViewById(R.id.restdetails_rating);


        Result resultItems= mResults.get(position);
        String phone =resultItems.getFormattedPhoneNumber();
        double lat= resultItems.getGeometry().getLocation().getLat();
        double lon= resultItems.getGeometry().getLocation().getLng();
        String website= resultItems.getWebsite();
        Log.d("lat and long", String.valueOf(lat) + String.valueOf(lon));
        Log.e("Phone", String.valueOf(phone));

        if(resultItems.getPhotos().size()>0){
            if(resultItems.getPhotos().get(0)!=null){
                if(resultItems.getPhotos().get(0).getPhotoReference()!=null){
                    String photoRef= resultItems.getPhotos().get(0).getPhotoReference();
//                    Log.d("photos", photoRef);
                    String finalPhoto=baseURL+photoRef+apiKey;
                    Picasso.with(parent.getContext()).load(finalPhoto).into(placesImage);
                }

            }
        }

        placesRating.setText(resultItems.getReviews().get(0).getText());
        placesName.setText(resultItems.getName());
        placesAddress.setText(resultItems.getFormattedAddress());
        placesPhone.setText(resultItems.getFormattedPhoneNumber());
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
