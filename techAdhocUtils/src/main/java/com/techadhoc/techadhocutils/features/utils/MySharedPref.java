package com.techadhoc.techadhocutils.features.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Prabal on 11/24/2015.
 */
public class MySharedPref {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    private AESCrpto crypto;

    /******* Create SharedPreferences *******/
    public MySharedPref(Context context) {
        pref = PreferenceManager.getDefaultSharedPreferences(context);
        editor = pref.edit();
        crypto = new AESCrpto();

    }

    public void saveString(String key, String value) {
        try {
            // Save the changes in SharedPreferences
            //encrypt
            editor.putString(key, crypto.encrypt(value));
            editor.apply(); // commit changes
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void saveLong(String key, long value) {
        editor.putLong(key, value);
        editor.apply(); // commit changes
    }

    public long getLongValue(String key) {
        return pref.getLong(key, 0);
    }

    public void saveBool(String key, boolean value) {
        editor.putBoolean(key, value);
        // Save the changes in SharedPreferences
        editor.apply(); // commit changes
    }

    public boolean getBoolValue(String key) {
        return pref.getBoolean(key, false);
    }

    public String getStringValue(String key) {
        try {
            String encryptedVal = pref.getString(key, null);
            if (null != encryptedVal) {
                return crypto.decrypt(encryptedVal);
            } else {
                return null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void deleteValue(String key) {

        editor.remove(key); //
        // Save the changes in SharedPreferences
        editor.apply(); // commit changes
    }

    public void deleteAll() {
        editor.clear();
        // Save the changes in SharedPreferences
        editor.apply(); // commit changes
    }
}
