package com.quangle.rentingutilities.viewmodel;

import com.google.firebase.auth.FirebaseAuth;
import com.quangle.rentingutilities.core.model.Item;
import com.quangle.rentingutilities.core.model.User;
import com.quangle.rentingutilities.networking.Api;
import com.quangle.rentingutilities.networking.NetworkResource;
import com.quangle.rentingutilities.networking.RetrofitService;

import java.util.HashMap;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserViewModel extends ViewModel {

    MutableLiveData<NetworkResource<User>> networkResourceUserMutableLiveData = new MutableLiveData<>();
    public static User loggedInUser = null;
    public static List<Item> wishlistItemOfLoggedInUser = null;

    Api api;
    private FirebaseAuth firebaseAuth;

    public UserViewModel() {
        if (api == null) {
            api = RetrofitService.get();
        }
        if (firebaseAuth == null) {
            firebaseAuth = FirebaseAuth.getInstance();
        }
    }


    public LiveData<NetworkResource<User>> create(HashMap<String, Object> params) {
        Api api = RetrofitService.get();
        Call<User> userCall = api.createUser(params);
        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful())
                    networkResourceUserMutableLiveData.setValue(new NetworkResource<>(response.body()));
                else
                    networkResourceUserMutableLiveData.setValue(new NetworkResource<>(response.code()));
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                System.out.println("ON FAILURE");
            }
        });

        return networkResourceUserMutableLiveData;
    }

    public LiveData<NetworkResource<User>> get() {
        firebaseAuth.getCurrentUser().getIdToken(false).addOnSuccessListener(getTokenResult -> {
            Call<User> userCall = api.getUser(getTokenResult.getToken());
            userCall.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.isSuccessful())
                        networkResourceUserMutableLiveData.setValue(new NetworkResource<>(response.body()));
                    else
                        networkResourceUserMutableLiveData.setValue(new NetworkResource<>(response.code()));
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    System.out.println("ON FAILURE");
                    System.out.println(t.getCause());
                }
            });
        });

        return networkResourceUserMutableLiveData;
    }

    public LiveData<NetworkResource<User>> edit(HashMap<String, Object> params) {
        firebaseAuth.getCurrentUser().getIdToken(false).addOnSuccessListener(getTokenResult -> {
            Call<User> userCall = api.editUser(getTokenResult.getToken(), params);
            userCall.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.isSuccessful())
                        networkResourceUserMutableLiveData.setValue(new NetworkResource<>(response.body()));
                    else
                        networkResourceUserMutableLiveData.setValue(new NetworkResource<>(response.code()));
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    System.out.println("ON FAILURE");
                    System.out.println(t.getCause());
                }
            });
        });

        return networkResourceUserMutableLiveData;
    }

}
