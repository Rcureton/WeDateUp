package com.ge.cbyge.ui.dialog

import android.app.Dialog

import android.content.Context
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog

private const val ARG_TITLE = "AlertDialog.Title"
private const val ARG_MESSAGE = "AlertDialog.Message"
private const val ARG_POSITIVE_BUTTON = "AlertDialog.Postive.Button"
private const val RESULT_POSITIVE = 1

class AlertDialogFragment : android.support.v4.app.DialogFragment() {

    interface AlertCallback {
        fun onDialogPositive()
    }

    companion object {
        fun newInstance(@StringRes titleResId: Int, @StringRes messageResId: Int, @StringRes positiveButtonText: Int): AlertDialogFragment {
            val args = Bundle()
            args.putInt(ARG_TITLE, titleResId)
            args.putInt(ARG_MESSAGE, messageResId)
            args.putInt(ARG_POSITIVE_BUTTON, positiveButtonText)
            val fragment = AlertDialogFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private var alertCallback: AlertCallback? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is AlertCallback) {
            alertCallback = context
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val title = arguments.getInt(ARG_TITLE)
        val message = arguments.getInt(ARG_MESSAGE)
        val positiveButton = arguments.getInt(ARG_POSITIVE_BUTTON)

        val builder = AlertDialog.Builder(activity).setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveButton, { dialogInterface, i -> onButtonTapped(RESULT_POSITIVE) })


        return builder.create()
    }

    override fun onDetach() {
        super.onDetach()
        alertCallback = null
    }

    fun onButtonTapped(buttonResultCode: Int) {
        val fragment: Fragment? = targetFragment
        fragment ?: let {
            fragment?.onActivityResult(targetRequestCode, buttonResultCode, null)
            when (buttonResultCode) {
                RESULT_POSITIVE -> alertCallback?.onDialogPositive()
                else -> throw IllegalArgumentException()
            }
        }
    }
}