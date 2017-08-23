package com.datenyc.mom.datenyc.ui

import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.CardView
import android.view.View
import android.widget.ImageView

import com.datenyc.mom.datenyc.MyDateItems
import com.datenyc.mom.datenyc.R
import com.datenyc.mom.datenyc.ui.cuisine.CuisineActivity
import com.datenyc.mom.datenyc.ui.restaurant.RestaurantActivity
import com.squareup.picasso.Picasso

import butterknife.Bind
import butterknife.ButterKnife
import com.datenyc.mom.datenyc.databinding.ActivityVenueTypeBinding


class VenueType : AppCompatActivity() {

    private lateinit var binding: ActivityVenueTypeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = DataBindingUtil.setContentView(this, R.layout.activity_venue_type)
        title = "Venue Type"


        Picasso.with(this@VenueType).load(R.drawable.wine_image).fit().into(binding.bars)
        Picasso.with(this@VenueType).load(R.drawable.breakfast_image).fit().into(binding.breakfast)
        Picasso.with(this@VenueType).load(R.drawable.brunch_image).fit().into(binding.brunch)
        Picasso.with(this@VenueType).load(R.drawable.lunch_image).fit().into(binding.lunch)
        Picasso.with(this@VenueType).load(R.drawable.dinner_image).fit().into(binding.dinner)
        Picasso.with(this@VenueType).load(R.drawable.dessert_image).fit().into(binding.dessert)
        Picasso.with(this@VenueType).load(R.drawable.wine_image).fit().into(binding.winebar)
        Picasso.with(this@VenueType).load(R.drawable.coffee_image).fit().into(binding.coffee)

        val intent = intent
        val myDate = intent.getParcelableExtra<MyDateItems>(MyDateItems.MY_ITEMS)

        val setVenue = { v ->
            val id = v.getId()

            when (id) {
                R.id.break_card -> myDate.venueType = "Breakfast"
                R.id.brunch_card -> myDate.venueType = "Brunch"
                R.id.lunch_card -> myDate.venueType = "Lunch"
                R.id.dinner_card -> myDate.venueType = "Dinner"
            }
            val sendVenue = Intent(this@VenueType, CuisineActivity::class.java)
            sendVenue.putExtra(MyDateItems.MY_ITEMS, myDate)
            startActivity(sendVenue)
        }

        binding.barCard.setOnClickListener({ v ->
            val sendBar = Intent(this@VenueType, RestaurantActivity::class.java)
            myDate.cuisine = "Bars"
            sendBar.putExtra(MyDateItems.MY_ITEMS, myDate)
            startActivity(sendBar)

        })

        binding.dessertCard.setOnClickListener({ v ->
            val sendDessert = Intent(this@VenueType, RestaurantActivity::class.java)
            myDate.cuisine = "best Bakeries"
            sendDessert.putExtra(MyDateItems.MY_ITEMS, myDate)

            startActivity(sendDessert)

        })

        binding.wineCard.setOnClickListener({ v ->
            val sendWine = Intent(this@VenueType, RestaurantActivity::class.java)
            myDate.cuisine = "best wine bars"
            sendWine.putExtra(MyDateItems.MY_ITEMS, myDate)

            startActivity(sendWine)

        })

        binding.coffeeCard.setOnClickListener({ v ->
            val sendCoffee = Intent(this@VenueType, RestaurantActivity::class.java)
            myDate.cuisine = "Coffee shops"
            sendCoffee.putExtra(MyDateItems.MY_ITEMS, myDate)

            startActivity(sendCoffee)
        })

        binding.breakCard.setOnClickListener(setVenue)
        binding.brunchCard.setOnClickListener(setVenue)
        binding.lunchCard.setOnClickListener(setVenue)
        binding.dinnerCard.setOnClickListener(setVenue)
    }

}
