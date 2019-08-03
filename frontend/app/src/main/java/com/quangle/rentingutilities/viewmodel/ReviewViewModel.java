package com.quangle.rentingutilities.viewmodel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.JsonObject;
import com.quangle.rentingutilities.core.model.Auth;
import com.quangle.rentingutilities.core.model.Booking;
import com.quangle.rentingutilities.core.model.Item;
import com.quangle.rentingutilities.core.model.Review;
import com.quangle.rentingutilities.networking.Api;
import com.quangle.rentingutilities.networking.NetworkResource;
import com.quangle.rentingutilities.networking.RetrofitService;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewViewModel extends ViewModel {

    MutableLiveData<NetworkResource<Review>> mJSReview = new MutableLiveData<>();
    MutableLiveData<List<JsonObject>> mReviews = new MutableLiveData<>();
    Api api;
    private FirebaseAuth firebaseAuth;

    public ReviewViewModel() {
        if (api == null) {
            api = RetrofitService.get();
        }

        if (firebaseAuth ==null) {
            firebaseAuth = FirebaseAuth.getInstance();
        }
    }

    // list all reviews of item
    public LiveData<List<JsonObject>> getReviewsOfItem(String itemId) {

        firebaseAuth.getCurrentUser().getIdToken(false).addOnSuccessListener(getTokenResult -> {
            Call<List<JsonObject>> reviewCall = api.getReviewsOfItem(getTokenResult.getToken(), itemId);
            reviewCall.enqueue(new Callback<List<JsonObject>>() {
                @Override
                public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                    mReviews.setValue(response.body());
                }

                @Override
                public void onFailure(Call<List<JsonObject>> call, Throwable t) {

                }
            });
        });

        return mReviews;
    }

    // add Review
    public LiveData<NetworkResource<Review>> addReview(HashMap<String, Object> params) {

        firebaseAuth.getCurrentUser().getIdToken(false).addOnSuccessListener(getTokenResult -> {
            Api api = RetrofitService.get();
            Call<Review> reviewCall = api.addReview(getTokenResult.getToken(), params);
            reviewCall.enqueue(new Callback<Review>() {
                @Override
                public void onResponse(Call<Review> call, Response<Review> response) {
                    if (response.isSuccessful()) {
                        mJSReview.setValue(new NetworkResource<>(response.body()));
                    } else {
                        mJSReview.setValue(new NetworkResource<>(401));
                    }
                }

                @Override
                public void onFailure(Call<Review> call, Throwable t) {
                    System.out.println("ON FAILURE");
                    System.out.println(t.getStackTrace());
                }
            });
        });

        return mJSReview;
    }
}
