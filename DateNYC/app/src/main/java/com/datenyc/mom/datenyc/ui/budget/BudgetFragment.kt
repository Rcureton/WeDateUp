package com.datenyc.mom.datenyc.ui.budget

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.datenyc.mom.datenyc.MyDateItems
import com.datenyc.mom.datenyc.R
import com.datenyc.mom.datenyc.databinding.ActivityBudgetBinding
import com.datenyc.mom.datenyc.ui.location.LocationPage
import com.ge.cbyge.ui.WeDateUpFragment

class BudgetFragment : WeDateUpFragment() {

    private lateinit var binding: ActivityBudgetBinding

    companion object {
        fun newInstance() = BudgetFragment()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.activity_budget, container, false)

        binding.apply {
            oneCash.setImageResource(R.drawable.ic_looks_one)
            oneCash.setOnClickListener(setPrice)

            twoCash.setImageResource(R.drawable.ic_looks_two)
            twoCash.setOnClickListener(setPrice)

            threeCash.setImageResource(R.drawable.ic_looks_3)
            threeCash.setOnClickListener(setPrice)

            fourCash.setImageResource(R.drawable.ic_looks_4)
            fourCash.setOnClickListener(setPrice)
        }

        return binding.root
    }

    private val setPrice = View.OnClickListener {
        val id = it.id
        val dateItems = MyDateItems()

        when (id) {
            R.id.oneCash -> dateItems.price = "1"
            R.id.twoCash -> dateItems.price = "2"
            R.id.threeCash -> dateItems.price = "3"
            R.id.fourCash -> dateItems.price = "4"
        }

        val intent = Intent(context, LocationPage::class.java)
        intent.putExtra(MyDateItems.MY_ITEMS, dateItems)
        startActivity(intent)
    }
}