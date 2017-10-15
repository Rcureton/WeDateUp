package com.ge.cbyge.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import com.datenyc.mom.datenyc.R

abstract class WeDateUpSingleFragmentActivity : WeDateUpActivity() {

    protected abstract fun createFragment(): Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_fragment)

        var fragment: Fragment? = supportFragmentManager.findFragmentById(R.id.fragment_container)

        fragment ?: let {
            fragment = createFragment()
            supportFragmentManager.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit()
        }
    }
}
