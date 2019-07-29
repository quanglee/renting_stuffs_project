package com.quangle.rentingutilities.networking;

import com.quangle.rentingutilities.core.model.Auth;
import com.quangle.rentingutilities.core.model.Booking;
import com.quangle.rentingutilities.core.model.Item;
import com.quangle.rentingutilities.core.model.User;


import java.util.HashMap;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;

public interface Api {

    @POST("auth/login")
    Call<Auth> login(@Body HashMap<String, Object> params);

    //Users
    @POST("users/create")
    Call<User> createUser(@Body HashMap<String, Object> params);

    @GET("users/")
    Call<User> getUser(@Header("Authorization") String auth);

    @POST("users/edit")
    Call<User> editUser(@Header("Authorization") String auth, @Body HashMap<String, Object> params);

    // get all items of a user
    @GET("users/items")
    Call<List<Item>> getAllItemsOfUser(@Header("Authorization") String auth);

    //get all items in wishlist of a user
    @GET("users/wishlist")
    Call<List<Item>> getWishlistOfUser(@Header("Authorization") String auth);

    // get all bookings of a particular user
    @GET("/users/bookings")
    Call<List<Booking>> getBookingsOfUser(@Header("Authorization") String auth);

    //Items
    // get all items for home page
    @GET("items")
    Call<List<Item>> getAllItems();

    // get item details
    @GET("items/{item_id}")
    Call<Item> getItemDetail(@Header("Authorization") String auth, @Path(value = "item_id", encoded = true) String itemId);

    // add new item
    @Multipart
    @POST("items/add")
    Call<Item> createItem(@Header("Authorization") String auth, @Part MultipartBody.Part file, @PartMap() HashMap<String, RequestBody> params);

    //Bookings
    //create a booking
    @POST("bookings/create")
    Call<User> createBooking(@Body HashMap<String, Object> params);
}
