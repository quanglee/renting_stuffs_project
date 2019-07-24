package com.quangle.rentingutilities.viewmodel;

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

    MutableLiveData<NetworkResource<User>> networkResourceAuthMutableLiveData = new MutableLiveData<>();

    public LiveData<NetworkResource<User>> create(HashMap<String, String> params) {
        Api api = RetrofitService.get();
        Call<User> userCall = api.createUser(params);
        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful())
                    networkResourceAuthMutableLiveData.setValue(new NetworkResource<>(response.body()));
                else
                    networkResourceAuthMutableLiveData.setValue(new NetworkResource<>(response.code()));
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                System.out.println("ON FAILURE");
            }
        });

        return networkResourceAuthMutableLiveData;
    }
}
