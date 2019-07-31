package com.quangle.rentingutilities.ui;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.quangle.rentingutilities.R;
import com.quangle.rentingutilities.core.model.Auth;
import com.quangle.rentingutilities.core.model.Booking;
import com.quangle.rentingutilities.customAdapter.BookingAdapter;
import com.quangle.rentingutilities.utils.MySharedPreferences;
import com.quangle.rentingutilities.viewmodel.BookingViewModel;
import com.quangle.rentingutilities.viewmodel.ItemViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

public class BookingsFragment extends BaseFragment {

    ItemViewModel itemViewModel;
    BookingViewModel bookingViewModel;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bookings, container, false);

        showProgressBar();

        // display all user's bookings.
        recyclerView = view.findViewById(R.id.booking_items_list);
        assert recyclerView != null;
        setupRecyclerView(recyclerView);

        return view;
    }

    private void setupRecyclerView(@NonNull RecyclerView tempRecyclerView) {
        final BookingAdapter adapter = new BookingAdapter(getContext(), booking -> {
            Intent intent = new Intent(getActivity(), BookingActivity.class);
            intent.putExtra("fragmentOptions", "bookingDetail");
            BookingActivity.setItem(intent,booking);
            startActivity(intent);
        });
        tempRecyclerView.setAdapter(adapter);

        // get email of logged in user
        Auth auth = MySharedPreferences.getAuth(getContext());

//        itemViewModel = ViewModelProviders.of(this).get(ItemViewModel.class);
//        itemViewModel.getUserBookings(auth).observe(this, new Observer<List<Booking>>() {
//            @Override
//            public void onChanged(List<Booking> bookings) {
//                hideProgressBar();
//                adapter.setNewBookings(bookings);
//            }
//        });

        bookingViewModel = ViewModelProviders.of(this).get(BookingViewModel.class);
        bookingViewModel.getUserBookings(auth).observe(this, new Observer<List<Booking>>() {
            @Override
            public void onChanged(List<Booking> bookings) {
                hideProgressBar();
                adapter.setNewBookings(bookings);
            }
        });
    }

}
