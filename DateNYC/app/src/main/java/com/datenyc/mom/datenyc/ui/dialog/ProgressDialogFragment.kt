package com.ge.cbyge.ui.dialog

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.datenyc.mom.datenyc.R
import com.datenyc.mom.datenyc.databinding.DialogProgressBinding

private const val ARG_TITLE = "ProgressDialog.Title"

class ProgressDialog : DialogFragment() {

    companion object {
        fun newInstance(titleResId: Int): ProgressDialog {
            val args = Bundle()
            args.putInt(ARG_TITLE, titleResId)
            val fragment = ProgressDialog()
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var binding: DialogProgressBinding

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_progress, container, false)

        binding.apply {
            progressBar.animate()
            val title = arguments.getInt(ARG_TITLE)
            titleTextView.setText(title)
        }

        return binding.root
    }
}