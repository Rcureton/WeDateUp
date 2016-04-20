package com.datenyc.mom.datenyc.Theatre;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.datenyc.mom.datenyc.MainActivity;
import com.datenyc.mom.datenyc.R;
import com.datenyc.mom.datenyc.Theatre.TicketmasterAPI.Embedded;
import com.datenyc.mom.datenyc.Theatre.TicketmasterAPI.Event;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TheatreActivity extends AppCompatActivity {
    @Bind(R.id.okHttp)
    TextView mJsonFeed;
    ArrayList<EventsObjects> mEvents;
    @Bind(R.id.theatreList)
    ListView mListOfEvents;
    TheatreAdapter mAdapter;


    private static final String API_KEY = "g86BUMnOTYyH7JOoAO1Anu2nqb3YjGGp";
    private static final String API_SECRET = "u87XRFm2LZ2QjIQt";
    private String urlNew = "https://app.ticketmaster.com/discovery/v1/events.json?size=20&apikey=g86BUMnOTYyH7JOoAO1Anu2nqb3YjGGp&keyword=broadway";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theatre);
        ButterKnife.bind(this);


        mAdapter = new TheatreAdapter(this, mEvents);
        mListOfEvents.setAdapter(mAdapter);

        TicketMasterAsyncTask ticketMasterAsyncTask = new TicketMasterAsyncTask();
        ticketMasterAsyncTask.execute();


    }

    public class TicketMasterAsyncTask extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... params) {

            try {
                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url(urlNew)
                        .build();

                Response response = null;
                       try{
                          response= client.newCall(request).execute();
                           String result = response.body().string();
                           JSONObject object = new JSONObject(result);
                           JSONObject array= object.getJSONObject("_embedded");
                           JSONArray real= array.getJSONArray("events");

                            for(int i=0; i<real.length(); i++){
                                JSONObject object3= real.optJSONObject(i);
                                String title= object3.optString("name");
                                Log.d("NAME", title);
                                if(mEvents ==null){
                                    mEvents= new ArrayList<>();
                                }

                                Gson gson= new GsonBuilder().create();

                                EventsObjects event= gson.fromJson(String.valueOf(real.get(i)), EventsObjects.class);
                                mEvents.add(event);
                            }

                           return result;
                       }catch (Exception e){
                           e.printStackTrace();
                       }return null;


            } catch (Exception e) {
                return null;

            }

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
//            mJsonFeed.setText(s);
//            mAdapter.setResults(mEvents);
            mAdapter.notifyDataSetChanged();
        }
    }

}
