package com.example.mom.datenyc.VenueTypePackage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.mom.datenyc.ItineraryActivity;
import com.example.mom.datenyc.MyDateItems;
import com.example.mom.datenyc.R;
import com.example.mom.datenyc.RestaurantActivity;
import com.squareup.picasso.Picasso;

public class CuisineActivity extends AppCompatActivity {
    ImageView mAsian,mAmerican,mIndian,mItalian,mMexican,mMeditteranean,mThai,mVeagan;
    CardView mAsianCard,mAmericanCard,mIndianCard,mItalianCard,mMexcianCard,mMeditteraneanCard, mThaiCard,mVeaganCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuisine);
        setTitle("Cuisine Type");

        mAsian=(ImageView)findViewById(R.id.asian);
        mAmerican=(ImageView)findViewById(R.id.american);
        mIndian=(ImageView)findViewById(R.id.indian);
        mItalian=(ImageView)findViewById(R.id.italian);
        mMexican=(ImageView)findViewById(R.id.mexican);
        mMeditteranean=(ImageView)findViewById(R.id.meditteranean);
        mThai=(ImageView)findViewById(R.id.thai);
        mVeagan=(ImageView)findViewById(R.id.veagan);

        mAsianCard=(CardView)findViewById(R.id.asian_card);
        mAmericanCard=(CardView)findViewById(R.id.american_card);
        mIndianCard=(CardView)findViewById(R.id.indian_card);
        mItalianCard=(CardView)findViewById(R.id.italian_card);
        mMexcianCard=(CardView)findViewById(R.id.mexican_card);
        mMeditteraneanCard=(CardView)findViewById(R.id.meditteranean_card);
        mThaiCard=(CardView)findViewById(R.id.thai_card);
        mVeaganCard=(CardView)findViewById(R.id.veagan_card);


        Picasso.with(CuisineActivity.this).load("http://nebula.wsimg.com/82c2438bcec6ce077fdb9f5777483347?AccessKeyId=FB771320EF82836BDCA7&disposition=0&alloworigin=1").fit().into(mAsian);
        Picasso.with(CuisineActivity.this).load("https://upload.wikimedia.org/wikipedia/commons/7/71/Soul_Food_at_Powell's_Place.jpg").fit().into(mAmerican);
        Picasso.with(CuisineActivity.this).load("http://insidenirvana.com/wp-content/uploads/2015/08/best-indian-food-new-orleans.jpg").fit().into(mIndian);
        Picasso.with(CuisineActivity.this).load("http://blog.maggianos.com/wp-content/uploads/ChickenFontina_FPO.jpg").fit().into(mItalian);
        Picasso.with(CuisineActivity.this).load("http://theseasonedchef.com/wp-content/uploads/2013/12/Mexican-Food.jpg").fit().into(mMexican);
        Picasso.with(CuisineActivity.this).load("http://nebula.wsimg.com/7056052cf5a33e79490298ad8251d82a?AccessKeyId=A19A869FFE0BB83606FD&disposition=0&alloworigin=1").fit().into(mMeditteranean);
        Picasso.with(CuisineActivity.this).load("http://farm4.staticflickr.com/3669/10832314654_ff403fcb07_b.jpg").fit().into(mThai);
        Picasso.with(CuisineActivity.this).load("https://media.timeout.com/images/102030827/617/347/image.jpg").fit().into(mVeagan);

        Intent intent= getIntent();
        final MyDateItems myDate= intent.getParcelableExtra(MyDateItems.MY_ITEMS);
        Log.d("venue type",myDate.getVenueType());

        View.OnClickListener setCuisine= new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id= v.getId();

                switch (id){
                    case R.id.asian_card:
                        myDate.setCuisine("Asian");
                        break;
                    case R.id.american_card:
                        myDate.setCuisine("American");
                        break;
                    case R.id.indian_card:
                        myDate.setCuisine("Indian");
                        break;
                    case R.id.italian_card:
                        myDate.setCuisine("Italian");
                        break;
                    case R.id.mexican_card:
                        myDate.setCuisine("Mexican");
                        break;
                    case R.id.meditteranean_card:
                        myDate.setCuisine("Mediterranean");
                        break;
                    case R.id.thai_card:
                        myDate.setCuisine("Thai");
                        break;
                    case R.id.veagan_card:
                        myDate.setCuisine("Vegan");
                        break;
                }
                Intent sendCuisine= new Intent(CuisineActivity.this, RestaurantActivity.class);
                sendCuisine.putExtra(MyDateItems.MY_ITEMS,myDate);
                startActivity(sendCuisine);
            }
        };

        mAsianCard.setOnClickListener(setCuisine);
        mAmericanCard.setOnClickListener(setCuisine);
        mIndianCard.setOnClickListener(setCuisine);
        mItalianCard.setOnClickListener(setCuisine);
        mMexcianCard.setOnClickListener(setCuisine);
        mMeditteraneanCard.setOnClickListener(setCuisine);
        mThaiCard.setOnClickListener(setCuisine);
        mVeaganCard.setOnClickListener(setCuisine);

    }
}
