package com.example.mom.datenyc.YelpAPI;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.mom.datenyc.VenueTypePackage.VenueType;
import com.example.mom.datenyc.MyDateItems;
import com.example.mom.datenyc.R;
import com.squareup.picasso.Picasso;

public class LocationPage extends AppCompatActivity {

    FloatingActionButton mBk, mQu, mMa, mSi, mBx;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Location");


        ImageView backgroundTwo = (ImageView) findViewById(R.id.backgroundTwo);

        Picasso.with(LocationPage.this).load("http://blog.tourcontrastesdenuevayork.com/wp-content/uploads/2014/04/FOTO-NYC-765.jpg").fit().into(backgroundTwo);

        final Intent intent = getIntent();
        final MyDateItems myDate = intent.getParcelableExtra(MyDateItems.MY_ITEMS);
        Log.d("price", myDate.getPrice());


        View.OnClickListener setLocation = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();

                switch (id){
                    case R.id.brooklyn:
                        myDate.setLocation("Brooklyn");
                        break;
                    case R.id.bronx:
                        myDate.setLocation("Bronx,NY");
                        break;
                    case R.id.queens:
                        myDate.setLocation("Queens,NY");
                        break;
                    case R.id.staten:
                        myDate.setLocation("Staten+Island,NY");
                        break;
                    case R.id.manhattan:
                        myDate.setLocation("Manhattan,NY");
                        break;
                }
                Intent sendLocation= new Intent(LocationPage.this, VenueType.class);
                sendLocation.putExtra(MyDateItems.MY_ITEMS,myDate);
                startActivity(sendLocation);

            }
        };


        mBk = (FloatingActionButton) findViewById(R.id.brooklyn);
        mBk.setImageResource(R.drawable.bk);
        mBk.setOnClickListener(setLocation);

        mBx = (FloatingActionButton) findViewById(R.id.bronx);
        mBx.setImageResource(R.drawable.bx);
        mBx.setOnClickListener(setLocation);

        mQu = (FloatingActionButton) findViewById(R.id.queens);
        mQu.setImageResource(R.drawable.qu);
        mQu.setOnClickListener(setLocation);

        mMa = (FloatingActionButton) findViewById(R.id.manhattan);
        mMa.setImageResource(R.drawable.ma);
        mMa.setOnClickListener(setLocation);

        mSi = (FloatingActionButton) findViewById(R.id.staten);
        mSi.setImageResource(R.drawable.si);
        mSi.setOnClickListener(setLocation);


    }


}

