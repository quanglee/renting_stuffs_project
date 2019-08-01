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

public class BookingViewModel extends ViewModel {

    MutableLiveData<List<Booking>> mUserBookings = new MutableLiveData<>();//bookings of logged in user
    MutableLiveData<List<Booking>> mUserRequests = new MutableLiveData<>();//requests of logged in user
    MutableLiveData<NetworkResource<Booking>> networkResourceBookingMutableLiveData = new MutableLiveData<>();
    MutableLiveData<NetworkResource<JSONObject>> networkResourceJSONObjectMutableLiveData = new MutableLiveData<>();
    Api api;
    private FirebaseAuth firebaseAuth;

    public BookingViewModel() {
        if (api == null) {
            api = RetrofitService.get();
        }

        if (firebaseAuth ==null) {
            firebaseAuth = FirebaseAuth.getInstance();
        }
    }

    public LiveData<List<Booking>> getUserBookings(Auth auth) {

        firebaseAuth.getCurrentUser().getIdToken(false).addOnSuccessListener(getTokenResult -> {
            Call<List<Booking>> bookingCall = api.getBookingsOfUser(getTokenResult.getToken());
            bookingCall.enqueue(new Callback<List<Booking>>() {
                @Override
                public void onResponse(Call<List<Booking>> call, Response<List<Booking>> response) {

                    if(response.body().size() == 0)
                        mUserBookings.setValue(new ArrayList<>());
                    else
                        getBookingsWithItemDetail(getTokenResult.getToken(), mUserBookings, response.body());
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

    public LiveData<List<Booking>> getUserRequests(Auth auth) {

        firebaseAuth.getCurrentUser().getIdToken(false).addOnSuccessListener(getTokenResult -> {
            Call<List<Booking>> bookingCall = api.getRequestsOfUser(getTokenResult.getToken());
            bookingCall.enqueue(new Callback<List<Booking>>() {
                @Override
                public void onResponse(Call<List<Booking>> call, Response<List<Booking>> response) {

                    if(response.body().size() == 0)
                        mUserRequests.setValue(new ArrayList<>());
                    else
                        getBookingsWithItemDetail(getTokenResult.getToken(), mUserRequests, response.body());
                }

                @Override
                public void onFailure(Call<List<Booking>> call, Throwable t) {
                    System.out.println("ON FAILURE");
                    System.out.println(t.getCause());
                }
            });
        });

        return mUserRequests;
    }

    private void getBookingsWithItemDetail(String accessToken,
                                           MutableLiveData<List<Booking>> mBookings,
                                           List<Booking> bookings) {
        final int[] bookingIndex = {0};

        for(Booking b: bookings) {

            Call<Item> callItem = api.getItemDetail(accessToken, String.valueOf(b.getItemId()));
            callItem.enqueue(new Callback<Item>() {
                @Override
                public void onResponse(Call<Item> call, Response<Item> response) {

                    b.setItem(response.body());//update Item

                    bookingIndex[0]++;//current index of booking

                    //update when process all bookings
                    if(bookingIndex[0] == bookings.size())
                        mBookings.postValue(bookings);
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

    //create booking
    public LiveData<NetworkResource<Booking>> createBooking(HashMap<String, Object> params) {
        firebaseAuth.getCurrentUser().getIdToken(false).addOnSuccessListener(getTokenResult -> {
            Api api = RetrofitService.get();
            Call<Booking> bookingCall = api.createBooking(getTokenResult.getToken(), params);
            bookingCall.enqueue(new Callback<Booking>() {
                @Override
                public void onResponse(Call<Booking> call, Response<Booking> response) {
                    if (response.isSuccessful())
                        networkResourceBookingMutableLiveData.setValue(new NetworkResource<>(response.body()));
                    else
                        networkResourceBookingMutableLiveData.setValue(new NetworkResource<>(response.code()));
                }

                @Override
                public void onFailure(Call<Booking> call, Throwable t) {
                    System.out.println("ON FAILURE");
                    System.out.println(t.getStackTrace());
                }
            });
        });

        return networkResourceBookingMutableLiveData;
    }

    // cancel booking
    public LiveData<NetworkResource<JSONObject>> cancelBooking(String bookingID) {
        // call api to cancel booking
        firebaseAuth.getCurrentUser().getIdToken(false).addOnSuccessListener(getTokenResult -> {
            Api api = RetrofitService.get();
            Call<JSONObject> bookingCall = api.cancelBooking(getTokenResult.getToken(), bookingID);
            bookingCall.enqueue(new Callback<JSONObject>() {
                @Override
                public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                    if (response.isSuccessful()) {
                        networkResourceJSONObjectMutableLiveData.setValue(new NetworkResource<>(response.body()));
                    } else {
                        networkResourceJSONObjectMutableLiveData.setValue(new NetworkResource<>(401));
                    }
                }

                @Override
                public void onFailure(Call<JSONObject> call, Throwable t) {
                    System.out.println("ON FAILURE");
                    System.out.println(t.getStackTrace());
                }
            });

        });

        return networkResourceJSONObjectMutableLiveData;
    }

    //accept booking
    public LiveData<NetworkResource<Booking>> acceptBooking(HashMap<String, Object> params) {
        firebaseAuth.getCurrentUser().getIdToken(false).addOnSuccessListener(getTokenResult -> {
            Api api = RetrofitService.get();
//            Call<Booking> bookingCall = api.acceptBooking(getTokenResult.getToken(), params);
//            bookingCall.enqueue(new Callback<Booking>() {
//                @Override
//                public void onResponse(Call<Booking> call, Response<Booking> response) {
//                    if (response.isSuccessful())
//                        networkResourceBookingMutableLiveData.setValue(new NetworkResource<>(response.body()));
//                    else
//                        networkResourceBookingMutableLiveData.setValue(new NetworkResource<>(response.code()));
//                }
//
//                @Override
//                public void onFailure(Call<Booking> call, Throwable t) {
//                    System.out.println("ON FAILURE");
//                    System.out.println(t.getStackTrace());
//                }
//            });
        });

        return networkResourceBookingMutableLiveData;
    }
}
