package com.quangle.rentingutilities.networking;

import com.quangle.rentingutilities.core.model.User;

import retrofit2.Call;
import retrofit2.http.POST;

public interface Api {

    @POST("auth/user")
    Call<User> login();

}
