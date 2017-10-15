package com.ge.cbyge.ui

import android.support.annotation.StringRes
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import com.datenyc.mom.datenyc.R
import com.ge.cbyge.ui.dialog.AlertDialogFragment
import com.ge.cbyge.ui.dialog.ProgressDialog
import com.ge.cbyge.ui.dialog.SuccessDialog
import io.reactivex.disposables.CompositeDisposable

private const val TAG_PROGRESS_DIALOG = "GEFragment.ProgressFragment"

private const val TAG_SUCCESS_DIALOG = "GEFragment.SuccessFragment"

private const val TAG_ALERT_DIALOG = "GEFragment.AlertDialogFragment"


open class WeDateUpFragment : Fragment() {

    var compositeDisposable = CompositeDisposable()

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

    protected fun showProgressDialog(titleResId: Int = R.string.progress_dialog_loading) {
        val fragment = ProgressDialog.newInstance(titleResId)
        fragment.show(fragmentManager, TAG_PROGRESS_DIALOG)
    }

    protected fun hideProgressDialog() {
        val fragment = fragmentManager.findFragmentByTag(TAG_PROGRESS_DIALOG)
        (fragment as? DialogFragment)?.dismissAllowingStateLoss()
    }

    protected fun showSuccessDialog(messageResId: Int) {
        val fragment = SuccessDialog.newInstance(messageResId)
        fragment.show(fragmentManager, TAG_SUCCESS_DIALOG)
    }

    protected fun showAlertDialog(@StringRes titleResId: Int, @StringRes messageResId: Int, @StringRes positiveButtonText: Int) {
        val fragment = AlertDialogFragment.newInstance(titleResId, messageResId, positiveButtonText)
        fragment.show(fragmentManager, TAG_ALERT_DIALOG)
    }
}