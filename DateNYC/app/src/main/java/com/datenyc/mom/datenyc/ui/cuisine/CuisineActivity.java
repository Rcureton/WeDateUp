package com.datenyc.mom.datenyc.ui.cuisine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.datenyc.mom.datenyc.MyDateItems;
import com.datenyc.mom.datenyc.R;
import com.datenyc.mom.datenyc.ui.restaurant.RestaurantActivity;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CuisineActivity extends AppCompatActivity {

    //ImageViews
    @BindView(R.id.asian) ImageView mAsian;
    @BindView(R.id.american) ImageView mAmerican;
    @BindView(R.id.indian) ImageView mIndian;
    @BindView(R.id.italian) ImageView mItalian;
    @BindView(R.id.mexican) ImageView mMexican;
    @BindView(R.id.meditteranean) ImageView mMeditteranean;
    @BindView(R.id.thai) ImageView mThai;
    @BindView(R.id.veagan) ImageView mVeagan;
    //Cardviews
    @BindView(R.id.asian_card) CardView mAsianCard;
    @BindView(R.id.american_card) CardView mAmericanCard;
    @BindView(R.id.indian_card) CardView mIndianCard;
    @BindView(R.id.italian_card) CardView mItalianCard;
    @BindView(R.id.mexican_card) CardView mMexicanCard;
    @BindView(R.id.meditteranean_card) CardView mMeditteraneanCard;
    @BindView(R.id.thai_card) CardView mThaiCard;
    @BindView(R.id.veagan_card) CardView mVeaganCard;

    @BindView(R.id.foodEditText) EditText mEditText;
    @BindView(R.id.submitButton) Button mButton;
    private static String mUserInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuisine);
        setTitle("Cuisine Type");
        ButterKnife.bind(this);

        Picasso.with(CuisineActivity.this).load("http://nebula.wsimg.com/82c2438bcec6ce077fdb9f5777483347?AccessKeyId=FB771320EF82836BDCA7&disposition=0&alloworigin=1").fit().into(mAsian);
        Picasso.with(CuisineActivity.this).load("http://www.loneriderbeer.com/wp-content/uploads/bbq.jpg").fit().into(mAmerican);
        Picasso.with(CuisineActivity.this).load("http://insidenirvana.com/wp-content/uploads/2015/08/best-indian-food-new-orleans.jpg").fit().into(mIndian);
        Picasso.with(CuisineActivity.this).load("http://blog.maggianos.com/wp-content/uploads/ChickenFontina_FPO.jpg").fit().into(mItalian);
        Picasso.with(CuisineActivity.this).load("http://theseasonedchef.com/wp-content/uploads/2013/12/Mexican-Food.jpg").fit().into(mMexican);
        Picasso.with(CuisineActivity.this).load("http://nebula.wsimg.com/7056052cf5a33e79490298ad8251d82a?AccessKeyId=A19A869FFE0BB83606FD&disposition=0&alloworigin=1").fit().into(mMeditteranean);
        Picasso.with(CuisineActivity.this).load("http://farm4.staticflickr.com/3669/10832314654_ff403fcb07_b.jpg").fit().into(mThai);
        Picasso.with(CuisineActivity.this).load("https://media.timeout.com/images/102030827/617/347/image.jpg").fit().into(mVeagan);

        Intent intent= getIntent();
        final MyDateItems myDate= intent.getParcelableExtra(MyDateItems.MY_ITEMS);
        final String price= myDate.getPrice();

        Log.d("venue type",myDate.getVenueType());

        View.OnClickListener setCuisine= new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id= v.getId();

                switch (id){
                    case R.id.asian_card:
                        myDate.setCuisine("best Asian restaurant");
                        break;
                    case R.id.american_card:
                        myDate.setCuisine("best BBQ restaurant");
                        break;
                    case R.id.indian_card:
                        myDate.setCuisine("best Indian restaurant");
                        break;
                    case R.id.italian_card:
                        myDate.setCuisine("best Italian restaurant");
                        break;
                    case R.id.mexican_card:
                        myDate.setCuisine("best Mexican restaurant");
                        break;
                    case R.id.meditteranean_card:
                        myDate.setCuisine("best Mediterranean restaurant");
                        break;
                    case R.id.thai_card:
                        myDate.setCuisine("best Thai restaurant");
                        break;
                    case R.id.veagan_card:
                        myDate.setCuisine("best Vegan restaurant");
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
        mMexicanCard.setOnClickListener(setCuisine);
        mMeditteraneanCard.setOnClickListener(setCuisine);
        mThaiCard.setOnClickListener(setCuisine);
        mVeaganCard.setOnClickListener(setCuisine);

        //Edit Text Input that searches for food as well as the cards

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserInput= mEditText.getText().toString();
                if(mUserInput==null){
                    Toast.makeText(CuisineActivity.this, "Please enter input", Toast.LENGTH_SHORT).show();

                }
                myDate.setCuisine(mUserInput);
                Intent textBox= new Intent(CuisineActivity.this, RestaurantActivity.class);
                textBox.putExtra(MyDateItems.MY_ITEMS, myDate);
                startActivity(textBox);

            }
        });

    }
}
