package com.quangle.rentingutilities.networking;

import com.quangle.rentingutilities.core.model.Auth;
import com.quangle.rentingutilities.core.model.Booking;
import com.quangle.rentingutilities.core.model.Item;
import com.quangle.rentingutilities.core.model.User;


import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

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

    // get all items of a user
    @GET("users/{owner_id}/items")
    Call<List<Item>> getAllItemsOfUser(@Header("Authorization") String auth, @Path(value = "owner_id", encoded = true) String ownerId);

    // get wishlist of a user
    @GET("wishlists/{owner_id}")
    Call<List<Item>> getWishListOfUser(@Path(value = "owner_id", encoded = true) String ownerId);

    // get all bookings of a particular user
    @GET("bookings/{owner_id}")
    Call<List<Booking>> getBookingsOfUser(@Header("Authorization") String auth, @Path(value = "owner_id", encoded = true) String ownerId);
}
