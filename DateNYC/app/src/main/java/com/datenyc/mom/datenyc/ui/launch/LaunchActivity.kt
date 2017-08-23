package com.datenyc.mom.datenyc.ui.launch

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.datenyc.mom.datenyc.R
import com.datenyc.mom.datenyc.databinding.ActivityMainBinding
import com.datenyc.mom.datenyc.ui.budget.BudgetActivity


class LaunchActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        binding.apply {
            start.setOnClickListener {
                val intent = Intent(this@LaunchActivity, BudgetActivity::class.java)
                startActivity(intent)
            }
        }


    }

}
