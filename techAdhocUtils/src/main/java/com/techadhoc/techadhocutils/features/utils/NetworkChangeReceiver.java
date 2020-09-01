package com.techadhoc.techadhocutils.features.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.techadhoc.techadhocutils.R;
import com.techadhoc.techadhocutils.features.constants.Keys;


public class NetworkChangeReceiver extends BroadcastReceiver {
    Context mContext;

    @Override
    public void onReceive(final Context context, final Intent intent) {
        mContext = context;
        int tag = NetworkUtil.getConnectivityStatus(mContext);
        String status = NetworkUtil.getConnectivityStatusString(mContext);
        if (tag == Keys.ERROR) {
            custumToast(Keys.ERROR, status);

        } else {
            custumToast(Keys.OK, status);

        }
    }

    void custumToast(int tag, String msg) {
        // create a LinearLayout and Views

        LinearLayout layout = new LinearLayout(mContext);
        layout.setGravity(Gravity.CENTER);
        if (tag == Keys.OK) {
            layout.setBackgroundResource(R.color.ok_green);

        }
        if (tag == Keys.ERROR) {
            layout.setBackgroundResource(R.color.error_red);

        }

        TextView tv = new TextView(mContext);
        // set the TextView properties like color, size etc
        tv.setTextColor(Color.WHITE);
        tv.setTextSize(20);

        tv.setGravity(Gravity.CENTER);

        // set the text you want to show in  Toast
        tv.setText(msg);

   /*  ImageView   img=new ImageView(this);

      // give the drawble resource for the ImageView
      img.setImageResource(R.drawable.myimage);*/

        // add both the Views TextView and ImageView in layout

        layout.addView(tv);

        Toast toast = new Toast(mContext); //context is object of Context write "this" if you are an Activity
        // Set The layout as Toast View
        toast.setView(layout);
        toast.setGravity(Gravity.FILL_HORIZONTAL | Gravity.TOP, 0, 0);
        toast.show();
    }
}