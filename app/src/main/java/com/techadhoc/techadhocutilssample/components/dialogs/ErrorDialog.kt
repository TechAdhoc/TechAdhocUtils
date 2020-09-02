package com.techadhoc.techadhocutilssample.components.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.techadhoc.techadhocutils.R
import com.techadhoc.techadhocutilssample.components.core.ui.DialogCallback

class ErrorDialog : DialogFragment() {
    companion object {
        private const val ERROR_DIALOG = "error_dialog"
        private lateinit var errorDialogCallback: DialogCallback
        fun newInstance(fragmentManager: FragmentManager, callBack: DialogCallback) {
            errorDialogCallback = callBack
            val dialog = ErrorDialog()
            dialog.isCancelable = false
            dialog.show(fragmentManager, ERROR_DIALOG)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        activity?.let {
            val dialogBuilder = AlertDialog.Builder(it, R.style.Base_Theme_AppCompat_Dialog_Alert)
            dialogBuilder.setCancelable(false)
                .setMessage("Alert")
                .setPositiveButton("OK",
                    DialogInterface.OnClickListener { dialog, which ->
                        errorDialogCallback.doPositive(dialog, which)
                    })
            dialogBuilder.create()
        } ?: throw IllegalStateException("Null Activity")
}