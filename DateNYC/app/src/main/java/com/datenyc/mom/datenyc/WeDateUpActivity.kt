package com.ge.cbyge.ui

import android.support.v7.app.AppCompatActivity
import android.view.MenuItem

abstract class WeDateUpActivity : AppCompatActivity() {

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    /* ActionBar */

    override fun onStart() {
        super.onStart()
        setupActionBar()
    }

    fun setupActionBar() = supportActionBar?.apply {

        setDisplayShowTitleEnabled(true)
        val actionBarTitle = getActionBarTitle()

        if (!actionBarTitle.isNullOrEmpty()) {
            title = actionBarTitle
        }

        // decide to display home caret
        if (requiresBackNavigation()) {
            setDisplayHomeAsUpEnabled(true)
        }
    }

    fun changeTitle(newTitle: String) = supportActionBar?.apply {
        setDisplayShowTitleEnabled(true)
        if (!newTitle.isNullOrEmpty()) {
            title = newTitle
        }
    }

    /**
     * Returns a fixed title to show in the Toolbar. If null is provided the Toolbar will have the app_name as a default.
     */
    protected open fun getActionBarTitle(): CharSequence? = title

    protected open fun requiresBackNavigation() = false
}
