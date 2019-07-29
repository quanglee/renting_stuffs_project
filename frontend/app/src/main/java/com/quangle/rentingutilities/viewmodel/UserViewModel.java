package com.quangle.rentingutilities.viewmodel;

import com.quangle.rentingutilities.core.model.Auth;
import com.quangle.rentingutilities.core.model.User;
import com.quangle.rentingutilities.networking.Api;
import com.quangle.rentingutilities.networking.NetworkResource;
import com.quangle.rentingutilities.networking.RetrofitService;

import java.util.HashMap;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserViewModel extends ViewModel {

    MutableLiveData<NetworkResource<User>> networkResourceUserMutableLiveData = new MutableLiveData<>();
    Api api;

    public UserViewModel() {
        if (api == null) {
            api = RetrofitService.get();
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

    public LiveData<NetworkResource<User>> get(Auth auth) {
        // TODO: there are two options
        // 1. If token expired, clear all share preference

        Call<User> userCall = api.getUser(auth.getAccessToken());
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

        return networkResourceUserMutableLiveData;
    }

    public LiveData<NetworkResource<User>> edit(Auth auth, HashMap<String, Object> params) {
        Call<User> userCall = api.editUser(auth.getAccessToken(), params);
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

        return networkResourceUserMutableLiveData;
    }
}
