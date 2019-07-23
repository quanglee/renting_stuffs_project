package com.quangle.rentingutilities.networking;

import com.quangle.rentingutilities.core.model.Auth;
import com.quangle.rentingutilities.core.model.Item;
import com.quangle.rentingutilities.core.model.User;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {

    @POST("auth/login")
    Call<Auth> login(@Body HashMap<String, String> params);

    //Users
    @POST("users/create")
    Call<User> createUser(@Body HashMap<String, String> params);

    //Items
    // get all items for home page
    @GET("items")
    Call<List<Item>> getAllItems();
}
