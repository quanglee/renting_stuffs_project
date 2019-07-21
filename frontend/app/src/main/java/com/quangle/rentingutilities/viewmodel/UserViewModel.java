package com.quangle.rentingutilities.viewmodel;

import com.quangle.rentingutilities.core.model.User;
import com.quangle.rentingutilities.networking.Api;
import com.quangle.rentingutilities.networking.NetworkResource;
import com.quangle.rentingutilities.networking.RetrofitService;
import com.quangle.rentingutilities.utils.Helper;

import java.util.HashMap;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserViewModel extends ViewModel {

    MutableLiveData<NetworkResource<User>> networkResourceUserMutableLiveData = new MutableLiveData<>();

    public LiveData<NetworkResource<User>> login(HashMap<String, String> params) {
        Api api = RetrofitService.get();
        Call<User> userCall = api.login(params);
        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful())
                    networkResourceUserMutableLiveData.setValue(new NetworkResource<>(response.body()));
                else
                    networkResourceUserMutableLiveData.setValue(new NetworkResource<User>(response.code()));
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

        return networkResourceUserMutableLiveData;
    }
}
