package com.techadhoc.techadhocutils.features.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.techadhoc.techadhocutils.R
import com.techadhoc.techadhocutils.features.constants.Keys
import com.techadhoc.techadhocutils.features.utils.NetworkUtil.getConnectivityStatus
import com.techadhoc.techadhocutils.features.utils.NetworkUtil.getConnectivityStatusString

class NetworkChangeReciever : BroadcastReceiver() {
    private var mContext: Context? = null
    override fun onReceive(context: Context, intent: Intent) {
        mContext = context
        val tag = getConnectivityStatus()
        val status = getConnectivityStatusString()
        if (tag == Keys.ERROR) {
            custumToast(Keys.ERROR, status)
        } else {
            custumToast(Keys.OK, status)
        }
    }

    fun custumToast(tag: Int, msg: String?) {
        // create a LinearLayout and Views
        val layout = LinearLayout(mContext)
        layout.gravity = Gravity.CENTER
        if (tag == Keys.OK) {
            layout.setBackgroundResource(R.color.ok_green)
        }
        if (tag == Keys.ERROR) {
            layout.setBackgroundResource(R.color.error_red)
        }
        val tv = TextView(mContext)
        // set the TextView properties like color, size etc
        tv.setTextColor(Color.WHITE)
        tv.textSize = 20f
        tv.gravity = Gravity.CENTER

        // set the text you want to show in  Toast
        tv.text = msg

        /*  ImageView   img=new ImageView(this);

      // give the drawble resource for the ImageView
      img.setImageResource(R.drawable.myimage);*/

        // add both the Views TextView and ImageView in error_dialog_view
        layout.addView(tv)
        val toast =
            Toast(mContext) //context is object of Context write "this" if you are an Activity
        // Set The error_dialog_view as Toast View
        toast.view = layout
        toast.setGravity(Gravity.FILL_HORIZONTAL or Gravity.TOP, 0, 0)
        toast.show()
    }
}