package com.quangle.rentingutilities.core.model;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.annotations.SerializedName;
import com.quangle.rentingutilities.utils.MySharedPreferences;

import java.io.Serializable;

public class Auth implements Serializable {

    @SerializedName("access_token")
    private String accessToken;
    @SerializedName("refresh_token")
    private String refreshToken;
    @SerializedName("user")
    private User user;

    public static final String SH_ACCESS_TOKEN = "accessToken";
    public static final String SH_REFRESH_TOKEN = "refreshToken";

    public Auth() {}

    public Auth(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public void toSharedPreferences(Context c) {
        SharedPreferences.Editor editor = MySharedPreferences.getSharedPrerencesEditor(c);
        editor.putString(SH_ACCESS_TOKEN, accessToken);
        editor.putString(SH_REFRESH_TOKEN, refreshToken);
        editor.commit();
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
