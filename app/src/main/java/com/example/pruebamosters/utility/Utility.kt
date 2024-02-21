package com.example.pruebamosters.utility

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.ContextWrapper
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import com.example.pruebamosters.R

class Utility {

    fun ShowLoadingDialog(context: Context?): AlertDialog? {

        val alertDialog: AlertDialog
        try {
            val builder: AlertDialog.Builder = AlertDialog.Builder(context, R.style.whiteLoad)
            val customLayout: View =
                LayoutInflater.from(context).inflate(R.layout.loading_data, null)
            builder.setView(customLayout)
            builder.setCancelable(false)
            alertDialog = builder.create()
            alertDialog.show()
            return alertDialog
        } catch (e: Exception) {
            Log.i("error", "no cargo el dialogo")
            return null
        }
    }

    fun CloseLoadingDialog(dialog: AlertDialog?) {
        var dialog = dialog
        if (dialog != null) {
            if (dialog.isShowing) {

                val context = (dialog.context as ContextWrapper).baseContext

                if (context is Activity) {

                    // Api >=17
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                        if (!context.isFinishing && !context.isDestroyed) {
                            dismissWithTryCatch(dialog)
                        }
                    } else {

                        // Api < 17. Unfortunately cannot check for isDestroyed()
                        if (!context.isFinishing) {
                            dismissWithTryCatch(dialog)
                        }
                    }
                } else  // if the Context used wasn't an Activity, then dismiss it too
                    dismissWithTryCatch(dialog)
            }
            dialog = null
        }
    }

    fun dismissWithTryCatch(dialog: Dialog?) {
        val dialog: Dialog? = dialog

        dialog?.dismiss()
    }
}