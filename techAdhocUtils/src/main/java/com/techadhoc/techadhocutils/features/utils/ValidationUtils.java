package com.techadhoc.techadhocutils.features.utils;

import android.provider.Settings;

import com.techadhoc.techadhocutils.features.MyAppUtils;

public class ValidationUtils {
    private long userAdminNumber = 1234L;

    public boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        //email.contains("@");
        return true;
    }

    public boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 2;
    }

    public String getDeviceId(long userName) {
        if (userName == userAdminNumber) {
            String admin_id = "7d6ada8c6b330508";
            return admin_id;
        } else {
            String android_id = Settings.Secure.getString(
                    MyAppUtils.getContext().getContentResolver(),
                    Settings.Secure.ANDROID_ID);
            return android_id;
        }
    }

    public static boolean NullCheck(String data) {
        if (null != data) {
            return true;
        }
        return false;
    }

    public static String NotNullData(String data) {
        if (null != data) {
            return data;
        }
        return "";
    }

}
