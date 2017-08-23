package com.datenyc.mom.datenyc.ui.budget;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.datenyc.mom.datenyc.MyDateItems;
import com.datenyc.mom.datenyc.R;
import com.datenyc.mom.datenyc.ui.location.LocationPage;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BudgetActivity extends AppCompatActivity {
    @BindView(R.id.oneCash) FloatingActionButton mOneCash;
    @BindView(R.id.twoCash) FloatingActionButton mTwoCash;
    @BindView(R.id.threeCash) FloatingActionButton mThreeCash;
    @BindView(R.id.fourCash) FloatingActionButton mFourCash;

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

    View.OnClickListener setPrice = v -> {
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

    };
}