package com.quangle.rentingutilities.ui;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.quangle.rentingutilities.R;
import com.quangle.rentingutilities.core.model.Booking;
import com.quangle.rentingutilities.viewmodel.ItemViewModel;
import com.squareup.picasso.Picasso;

import androidx.lifecycle.ViewModelProviders;


public class BookingDetailFragment extends BaseFragment {

    private Booking booking;
    private static final String ARG_BOOKING = "booking";
    private TextView txtNameView, txtDescView, txtPriceView, txtStatusView;
    private ImageView imageView;
    private Button btnCancel;
    private ItemViewModel itemViewModel;



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

        // load booking data
        txtNameView.setText(booking.getItem().getName());
        txtDescView.setText(booking.getItem().getDescription());
        txtPriceView.setText(String.valueOf(booking.getItem().getPrice()));
        txtStatusView.setText(booking.getStatus());
        Picasso.get().load(booking.getItem().getImageURL()).resize(1000, 600).onlyScaleDown()
                .into(imageView);

        // cancel booking
        itemViewModel = ViewModelProviders.of(getActivity()).get(ItemViewModel.class);

        btnCancel.setOnClickListener(v -> {
            btnCancel.setEnabled(false);
            showProgressBar();


        });


        return view;
    }

}
