package com.apripachkin.shibagram.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Pripachkin on 02.08.2016.
 */
public class SharedPreferencesUtil {
    private static final String SHARED_PREF_NAME = "ShibaLoverSharedPreferences";
    private SharedPreferences sp;

    public SharedPreferencesUtil(Context context) {
        sp = context.getSharedPreferences(SHARED_PREF_NAME, context.MODE_PRIVATE);
    }

    public void saveBoolean(String key, boolean value) {
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean(key, value);
        edit.apply();
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return sp.getBoolean(key, defaultValue);
    }

    public void saveData(String key, String data) {
        SharedPreferences.Editor spEditor = sp.edit();
        spEditor.putString(key, data);
        spEditor.apply();
    }

    public String getData(String key, String defaultValue) {
        return sp.getString(key, defaultValue);
    }
}
