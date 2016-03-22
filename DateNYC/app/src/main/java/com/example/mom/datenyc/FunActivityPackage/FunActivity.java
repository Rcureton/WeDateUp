package com.example.mom.datenyc.FunActivityPackage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.mom.datenyc.R;
import com.squareup.picasso.Picasso;

public class FunActivity extends AppCompatActivity {
    ImageView mBars,mBreakfast,mLunch,mCoffee,mBrunch,mDinner,mDessert,mWine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fun);

        mBars=(ImageView)findViewById(R.id.bars);
        mBreakfast=(ImageView)findViewById(R.id.breakfast);
        mBrunch=(ImageView)findViewById(R.id.brunch);
        mLunch=(ImageView)findViewById(R.id.lunch);
        mDinner=(ImageView)findViewById(R.id.dinner);
        mCoffee=(ImageView)findViewById(R.id.coffee);
        mDessert=(ImageView)findViewById(R.id.dessert);
        mWine=(ImageView)findViewById(R.id.winebar);

        Picasso.with(FunActivity.this).load("https://media.timeout.com/images/103012874/image.jpg").fit().into(mBars);
        Picasso.with(FunActivity.this).load("http://www.ignant.de/wp-content/uploads/2013/03/DT64com_ignant_nyc-19-von-361.jpg").fit().into(mBreakfast);
        Picasso.with(FunActivity.this).load("http://nyctalon.com/wp-content/uploads/2015/09/Catch-NYC-Rooftop-Brunch.jpg").fit().into(mBrunch);
        Picasso.with(FunActivity.this).load("http://www.travelgoat.com/sites/default/files/temp_images_for_spot/ny_delmonicos_historic_downtown_23_445.jpg").fit().into(mLunch);
        Picasso.with(FunActivity.this).load("https://theviewgoldcoast.files.wordpress.com/2014/05/new-york-hotel-restaurant-asiate-dining.jpg").fit().into(mDinner);
        Picasso.with(FunActivity.this).load("https://pbs.twimg.com/media/CcoVkbeW0AELQjU.jpg").fit().into(mDessert);
        Picasso.with(FunActivity.this).load("http://www.restaurantgirl.com/assets_c/other/Krieger4.jpg").fit().into(mWine);
        Picasso.with(FunActivity.this).load("http://hgtvhome.sndimg.com/content/dam/images/hgtv/fullset/2014/6/18/2/BP_HFXUP103H_70s-Redress_dining-area-detail-113143-278651_h.jpg.rend.hgtvcom.1280.960.jpeg").fit().into(mCoffee);


    }
}
