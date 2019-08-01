package com.quangle.rentingutilities.ui;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.quangle.rentingutilities.R;
import com.quangle.rentingutilities.core.model.Auth;
import com.quangle.rentingutilities.core.model.Booking;
import com.quangle.rentingutilities.viewmodel.BookingViewModel;
import com.quangle.rentingutilities.viewmodel.ItemViewModel;
import com.squareup.picasso.Picasso;

import androidx.lifecycle.ViewModelProviders;


public class BookingDetailFragment extends BaseFragment {

    private Booking booking;
    private static final String ARG_BOOKING = "booking";
    private TextView txtNameView, txtDescView, txtPriceView, txtStatusView;
    private ImageView imageView;
    private Button btnCancel, btnReview, btnAccept, btnReject, btnDone;
    private ItemViewModel itemViewModel;
    private BookingViewModel bookingViewModel;



    public BookingDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            booking = (Booking) getArguments().getSerializable(ARG_BOOKING);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_booking_detail, container, false);
        txtNameView = view.findViewById(R.id.txtName);
        txtDescView = view.findViewById(R.id.txtDesc);
        txtPriceView = view.findViewById(R.id.txtPrice);
        txtStatusView = view.findViewById(R.id.txtStatus);
        imageView = view.findViewById(R.id.itemImage);
        btnCancel = view.findViewById(R.id.btnCancelBooking);
        btnReview = view.findViewById(R.id.btnReviewBooking);
        btnAccept = view.findViewById(R.id.btnAcceptBooking);
        btnReject = view.findViewById(R.id.btnRejectBooking);
        btnDone = view.findViewById(R.id.btnDoneBooking);

        // load booking data
        txtNameView.setText(booking.getItem().getName());
        txtDescView.setText(booking.getItem().getDescription());
        txtPriceView.setText("$" + String.valueOf(booking.getItem().getPrice()));
        txtStatusView.setText(booking.getStatus());
        Picasso.get().load(booking.getItem().getImageURL()).resize(1000, 600).onlyScaleDown()
                .into(imageView);

        btnCancel.setVisibility(View.INVISIBLE);
        btnReview.setVisibility(View.INVISIBLE);
        btnAccept.setVisibility(View.INVISIBLE);
        btnReject.setVisibility(View.INVISIBLE);
        btnDone.setVisibility(View.INVISIBLE);

        // check to display approriate button
        if (booking.getStatus().equals(Booking.STATUS.PENDING.displayName())) {
            //TODO: display if user is borrower
            btnCancel.setVisibility(View.VISIBLE);

            //TODO: display if user is owner of the item
            btnAccept.setVisibility(View.VISIBLE);
            btnReject.setVisibility(View.VISIBLE);

        } else if (booking.getStatus().equals(Booking.STATUS.DONE.displayName())) {

            //TODO: display if user is borrower and have no review yet
            btnReview.setVisibility(View.VISIBLE);
        } else if(booking.getStatus().equals(Booking.STATUS.ACCEPTED.displayName())) {

            //TODO: display if user is owner of the item
            btnDone.setVisibility(View.VISIBLE);
        }else{
            // do not display any buttons
        }

        // cancel booking
        itemViewModel = ViewModelProviders.of(getActivity()).get(ItemViewModel.class);
        bookingViewModel = ViewModelProviders.of(getActivity()).get(BookingViewModel.class);

        btnCancel.setOnClickListener(v -> {
            btnCancel.setEnabled(false);
            showProgressBar();

            itemViewModel.cancelBooking(String.valueOf(booking.getId())).observe(this, authNetworkResource -> {
                hideProgressBar();
                btnCancel.setEnabled(true);

                if (authNetworkResource.code == 401) {
                    Toast.makeText(getContext(), "Cancel Booking Fail!", Toast.LENGTH_SHORT).show();
                } else if (authNetworkResource.data != null) {

                    //return to booking tab
                    BaseActivity.startHomeActivityAtTab(HomeActivity.BOOKINGS_TAB_INDEX);
                }
            });
        });

        btnAccept.setOnClickListener(v -> {
            btnAccept.setEnabled(false);
            showProgressBar();

            bookingViewModel.cancelBooking(String.valueOf(booking.getId())).observe(this, authNetworkResource -> {
                hideProgressBar();
                btnAccept.setEnabled(true);

                if (authNetworkResource.code == 401) {
                    Toast.makeText(getContext(), "Cancel Booking Fail!", Toast.LENGTH_SHORT).show();
                } else if (authNetworkResource.data != null) {

                    //return to booking tab
                    BaseActivity.startHomeActivityAtTab(HomeActivity.BOOKINGS_TAB_INDEX);
                }
            });
        });


        return view;
    }

}
