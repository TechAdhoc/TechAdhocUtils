package com.techadhoc.techadhocutils.features.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AlertDialog;


public class AlertUtils {

  /*  public void showAlert(String msg, Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(msg)
                .setTitle("Alert Message")
                .setIcon(R.drawable.ic_warning)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dismissDialog();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
        final Button positiveButton = alert.getButton(AlertDialog.BUTTON_POSITIVE);
        LinearLayout.LayoutParams positiveButtonLL = (LinearLayout.LayoutParams)
                positiveButton.getLayoutParams();
        positiveButtonLL.width = ViewGroup.LayoutParams.MATCH_PARENT;
        //positiveButton.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimaryLight));
        positiveButton.setLayoutParams(positiveButtonLL);

    }*/
}
