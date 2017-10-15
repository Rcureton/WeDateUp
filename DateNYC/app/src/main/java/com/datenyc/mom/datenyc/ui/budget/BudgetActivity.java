package com.datenyc.mom.datenyc.ui.budget;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.datenyc.mom.datenyc.MyDateItems;
import com.datenyc.mom.datenyc.R;
import com.datenyc.mom.datenyc.databinding.ActivityBudgetBinding;
import com.datenyc.mom.datenyc.ui.location.LocationPage;

public class BudgetActivity extends AppCompatActivity {
    private ActivityBudgetBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);
        setTitle("Budget");
        binding = DataBindingUtil.setContentView(this, R.layout.activity_budget);


        binding.oneCash.setImageResource(R.drawable.ic_looks_one);
        binding.oneCash.setOnClickListener(setPrice);

        binding.twoCash.setImageResource(R.drawable.ic_looks_two);
        binding.twoCash.setOnClickListener(setPrice);

        binding.threeCash.setImageResource(R.drawable.ic_looks_3);
        binding.threeCash.setOnClickListener(setPrice);

        binding.fourCash.setImageResource(R.drawable.ic_looks_4);
        binding.fourCash.setOnClickListener(setPrice);

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