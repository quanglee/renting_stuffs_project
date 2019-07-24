package com.quangle.rentingutilities.viewmodel;

import com.quangle.rentingutilities.core.model.Auth;
import com.quangle.rentingutilities.core.model.Item;
import com.quangle.rentingutilities.networking.Api;
import com.quangle.rentingutilities.networking.NetworkResource;
import com.quangle.rentingutilities.networking.RetrofitService;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemViewModel extends ViewModel {
    MutableLiveData<List<Item>> mItems = new MutableLiveData<>();
    MutableLiveData<List<Item>> mUserItems = new MutableLiveData<>();//items of logged in user

    public ItemViewModel() {

    }

    public LiveData<List<Item>> getAllItems() {
        Api api = RetrofitService.get();
        Call<List<Item>> itemCall = api.getAllItems();
        itemCall.enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                mItems.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                System.out.println("ON FAILURE");
                System.out.println(t.getCause());
            }
        });

        return mItems;
    }

    public LiveData<List<Item>> getAllItemsOfUser(String ownerId) {
        Api api = RetrofitService.get();
        Call<List<Item>> itemCall = api.getAllItemsOfUser(ownerId);
        itemCall.enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                mUserItems.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                System.out.println("ON FAILURE");
                System.out.println(t.getCause());
            }
        });

        return mUserItems;
    }
}
