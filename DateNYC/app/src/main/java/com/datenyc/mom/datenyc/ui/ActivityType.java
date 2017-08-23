package com.datenyc.mom.datenyc.ui;

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

public class ActivityType extends AppCompatActivity {
    @Bind(R.id.parks_card)CardView mParkCard;
    @Bind(R.id.comedy_card)CardView mComedyCard;
    @Bind(R.id.museum_card)CardView mMuseumCard;
    @Bind(R.id.amusement_card)CardView mAmusementCard;
    @Bind(R.id.bowling_card)CardView mBowlingCard;
    @Bind(R.id.paint_card)CardView mPaintCard;
    @Bind(R.id.theater_card)CardView mTheaterCard;
    @Bind(R.id.movies_card)CardView mMovieCard;

    @Bind(R.id.theater)ImageView mTheaterImage;
    @Bind(R.id.parks)ImageView mParksImage;
    @Bind(R.id.comedy)ImageView mComedyImage;
    @Bind(R.id.museum)ImageView mMuseumImage;
    @Bind(R.id.amusement)ImageView mAmusementImage;
    @Bind(R.id.bowling)ImageView mBowlingImage;
    @Bind(R.id.paint)ImageView mPaintImage;
    @Bind(R.id.movies)ImageView mMoviesImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_type);
        ButterKnife.bind(this);
        setTitle("WeDateUp");

        Intent intent= getIntent();
        final MyDateItems myDate= intent.getParcelableExtra(MyDateItems.MY_ITEMS);

        Picasso.with(ActivityType.this).load("https://upload.wikimedia.org/wikipedia/commons/d/d9/Lower_Central_Park_Shot_5.JPG").fit().into(mParksImage);
        Picasso.with(ActivityType.this).load("http://1.bp.blogspot.com/-OtTVDG1EfcA/TfZn4ip_NNI/AAAAAAAAAAs/aqiGp6dY83Y/s1600/BTCHomepageImage1-HR.jpg").fit().into(mTheaterImage);
        Picasso.with(ActivityType.this).load("http://atasteofourcity.com/wp-content/uploads/2014/09/Improv-interior.jpg").fit().into(mComedyImage);
        Picasso.with(ActivityType.this).load("http://www.amnh.org/var/ezflow_site/storage/images/media/amnh/images/join-and-support/seats-for-science-hall-of-ocean-life/751839-4-eng-US/seats-for-science-hall-of-ocean-life_imagelarge.jpg").fit().into(mMuseumImage);
        Picasso.with(ActivityType.this).load("http://www.millvolvotynetheatre.co.uk/wp-content/uploads/2016/02/background.jpg").fit().into(mMoviesImage);
        Picasso.with(ActivityType.this).load("http://lunaparknyc.com/wp-content/uploads/2015/06/aDSC09803_DxO.jpg").fit().into(mAmusementImage);
        Picasso.with(ActivityType.this).load("https://partywirks.com/asset/asset/38028/WineBeer_Couples.jpg?1448921856").fit().into(mPaintImage);
        Picasso.with(ActivityType.this).load("http://www.kenricephoto.com/img/s3/v25/p322837547-3.jpg").fit().into(mBowlingImage);

        View.OnClickListener setActivityType = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();

                switch (id){
                    case R.id.parks_card:
                        myDate.setFunType("Parks");
                        break;
                    case R.id.amusement_card:
                        myDate.setFunType("Amusement");
                        break;
                    case R.id.comedy_card:
                        myDate.setFunType("Comedy Club");
                        break;
                    case R.id.theater_card:
                        myDate.setFunType("Theatre");
                        break;
                    case R.id.museum_card:
                        myDate.setFunType("Museums");
                        break;
                    case R.id.movies_card:
                        myDate.setFunType("Movie Theater");
                        break;
                    case R.id.paint_card:
                        myDate.setFunType("Paint and Sip");
                        break;
                    case R.id.bowling_card:
                        myDate.setFunType("Bowling");
                        break;
                }
                Intent sendFunType= new Intent(ActivityType.this, FunActivity.class);
                sendFunType.putExtra(MyDateItems.MY_ITEMS,myDate);
                startActivity(sendFunType);
            }
        };

        mPaintCard.setOnClickListener(setActivityType);
        mParkCard.setOnClickListener(setActivityType);
        mComedyCard.setOnClickListener(setActivityType);
        mBowlingCard.setOnClickListener(setActivityType);
        mTheaterCard.setOnClickListener(setActivityType);
        mMuseumCard.setOnClickListener(setActivityType);
        mMovieCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent currentMovies= new Intent(ActivityType.this, RottenTomatoes.class);
                currentMovies.putExtra(MyDateItems.MY_ITEMS,myDate);
                startActivity(currentMovies);
            }
        });
        mAmusementCard.setOnClickListener(setActivityType);


    }
}
