package com.datenyc.mom.datenyc.ui.location

import android.support.v4.app.ActivityCompat
import com.datenyc.mom.datenyc.R
import com.ge.cbyge.ui.WeDateUpSingleFragmentActivity


class LocationPage : WeDateUpSingleFragmentActivity(), ActivityCompat.OnRequestPermissionsResultCallback {

    override fun createFragment() = LocationFragment.newInstance()

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        val fragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        (fragment as LocationFragment).onPermissionsResult(requestCode, grantResults)
    }
}

