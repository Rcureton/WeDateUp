package com.datenyc.mom.datenyc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    Button mStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView background= (ImageView)findViewById(R.id.background);

        Picasso.with(MainActivity.this).load("http://realestate-report.com/media/33.jpg").fit().into(background);


        mStart=(Button)findViewById(R.id.start);
        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, BudgetActivity.class);
                startActivity(intent);
            }
        });
    }
}
