package com.quangle.rentingutilities.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.quangle.rentingutilities.core.model.Auth;

public class MySharedPreferences {

    public static SharedPreferences.Editor getSharedPrerencesEditor(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.edit();
    }

    public static Auth getAuth(Context c) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(c);
        String accessToken = sharedPreferences.getString(Auth.SH_ACCESS_TOKEN, null);
        String refreshToken = sharedPreferences.getString(Auth.SH_REFRESH_TOKEN, null);
        Auth auth = null;

        if (accessToken != null && refreshToken != null)
            auth = new Auth(accessToken, refreshToken);

        return auth;
    }

    public static void clearSharedPreferences(Context c) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(c);
        preferences.edit().clear().commit();
    }
}
