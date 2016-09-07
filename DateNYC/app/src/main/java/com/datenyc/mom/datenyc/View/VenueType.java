package com.datenyc.mom.datenyc.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;

import com.datenyc.mom.datenyc.MyDateItems;
import com.datenyc.mom.datenyc.R;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;


public class VenueType extends AppCompatActivity {


   //Imageviews
    @Bind(R.id.bars)ImageView mBars;
    @Bind(R.id.breakfast)ImageView mBreakfast;
    @Bind(R.id.brunch)ImageView mBrunch;
    @Bind(R.id.lunch) ImageView mLunch;
    @Bind(R.id.coffee)ImageView mCoffee;
    @Bind(R.id.dinner)ImageView mDinner;
    @Bind(R.id.dessert)ImageView mDessert;
    @Bind(R.id.winebar)ImageView mWine;
    //Cardviews
    @Bind(R.id.bar_card)CardView mBarCard;
    @Bind(R.id.brunch_card)CardView mBrunchCard;
    @Bind(R.id.break_card)CardView mBreakfastCard;
    @Bind(R.id.lunch_card)CardView mLunchCard;
    @Bind(R.id.coffee_card)CardView mCoffeeCard;
    @Bind(R.id.dinner_card)CardView mDinnerCard;
    @Bind(R.id.wine_card)CardView mWineCard;
    @Bind(R.id.dessert_card)CardView mDessertCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue_type);
        setTitle("Venue Type");
        ButterKnife.bind(this);


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
