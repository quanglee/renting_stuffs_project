package com.quangle.rentingutilities.viewmodel;

import com.google.firebase.auth.FirebaseAuth;
import com.quangle.rentingutilities.core.model.Auth;
import com.quangle.rentingutilities.core.model.Booking;
import com.quangle.rentingutilities.core.model.Item;
import com.quangle.rentingutilities.networking.Api;
import com.quangle.rentingutilities.networking.NetworkResource;
import com.quangle.rentingutilities.networking.RetrofitService;

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

    MutableLiveData<NetworkResource<JSONObject>> mJSReview = new MutableLiveData<>();
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

    // add Review
    public LiveData<NetworkResource<JSONObject>> addReview(HashMap<String, Object> params) {
        firebaseAuth.getCurrentUser().getIdToken(false).addOnSuccessListener(getTokenResult -> {
            Api api = RetrofitService.get();
            Call<JSONObject> reviewCall = api.addReview(getTokenResult.getToken(), params);
            reviewCall.enqueue(new Callback<JSONObject>() {
                @Override
                public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                    if (response.isSuccessful()) {
                        mJSReview.setValue(new NetworkResource<>(response.body()));
                    } else {
                        mJSReview.setValue(new NetworkResource<>(401));
                    }
                }

                @Override
                public void onFailure(Call<JSONObject> call, Throwable t) {
                    System.out.println("ON FAILURE");
                    System.out.println(t.getStackTrace());
                }
            });

        });

        return mJSReview;
    }
}
