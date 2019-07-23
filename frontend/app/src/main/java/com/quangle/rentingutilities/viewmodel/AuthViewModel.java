package com.quangle.rentingutilities.viewmodel;

import com.quangle.rentingutilities.core.model.Auth;
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

public class AuthViewModel extends ViewModel {

    MutableLiveData<NetworkResource<Auth>> networkResourceAuthMutableLiveData = new MutableLiveData<>();

    public LiveData<NetworkResource<Auth>> login(HashMap<String, String> params) {
        Api api = RetrofitService.get();
        Call<Auth> authCall = api.login(params);
        authCall.enqueue(new Callback<Auth>() {
            @Override
            public void onResponse(Call<Auth> call, Response<Auth> response) {
                if (response.isSuccessful())
                    networkResourceAuthMutableLiveData.setValue(new NetworkResource<>(response.body()));
                else
                    networkResourceAuthMutableLiveData.setValue(new NetworkResource<>(response.code()));
            }

            @Override
            public void onFailure(Call<Auth> call, Throwable t) {
                System.out.println("ON FAILURE");
            }
        });

        return networkResourceAuthMutableLiveData;
    }
}
