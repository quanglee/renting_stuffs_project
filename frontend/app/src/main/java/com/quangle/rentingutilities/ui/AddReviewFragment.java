package com.quangle.rentingutilities.ui;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.quangle.rentingutilities.R;
import com.quangle.rentingutilities.core.model.Booking;
import com.quangle.rentingutilities.viewmodel.ReviewViewModel;

import java.util.HashMap;

import androidx.lifecycle.ViewModelProviders;


public class AddReviewFragment extends BaseFragment {

    private Button btnReview;
    private RatingBar ratingBar;
    private EditText txtTitle, txtContent;
    private double ratingStar = 4.0; // default, please update ratingbar as same as otherwise it will error
    private ReviewViewModel reviewViewModel;


    public AddReviewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_review, container, false);
        btnReview = view.findViewById(R.id.btnReview);
        ratingBar = view.findViewById(R.id.ratingBar);
        txtTitle = view.findViewById(R.id.etTitle);
        txtContent = view.findViewById(R.id.etDescription);
        reviewViewModel = ViewModelProviders.of(getActivity()).get(ReviewViewModel.class);

        // get booking detail
        Booking booking = (Booking) getArguments().getSerializable("booking");

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ratingStar = rating;
            }
        });

        btnReview.setOnClickListener(v -> {
            HashMap<String, Object> params = new HashMap<>();
            params.put("itemId", booking.getItem().getId());
            params.put("borrowerId", booking.getBorrowerId() );
            params.put("title", txtTitle.getText().toString());
            params.put("content", txtContent.getText().toString());
            params.put("rating", ratingStar);
            params.put("bookingId", booking.getId());


            btnReview.setEnabled(false);
            reviewViewModel.addReview(params).observe(this, authNetworkResource -> {
                hideProgressBar();
                btnReview.setEnabled(true);
                if (authNetworkResource.code == 401) {
                    Toast.makeText(getContext(), "Error occurred during add review. Please try again", Toast.LENGTH_SHORT).show();
                } else if (authNetworkResource.data != null) {
                    Toast.makeText(getContext(), "Review is added. Please go to Review Items tab for detail", Toast.LENGTH_SHORT).show();
                    getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
                }
            });

        });

        return view;
    }

}
