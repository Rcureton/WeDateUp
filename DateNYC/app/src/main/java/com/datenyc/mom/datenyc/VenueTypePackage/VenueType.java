package com.datenyc.mom.datenyc.VenueTypePackage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.util.Log;

import com.datenyc.mom.datenyc.MyDateItems;
import com.datenyc.mom.datenyc.R;
import com.datenyc.mom.datenyc.RestaurantActivity;
import com.squareup.picasso.Picasso;



public class VenueType extends AppCompatActivity {
    ImageView mBars,mBreakfast,mLunch,mCoffee,mBrunch,mDinner,mDessert,mWine;
    CardView mBarCard,mBrunchCard,mBreakfastCard,mLunchCard,mCoffeeCard,mDinnerCard, mWineCard,mDessertCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue_type);
        setTitle("Venue Type");

        mBars=(ImageView)findViewById(R.id.bars);
        mBreakfast=(ImageView)findViewById(R.id.breakfast);
        mBrunch=(ImageView)findViewById(R.id.brunch);
        mLunch=(ImageView)findViewById(R.id.lunch);
        mDinner=(ImageView)findViewById(R.id.dinner);
        mCoffee=(ImageView)findViewById(R.id.coffee);
        mDessert=(ImageView)findViewById(R.id.dessert);
        mWine=(ImageView)findViewById(R.id.winebar);

        mBarCard=(CardView)findViewById(R.id.bar_card);
        mBreakfastCard=(CardView)findViewById(R.id.break_card);
        mBrunchCard=(CardView)findViewById(R.id.brunch_card);
        mLunchCard=(CardView)findViewById(R.id.lunch_card);
        mDinnerCard=(CardView)findViewById(R.id.dinner_card);
        mCoffeeCard=(CardView)findViewById(R.id.coffee_card);
        mWineCard=(CardView)findViewById(R.id.wine_card);
        mDessertCard=(CardView)findViewById(R.id.dessert_card);


        Picasso.with(VenueType.this).load("https://media.timeout.com/images/103012874/image.jpg").fit().into(mBars);
        Picasso.with(VenueType.this).load("http://www.ignant.de/wp-content/uploads/2013/03/DT64com_ignant_nyc-19-von-361.jpg").fit().into(mBreakfast);
        Picasso.with(VenueType.this).load("http://nyctalon.com/wp-content/uploads/2015/09/Catch-NYC-Rooftop-Brunch.jpg").fit().into(mBrunch);
        Picasso.with(VenueType.this).load("http://www.travelgoat.com/sites/default/files/temp_images_for_spot/ny_delmonicos_historic_downtown_23_445.jpg").fit().into(mLunch);
        Picasso.with(VenueType.this).load("https://theviewgoldcoast.files.wordpress.com/2014/05/new-york-hotel-restaurant-asiate-dining.jpg").fit().into(mDinner);
        Picasso.with(VenueType.this).load("https://pbs.twimg.com/media/CcoVkbeW0AELQjU.jpg").fit().into(mDessert);
        Picasso.with(VenueType.this).load("http://www.restaurantgirl.com/assets_c/other/Krieger4.jpg").fit().into(mWine);
        Picasso.with(VenueType.this).load("http://hgtvhome.sndimg.com/content/dam/images/hgtv/fullset/2014/6/18/2/BP_HFXUP103H_70s-Redress_dining-area-detail-113143-278651_h.jpg.rend.hgtvcom.1280.960.jpeg").fit().into(mCoffee);

        Intent intent= getIntent();
        final MyDateItems myDate= intent.getParcelableExtra(MyDateItems.MY_ITEMS);




        View.OnClickListener setVenue= new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                int id = v.getId();

                switch (id){
                    case R.id.break_card:
                        myDate.setVenueType("Breakfast");
                        break;
                    case R.id.brunch_card:
                        myDate.setVenueType("Brunch");
                        break;
                    case R.id.lunch_card:
                        myDate.setVenueType("Lunch");
                        break;
                    case R.id.dinner_card:
                        myDate.setVenueType("Dinner");
                        break;
                }
                Intent sendVenue= new Intent(VenueType.this,CuisineActivity.class);
                sendVenue.putExtra(MyDateItems.MY_ITEMS,myDate);
                startActivity(sendVenue);
            }
        };
        mBarCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendBar= new Intent(VenueType.this,RestaurantActivity.class);
                myDate.setCuisine("Bars");
                sendBar.putExtra(MyDateItems.MY_ITEMS,myDate);
                startActivity(sendBar);

            }
        });
        mDessertCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendDessert= new Intent(VenueType.this,RestaurantActivity.class);
                myDate.setCuisine("best Bakeries");
                sendDessert.putExtra(MyDateItems.MY_ITEMS, myDate);

                startActivity(sendDessert);

            }
        });
        mWineCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendWine= new Intent(VenueType.this,RestaurantActivity.class);
                myDate.setCuisine("best wine bars");
                sendWine.putExtra(MyDateItems.MY_ITEMS, myDate);

                startActivity(sendWine);

            }
        });
        mCoffeeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendCoffee= new Intent(VenueType.this,RestaurantActivity.class);
                myDate.setCuisine("Coffee shops");
                sendCoffee.putExtra(MyDateItems.MY_ITEMS, myDate);

                startActivity(sendCoffee);
            }
        });

        mBreakfastCard.setOnClickListener(setVenue);
        mBrunchCard.setOnClickListener(setVenue);
        mLunchCard.setOnClickListener(setVenue);
        mDinnerCard.setOnClickListener(setVenue);
    }

}
