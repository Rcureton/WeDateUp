package com.datenyc.mom.datenyc.ui.budget

import android.content.Context
import android.content.Intent
import com.ge.cbyge.ui.WeDateUpSingleFragmentActivity

class BudgetActivity : WeDateUpSingleFragmentActivity() {

    companion object {
        fun newIntent(context: Context) = Intent(context, BudgetActivity::class.java)
    }

    override fun createFragment() = BudgetFragment.newInstance()

}