package com.datenyc.mom.datenyc.ui.launch

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.datenyc.mom.datenyc.R
import com.datenyc.mom.datenyc.databinding.FragmentLaunchBinding
import com.datenyc.mom.datenyc.ui.budget.BudgetActivity
import com.ge.cbyge.ui.WeDateUpFragment

class LaunchFragment: WeDateUpFragment() {

    companion object {
        fun newInstance() = LaunchFragment()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentLaunchBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_launch, container, false)

        binding.apply {
            start.setOnClickListener {
                val intent = BudgetActivity.newIntent(context)
                startActivity(intent)
            }
        }
        return binding.root
    }
}