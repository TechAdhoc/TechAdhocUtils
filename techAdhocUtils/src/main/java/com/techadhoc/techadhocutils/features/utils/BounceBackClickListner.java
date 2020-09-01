package com.techadhoc.techadhocutils.features.utils;

import android.view.View;

public abstract class BounceBackClickListner implements View.OnClickListener {
    private static boolean enabled = true;

    private static final Runnable ENABLE_AGAIN = () -> enabled = true;

    @Override public final void onClick(View v) {
        if (enabled) {
            enabled = false;
            v.post(ENABLE_AGAIN);
            doClick(v);
        }
    }

    public abstract void doClick(View v);
}