package com.quangle.rentingutilities.viewmodel;

import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.quangle.rentingutilities.core.model.Auth;
import com.quangle.rentingutilities.core.model.Booking;
import com.quangle.rentingutilities.core.model.Item;
import com.quangle.rentingutilities.networking.Api;
import com.quangle.rentingutilities.networking.NetworkResource;
import com.quangle.rentingutilities.networking.RetrofitService;
import com.quangle.rentingutilities.ui.HomeActivity;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemViewModel extends ViewModel {
    MutableLiveData<NetworkResource<Item>> networkResourceItemMutableLiveData = new MutableLiveData<>();
    MutableLiveData<List<Item>> mItems = new MutableLiveData<>();
    MutableLiveData<List<Item>> mUserItems = new MutableLiveData<>();//items of logged in user
    MutableLiveData<List<Item>> mUserWishList = new MutableLiveData<>();//wishlist of logged in user
    MutableLiveData<List<Booking>> mUserBookings = new MutableLiveData<>();//bookings of logged in user
    Api api;
    private FirebaseAuth firebaseAuth;

    public ItemViewModel() {
        if (api == null) {
            api = RetrofitService.get();
        }

        if (firebaseAuth ==null) {
            firebaseAuth = FirebaseAuth.getInstance();
        }
    }

    public LiveData<List<Item>> getAllItems() {
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

                // get token

            }
        });

        return mItems;
    }

    public LiveData<List<Item>> getAllItemsOfUser(Auth auth) {
        firebaseAuth.getCurrentUser().getIdToken(false).addOnSuccessListener(getTokenResult -> {
            // firebase will refresh this access token if it is expired.
            // TODO: I don't think we need to store access token let Firebase handle it for us.
            // TODO: I think we just make it simple for this authentication. Because we spend so much time for this
            // call api with new access token if any
            Call<List<Item>> itemCall = api.getAllItemsOfUser(getTokenResult.getToken());
            itemCall.enqueue(new Callback<List<Item>>() {
                @Override
                public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                    if (response.isSuccessful()) {
                        mUserItems.setValue(response.body());
                    } else {
                        System.out.println("DFdfd");
                    }
                }

                @Override
                public void onFailure(Call<List<Item>> call, Throwable t) {
                    System.out.println("ON FAILURE");
                    System.out.println(t.getCause());
                }
            });
        });

        return mUserItems;
    }

    public LiveData<List<Item>> getWishlistOfUser(Auth auth) {

        firebaseAuth.getCurrentUser().getIdToken(false).addOnSuccessListener(getTokenResult -> {
            Call<List<Item>> itemCall = api.getWishlistOfUser(getTokenResult.getToken());
            itemCall.enqueue(new Callback<List<Item>>() {
                @Override
                public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                    if (response.isSuccessful()) {
                        mUserWishList.setValue(response.body());
                    } else {
                        // TODO: Token Expired, Display message Redirect to LOGIN activity
                    }
                }

                @Override
                public void onFailure(Call<List<Item>> call, Throwable t) {
                    System.out.println("ON FAILURE");
                    System.out.println(t.getCause());
                }
            });
        });

        return mUserWishList;
    }

    public LiveData<List<Booking>> getUserBookings(Auth auth) {

        firebaseAuth.getCurrentUser().getIdToken(false).addOnSuccessListener(getTokenResult -> {
            Call<List<Booking>> bookingCall = api.getBookingsOfUser(getTokenResult.getToken());
            bookingCall.enqueue(new Callback<List<Booking>>() {
                @Override
                public void onResponse(Call<List<Booking>> call, Response<List<Booking>> response) {
                    getBookingsWithItemDetail(getTokenResult.getToken(), response.body());
                }

                @Override
                public void onFailure(Call<List<Booking>> call, Throwable t) {
                    System.out.println("ON FAILURE");
                    System.out.println(t.getCause());
                }
            });
        });

        return mUserBookings;
    }

    private void getBookingsWithItemDetail(String accessToken, List<Booking> bookings) {
        List<Booking> tempBookings = new ArrayList<>();

        for(Booking b: bookings) {
            Booking temp = new Booking();
            temp.setId(b.getId());
            temp.setBorrowerId(b.getBorrowerId());
            temp.setItemId(b.getItemId());
            temp.setStartDate(b.getStartDate());
            temp.setReturnDate(b.getReturnDate());
            temp.setStatus(b.getStatus());

            Call<Item> callItem = api.getItemDetail(accessToken, String.valueOf(b.getItemId()));
            callItem.enqueue(new Callback<Item>() {
                @Override
                public void onResponse(Call<Item> call, Response<Item> response) {
                    temp.setItem(response.body());
                    tempBookings.add(temp);
                    mUserBookings.setValue(tempBookings);
                }

                @Override
                public void onFailure(Call<Item> call, Throwable t) {
                    System.out.println("HERE FAILURE");
                    System.out.println(t.getCause());
                }
            });
            System.out.println("HERE " + String.valueOf(b.getItemId()));
        }

    }

    public LiveData<NetworkResource<Item>> createItem(Auth auth, MultipartBody.Part filePart, HashMap<String, RequestBody> params) {
        Api api = RetrofitService.get();
        Call<Item> itemCall = api.createItem(auth.getAccessToken(), filePart, params);
        itemCall.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                if (response.isSuccessful())
                    networkResourceItemMutableLiveData.setValue(new NetworkResource<>(response.body()));
                else
                    networkResourceItemMutableLiveData.setValue(new NetworkResource<>(response.code()));
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {
                System.out.println("ON FAILURE");
                System.out.println(t.getStackTrace());
            }
        });

        return networkResourceItemMutableLiveData;
    }
}
