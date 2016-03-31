package com.example.mom.datenyc;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.mom.datenyc.YelpAPI.LocationPage;
import com.squareup.picasso.Picasso;

public class BudgetActivity extends AppCompatActivity {
    FloatingActionButton mOneCash, mTwoCash, mThreeCash, mFourCash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);
        setTitle("Budget");

        ImageView budgetBackground = (ImageView) findViewById(R.id.budgetBackground);

        Picasso.with(BudgetActivity.this).load("https://s-media-cache-ak0.pinimg.com/564x/b6/f1/34/b6f1340783278be2c97535f2674f6f49.jpg").fit().into(budgetBackground);


        mOneCash = (FloatingActionButton) findViewById(R.id.oneCash);
        mOneCash.setImageResource(R.drawable.cash);
        mOneCash.setOnClickListener(setPrice);

        mTwoCash = (FloatingActionButton) findViewById(R.id.twoCash);
        mTwoCash.setImageResource(R.drawable.cash_two);
        mTwoCash.setOnClickListener(setPrice);
        mThreeCash = (FloatingActionButton) findViewById(R.id.threeCash);
        mThreeCash.setImageResource(R.drawable.cash_three);
        mThreeCash.setOnClickListener(setPrice);

        mFourCash = (FloatingActionButton) findViewById(R.id.fourCash);
        mFourCash.setImageResource(R.drawable.cash_four);
        mFourCash.setOnClickListener(setPrice);

    }

    View.OnClickListener setPrice = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            MyDateItems dateItems = new MyDateItems();

            switch (id) {
                case R.id.oneCash:
                    dateItems.setPrice("1");
                    break;
                case R.id.twoCash:
                    dateItems.setPrice("2");
                    break;
                case R.id.threeCash:
                    dateItems.setPrice("3");
                    break;
                case R.id.fourCash:
                    dateItems.setPrice("4");
                    break;

            }

            Intent intent = new Intent(BudgetActivity.this, LocationPage.class);
            intent.putExtra(MyDateItems.MY_ITEMS, dateItems);
            startActivity(intent);

        }
    };
}