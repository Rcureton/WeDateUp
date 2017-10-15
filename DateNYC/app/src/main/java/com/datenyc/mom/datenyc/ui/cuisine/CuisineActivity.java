package com.datenyc.mom.datenyc.ui.cuisine;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.datenyc.mom.datenyc.MyDateItems;
import com.datenyc.mom.datenyc.R;
import com.datenyc.mom.datenyc.databinding.ActivityCuisineBinding;
import com.datenyc.mom.datenyc.ui.restaurant.RestaurantActivity;
import com.squareup.picasso.Picasso;

public class CuisineActivity extends AppCompatActivity {

    private static String mUserInput;
    private ActivityCuisineBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuisine);
        setTitle("Cuisine Type");
        binding = DataBindingUtil.setContentView(this, R.layout.activity_cuisine);

        Picasso.with(CuisineActivity.this).load("http://nebula.wsimg.com/82c2438bcec6ce077fdb9f5777483347?AccessKeyId=FB771320EF82836BDCA7&disposition=0&alloworigin=1").fit().into(binding.asian);
        Picasso.with(CuisineActivity.this).load("http://www.loneriderbeer.com/wp-content/uploads/bbq.jpg").fit().into(binding.american);
        Picasso.with(CuisineActivity.this).load("http://insidenirvana.com/wp-content/uploads/2015/08/best-indian-food-new-orleans.jpg").fit().into(binding.indian);
        Picasso.with(CuisineActivity.this).load("http://blog.maggianos.com/wp-content/uploads/ChickenFontina_FPO.jpg").fit().into(binding.italian);
        Picasso.with(CuisineActivity.this).load("http://theseasonedchef.com/wp-content/uploads/2013/12/Mexican-Food.jpg").fit().into(binding.mexican);
        Picasso.with(CuisineActivity.this).load("http://nebula.wsimg.com/7056052cf5a33e79490298ad8251d82a?AccessKeyId=A19A869FFE0BB83606FD&disposition=0&alloworigin=1").fit().into(binding.meditteranean);
        Picasso.with(CuisineActivity.this).load("http://farm4.staticflickr.com/3669/10832314654_ff403fcb07_b.jpg").fit().into(binding.thai);
        Picasso.with(CuisineActivity.this).load("https://media.timeout.com/images/102030827/617/347/image.jpg").fit().into(binding.veagan);

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

        binding.asianCard.setOnClickListener(setCuisine);
        binding.americanCard.setOnClickListener(setCuisine);
        binding.indianCard.setOnClickListener(setCuisine);
        binding.italianCard.setOnClickListener(setCuisine);
        binding.mexicanCard.setOnClickListener(setCuisine);
        binding.meditteraneanCard.setOnClickListener(setCuisine);
        binding.thaiCard.setOnClickListener(setCuisine);
        binding.veaganCard.setOnClickListener(setCuisine);

        //Edit Text Input that searches for food as well as the cards

        binding.submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserInput= binding.foodEditText.getText().toString();
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
