package com.datenyc.mom.datenyc;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.datenyc.mom.datenyc.VenueTypePackage.VenueType;
import com.datenyc.mom.datenyc.YelpAPI.LocationPage;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BudgetActivity extends AppCompatActivity {
    @Bind(R.id.oneCash)FloatingActionButton mOneCash;
@   Bind(R.id.twoCash)FloatingActionButton mTwoCash;
    @Bind(R.id.threeCash)FloatingActionButton mThreeCash;
    @Bind(R.id.fourCash)FloatingActionButton mFourCash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);
        setTitle("Budget");
        ButterKnife.bind(this);


        mOneCash.setImageResource(R.drawable.ic_looks_one);
        mOneCash.setOnClickListener(setPrice);

        mTwoCash.setImageResource(R.drawable.ic_looks_two);
        mTwoCash.setOnClickListener(setPrice);

        mThreeCash.setImageResource(R.drawable.ic_looks_3);
        mThreeCash.setOnClickListener(setPrice);

        mFourCash.setImageResource(R.drawable.ic_looks_4);
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

            Intent intent = new Intent(BudgetActivity.this, VenueType.class);
            intent.putExtra(MyDateItems.MY_ITEMS, dateItems);
            startActivity(intent);

        }
    };
}