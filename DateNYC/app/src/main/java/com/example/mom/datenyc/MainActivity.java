package com.example.mom.datenyc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.mom.datenyc.YelpAPI.LocationPage;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    Button mStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView background= (ImageView)findViewById(R.id.background);

        Picasso.with(MainActivity.this).load("https://s-media-cache-ak0.pinimg.com/564x/b6/f1/34/b6f1340783278be2c97535f2674f6f49.jpg").fit().into(background);


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
