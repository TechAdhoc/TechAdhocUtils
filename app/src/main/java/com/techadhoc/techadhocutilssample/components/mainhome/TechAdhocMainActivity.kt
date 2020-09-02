package com.techadhoc.techadhocutilssample.components.mainhome

import android.content.DialogInterface
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.techadhoc.techadhocutilssample.R
import com.techadhoc.techadhocutilssample.components.core.ui.DialogCallback
import com.techadhoc.techadhocutilssample.components.dialogs.CustomDialog
import com.techadhoc.techadhocutilssample.components.dialogs.ErrorDialog

class TechAdhocMainActivity : AppCompatActivity() {
    private lateinit var errorDialogButton: Button
    private lateinit var customDialogButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_techadhoc_main)
        errorDialogButton = findViewById<Button>(R.id.error_dialog)
        customDialogButton = findViewById<Button>(R.id.custom_dialog)
        setListner()
    }

    private fun setListner() {
        errorDialogButton.setOnClickListener {
            ErrorDialog.newInstance(supportFragmentManager, callBack = object : DialogCallback {
                override fun doPositive(dialog: DialogInterface, which: Int) {
                    dialog.dismiss()
                }

                override fun doNegative(dialog: DialogInterface, which: Int) {
                    TODO("Not yet implemented")
                    dialog.dismiss()
                }

            })
        }

        customDialogButton.setOnClickListener {
            val dialog = CustomDialog.newInstance { customDialogOnClick() }
            dialog.show(supportFragmentManager)
        }
    }

    private fun customDialogOnClick() {
        // click function for dialog call back
        Toast.makeText(this, "Custom", Toast.LENGTH_LONG).show()
    }
}