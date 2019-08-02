package com.quangle.rentingutilities.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.quangle.rentingutilities.core.model.Wishlist;
import com.quangle.rentingutilities.networking.Api;
import com.quangle.rentingutilities.networking.NetworkResource;
import com.quangle.rentingutilities.networking.RetrofitService;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WishlistViewModel extends ViewModel {

    MutableLiveData<NetworkResource<Wishlist>> networkResourceWishlistMutableLiveData = new MutableLiveData<>();
    Api api;
    private FirebaseAuth firebaseAuth;

    public WishlistViewModel() {
        if (api == null) {
            api = RetrofitService.get();
        }

        if (firebaseAuth ==null) {
            firebaseAuth = FirebaseAuth.getInstance();
        }
    }

    public LiveData<NetworkResource<Wishlist>> create(HashMap<String, Object> params) {
        firebaseAuth.getCurrentUser().getIdToken(false).addOnSuccessListener(getTokenResult -> {
            Call<Wishlist> wishlistCall = api.createWishlist(getTokenResult.getToken(), params);
            wishlistCall.enqueue(new Callback<Wishlist>() {
                @Override
                public void onResponse(Call<Wishlist> call, Response<Wishlist> response) {
                    if (response.isSuccessful())
                        networkResourceWishlistMutableLiveData.setValue(new NetworkResource<>(response.body()));
                    else
                        networkResourceWishlistMutableLiveData.setValue(new NetworkResource<>(response.code()));
                }

                @Override
                public void onFailure(Call<Wishlist> call, Throwable t) {
                    System.out.println("ON FAILURE");
                }
            });
        });

        return networkResourceWishlistMutableLiveData;
    }
}
