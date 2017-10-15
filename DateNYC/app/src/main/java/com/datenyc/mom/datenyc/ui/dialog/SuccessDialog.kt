package com.ge.cbyge.ui.dialog

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.datenyc.mom.datenyc.R
import com.datenyc.mom.datenyc.databinding.DialogSuccessBinding

private const val ARG_MESSAGE = "SuccessDialog.Message"

class SuccessDialog : DialogFragment() {

    companion object {
        fun newInstance(messageResId: Int): SuccessDialog {
            val args = Bundle()
            args.putInt(ARG_MESSAGE, messageResId)
            val fragment = SuccessDialog()
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var binding: DialogSuccessBinding

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_success, container, false)

        binding.apply {
            val message = arguments.getInt(ARG_MESSAGE)
            dialogSuccessMessageTextView.setText(message)

//            dialogSuccessButton.setOnClickListener {
//                val intent = LoginActivity.newIntent(context)
//                startActivity(intent)
//            }
        }

        return binding.root
    }

    override fun onResume() {
        val params: ViewGroup.LayoutParams = dialog.window.attributes
        params.width = WindowManager.LayoutParams.MATCH_PARENT
        params.height = WindowManager.LayoutParams.MATCH_PARENT
        dialog.window.attributes = params as android.view.WindowManager.LayoutParams

        super.onResume()
    }
}