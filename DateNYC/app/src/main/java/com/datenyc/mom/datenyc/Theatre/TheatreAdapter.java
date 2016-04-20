package com.datenyc.mom.datenyc.Theatre;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.datenyc.mom.datenyc.GoogleMaps.Data.Result;
import com.datenyc.mom.datenyc.R;
import com.datenyc.mom.datenyc.Theatre.TicketmasterAPI.Event;

import java.util.ArrayList;

/**
 * Created by MOM on 4/17/16.
 */
public class TheatreAdapter extends ArrayAdapter<EventsObjects> {
    ArrayList<EventsObjects> mEvents;
    private String urlNew="https://app.ticketmaster.com/discovery/v1/events.json?size=20&apikey=g86BUMnOTYyH7JOoAO1Anu2nqb3YjGGp&keyword=broadway";

    public TheatreAdapter(Context context, ArrayList<EventsObjects> events) {
        super(context, -1);

        mEvents= new ArrayList<>();
        if(events != null){
            mEvents.addAll(events);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemLayout = inflater.inflate(R.layout.theatre_custom_adapter, parent, false);

        ImageView theatreImage= (ImageView)itemLayout.findViewById(R.id.theatre_card_image);
        TextView theatreName=(TextView)itemLayout.findViewById(R.id.theatre_place_info);
        TextView theatreAddress= (TextView)itemLayout.findViewById(R.id.theatre_address);
        TextView theatreRating=(TextView)itemLayout.findViewById(R.id.theatreRating);

        EventsObjects events= mEvents.get(position);

        String theatre= events.getName();
        String venue= events.getType();

        theatreName.setText(theatre);
        theatreAddress.setText(venue);

        return itemLayout;
    }

    @Override
    public int getCount() {
        return mEvents.size();
    }

    public void setResults(ArrayList<EventsObjects> results) {
        mEvents.clear();
        if(results != null){
            mEvents.addAll(results);
        }
    }
}
