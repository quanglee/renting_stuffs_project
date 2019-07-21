package com.quangle.rentingutilities.networking;

import com.quangle.rentingutilities.core.model.User;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Api {

    @POST("auth/login")
    Call<User> login(@Body HashMap<String, String> params);

}
