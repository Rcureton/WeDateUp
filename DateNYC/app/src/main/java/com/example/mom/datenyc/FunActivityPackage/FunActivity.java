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


    }
}
