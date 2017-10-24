package com.datenyc.mom.datenyc.ui.location

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.datenyc.mom.datenyc.MyDateItems
import com.datenyc.mom.datenyc.R
import com.datenyc.mom.datenyc.databinding.ActivityLocationBinding
import com.datenyc.mom.datenyc.databinding.ContentLocationBinding
import com.datenyc.mom.datenyc.ui.VenueType
import com.ge.cbyge.ui.WeDateUpFragment
import com.google.android.gms.awareness.Awareness
import com.google.android.gms.common.api.GoogleApiClient
import timber.log.Timber

private const val REQUEST_FINE_LOCATION = 2

class LocationFragment : WeDateUpFragment() {

    private lateinit var googleApiClient: GoogleApiClient
    private var lon: Double = 0.toDouble()
    private var lat: Double = 0.toDouble()
    private lateinit var dateItems: MyDateItems


    companion object {
        fun newInstance() = LocationFragment()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: ContentLocationBinding = DataBindingUtil.inflate(inflater, R.layout.content_location, container, false)

        googleApiClient = GoogleApiClient.Builder(context)
                .addApi(Awareness.API)
                .build()
        googleApiClient.connect()

        Awareness.SnapshotApi.getLocation(googleApiClient)
                .setResultCallback { locationResult ->
                    lat = locationResult.location.latitude
                    lon = locationResult.location.longitude
                }

        dateItems = MyDateItems()

        binding.imageButton.setOnClickListener {
            hasLocationPermissions()
            val intent = Intent(context, VenueType::class.java)
            dateItems.lat = lat
            dateItems.lon = lon
            intent.putExtra(MyDateItems.MY_ITEMS, dateItems)
            startActivity(intent)
        }
        return binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_FINE_LOCATION && resultCode == Activity.RESULT_OK) {
            onEnableLocationAccessClick()
        }
    }

    fun onPermissionsResult(requestCode: Int, grantResults: IntArray) {
        if (requestCode == REQUEST_FINE_LOCATION
                && grantResults.isNotEmpty()
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            onEnableLocationAccessClick()
        }
    }

    fun onEnableLocationAccessClick() {
        if (!hasPermissions()) {
            return
        }
        val intent = Intent(context, VenueType::class.java)
        startActivity(intent)
        activity.finish()
    }

    fun hasPermissions(): Boolean {
        if (!hasLocationPermissions()) {
            requestLocationPermission()
            return false
        }
        return true
    }

    private fun hasLocationPermissions()
            = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED

    private fun requestLocationPermission() {
        ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_FINE_LOCATION)
        Timber.d("Requested user enable Location. Try connecting again.")
    }
}