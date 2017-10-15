package com.datenyc.mom.datenyc.ui.location

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.databinding.DataBindingUtil.setContentView
import android.location.Location
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

import com.datenyc.mom.datenyc.MyDateItems
import com.datenyc.mom.datenyc.R
import com.datenyc.mom.datenyc.databinding.ActivityLocationBinding
import com.datenyc.mom.datenyc.databinding.ContentLocationBinding
import com.datenyc.mom.datenyc.ui.VenueType
import com.google.android.gms.awareness.Awareness
import com.google.android.gms.awareness.snapshot.LocationResult
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.ResultCallback
import com.google.android.gms.location.LocationServices
import timber.log.Timber

private const val REQUEST_FINE_LOCATION = 2

class LocationPage : AppCompatActivity() {

    private lateinit var binding: ContentLocationBinding
    internal var lon: Double = 0.toDouble()
    internal var lat: Double = 0.toDouble()
    internal var myDate: MyDateItems? = null
    private var mLastLocationCoordinates: Location? = null
    private var mGoogleApiClient: GoogleApiClient? = null
    private lateinit var googleApiClient: GoogleApiClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        title = "Location"


        googleApiClient = GoogleApiClient.Builder(this)
                .addApi(Awareness.API)
                .build()
        googleApiClient.connect()


        Awareness.SnapshotApi.getLocation(googleApiClient)
                .setResultCallback { locationResult ->
                    lat = locationResult.location.latitude
                    lon = locationResult.location.longitude
                }
        val myDate = MyDateItems()

        binding.imageButton.setOnClickListener({
            val intent = Intent(this@LocationPage, VenueType::class.java)
            myDate.lat = lat
            myDate.lon = lon
            intent.putExtra(MyDateItems.MY_ITEMS, myDate)
            startActivity(intent)
        })
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if (requestCode == REQUEST_ENABLE_BT && resultCode == Activity.RESULT_OK) {
//            onEnableLocationAccessClick()
//        }
//    }
//
//    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        val fragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
//        (fragment as LocationAccessFragment).onPermissionsResult(requestCode, grantResults)
//    }

    fun onPermissionsResult(requestCode: Int, grantResults: IntArray) {
        if (requestCode == REQUEST_FINE_LOCATION
                && grantResults.isNotEmpty()
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

        }
    }


    fun hasPermissions(): Boolean {
        if (!hasLocationPermissions()) {
            requestLocationPermission()
            return false
        }
        return true
    }

    private fun hasLocationPermissions()
            = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED

    private fun requestLocationPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_FINE_LOCATION)
        Timber.d("Requested user enable Location. Try connecting again.")
    }



}

