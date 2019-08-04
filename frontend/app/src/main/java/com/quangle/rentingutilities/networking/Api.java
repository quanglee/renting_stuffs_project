package com.quangle.rentingutilities.networking;

import com.google.gson.JsonObject;
import com.quangle.rentingutilities.core.model.Auth;
import com.quangle.rentingutilities.core.model.Booking;
import com.quangle.rentingutilities.core.model.Item;
import com.quangle.rentingutilities.core.model.Review;
import com.quangle.rentingutilities.core.model.User;
import com.quangle.rentingutilities.core.model.Wishlist;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;

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

    // get all requests (bookings send to the user) of a particular user
    @GET("/users/requests")
    Call<List<Booking>> getRequestsOfUser(@Header("Authorization") String auth);

    //Items
    // get all items for home page
    @GET("items")
    Call<List<Item>> getAllItems(@Query("itemname") String itemName);

    // get item details
    @GET("items/{item_id}")
    Call<Item> getItemDetail(@Header("Authorization") String auth, @Path(value = "item_id", encoded = true) String itemId);

    // get all reviews of item
    @GET("items/{item_id}/reviews")
    Call<List<JsonObject>> getReviewsOfItem(@Path(value = "item_id", encoded = true) String itemId);

    // add new item
    @Multipart
    @POST("items/add")
    Call<Item> createItem(@Header("Authorization") String auth, @Part MultipartBody.Part file, @PartMap() HashMap<String, RequestBody> params);

    //Edit item
    @Multipart
    @PUT("items/edit/{item_id}")
    Call<Item> editItem(@Header("Authorization") String auth, @Path(value = "item_id") String itemId, @Part MultipartBody.Part file, @PartMap() HashMap<String, RequestBody> params);

    //Bookings
    //create a booking
    @POST("bookings/create")
    Call<Booking> createBooking(@Header("Authorization") String auth, @Body HashMap<String, Object> params);

    //Bookings
    //create a booking
    @DELETE("bookings/delete/{booking_id}")
    Call<JSONObject> cancelBooking(@Header("Authorization") String auth, @Path(value = "booking_id", encoded = true) String bookingId);

    //Reviews
    //add a review
    @POST("reviews/create")
    Call<Review> addReview(@Header("Authorization") String auth, @Body HashMap<String, Object> params);

    //accept a booking
    @PUT("bookings/accept/{booking_id}")
    Call<Booking> acceptBooking(@Header("Authorization") String auth, @Path(value = "booking_id", encoded = true) String bookingId, @Body HashMap<String, Object> params);

    //reject a booking
    @PUT("bookings/reject/{booking_id}")
    Call<Booking> rejectBooking(@Header("Authorization") String auth, @Path(value = "booking_id", encoded = true) String bookingId, @Body HashMap<String, Object> params);

    //done a booking
    @PUT("bookings/done/{booking_id}")
    Call<Booking> doneBooking(@Header("Authorization") String auth, @Path(value = "booking_id", encoded = true) String bookingId, @Body HashMap<String, Object> params);

    //Wishlist
    //Add to your wishlist
    @POST("wishlists/create")
    Call<Wishlist> createWishlist(@Header("Authorization") String auth, @Body HashMap<String, Object> params);

    //Remove to your wishlist
    @POST("wishlists/delete")
    Call<Wishlist> deleteWishlist(@Header("Authorization") String auth, @Body HashMap<String, Object> params);

}
