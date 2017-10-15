package com.datenyc.mom.datenyc.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.datenyc.mom.datenyc.MyDateItems;
import com.datenyc.mom.datenyc.R;
import com.datenyc.mom.datenyc.databinding.ActivityActivityTypeBinding;
import com.squareup.picasso.Picasso;

public class ActivityType extends AppCompatActivity {
    private ActivityActivityTypeBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_activity_type);

        setTitle("WeDateUp");

        Intent intent= getIntent();
        final MyDateItems myDate= intent.getParcelableExtra(MyDateItems.MY_ITEMS);

        Picasso.with(ActivityType.this).load("https://upload.wikimedia.org/wikipedia/commons/d/d9/Lower_Central_Park_Shot_5.JPG").fit().into(binding.parks);
        Picasso.with(ActivityType.this).load("http://1.bp.blogspot.com/-OtTVDG1EfcA/TfZn4ip_NNI/AAAAAAAAAAs/aqiGp6dY83Y/s1600/BTCHomepageImage1-HR.jpg").fit().into(binding.theater);
        Picasso.with(ActivityType.this).load("http://atasteofourcity.com/wp-content/uploads/2014/09/Improv-interior.jpg").fit().into(binding.comedy);
        Picasso.with(ActivityType.this).load("http://www.amnh.org/var/ezflow_site/storage/images/media/amnh/images/join-and-support/seats-for-science-hall-of-ocean-life/751839-4-eng-US/seats-for-science-hall-of-ocean-life_imagelarge.jpg").fit().into(binding.museum);
        Picasso.with(ActivityType.this).load("http://www.millvolvotynetheatre.co.uk/wp-content/uploads/2016/02/background.jpg").fit().into(binding.movies);
        Picasso.with(ActivityType.this).load("http://lunaparknyc.com/wp-content/uploads/2015/06/aDSC09803_DxO.jpg").fit().into(binding.amusement);
        Picasso.with(ActivityType.this).load("https://partywirks.com/asset/asset/38028/WineBeer_Couples.jpg?1448921856").fit().into(binding.paint);
        Picasso.with(ActivityType.this).load("http://www.kenricephoto.com/img/s3/v25/p322837547-3.jpg").fit().into(binding.bowling);

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

        binding.paintCard.setOnClickListener(setActivityType);
        binding.parksCard.setOnClickListener(setActivityType);
        binding.comedyCard.setOnClickListener(setActivityType);
        binding.bowlingCard.setOnClickListener(setActivityType);
        binding.theaterCard.setOnClickListener(setActivityType);
        binding.museumCard.setOnClickListener(setActivityType);
        binding.moviesCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent currentMovies= new Intent(ActivityType.this, RottenTomatoes.class);
                currentMovies.putExtra(MyDateItems.MY_ITEMS,myDate);
                startActivity(currentMovies);
            }
        });
        binding.amusementCard.setOnClickListener(setActivityType);


    }
}
