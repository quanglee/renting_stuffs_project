package com.quangle.rentingutilities.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class MySharedPreferences {

    public static final String USER_TOKEN = "userToken";

    public static SharedPreferences.Editor getSharedPrerencesEditor(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.edit();
    }

    public static String getLoggedUserToken(Context c) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(c);
        return sharedPreferences.getString(USER_TOKEN, null);
    }

    public static void clearSharedPreferences(Context c) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(c);
        preferences.edit().clear().commit();
    }
}
