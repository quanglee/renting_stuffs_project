package com.quangle.rentingutilities.networking;

import com.quangle.rentingutilities.core.model.Auth;
import com.quangle.rentingutilities.core.model.Item;


import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api {

    @POST("auth/login")
    Call<Auth> login(@Body HashMap<String, String> params);

    // get all items for home page
    @GET("items")
    Call<List<Item>> getAllItems();

    // get all items of a user
    @GET("items/{owner_id}")
    Call<List<Item>> getAllItemsOfUser(@Path(value = "owner_id", encoded = true) String ownerId);

}
