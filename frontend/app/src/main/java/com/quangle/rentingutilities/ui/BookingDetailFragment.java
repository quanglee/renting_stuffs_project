package com.quangle.rentingutilities.ui;


import android.content.Intent;
import android.os.Bundle;
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
import com.quangle.rentingutilities.utils.Helper;
import com.quangle.rentingutilities.viewmodel.BookingViewModel;
import com.quangle.rentingutilities.viewmodel.ItemViewModel;
import com.quangle.rentingutilities.viewmodel.ReviewViewModel;
import com.quangle.rentingutilities.viewmodel.UserViewModel;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.HashMap;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;


public class BookingDetailFragment extends BaseFragment {

    private Booking booking;
    private static final String ARG_BOOKING = "booking";
    private TextView txtNameView, txtDescView, txtPriceView, txtStatusView, txtStartDate, txtReturnDate;
    private ImageView imageView;
    private Button btnCancel, btnReview, btnAccept, btnReject, btnDone;
    private ItemViewModel itemViewModel;
    private BookingViewModel bookingViewModel;
//    private ReviewViewModel reviewViewModel;



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
        txtStartDate = view.findViewById(R.id.txtStartDate);
        txtReturnDate = view.findViewById(R.id.txtReturnDate);
        imageView = view.findViewById(R.id.itemImage);
        btnCancel = view.findViewById(R.id.btnCancelBooking);
//        btnReview = view.findViewById(R.id.btnReviewBooking);
        btnAccept = view.findViewById(R.id.btnAcceptBooking);
        btnReject = view.findViewById(R.id.btnRejectBooking);
        btnDone = view.findViewById(R.id.btnDoneBooking);

        // load booking data
        txtNameView.setText(booking.getItem().getName());
        txtDescView.setText(booking.getItem().getDescription());
        txtPriceView.setText("$" + String.valueOf(booking.getItem().getPrice()));
        txtStatusView.setText(booking.getStatus());
        String pattern = "MM-dd-yyyy HH:mm";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String startDate = simpleDateFormat.format(booking.getStartDate());
        String returnDate = simpleDateFormat.format(booking.getReturnDate());
        txtStartDate.setText(startDate);
        txtReturnDate.setText(returnDate);

        Picasso.get().load(booking.getItem().getImageURL()).resize(1000, 600).onlyScaleDown()
                .into(imageView);

        btnCancel.setVisibility(View.GONE);
//        btnReview.setVisibility(View.GONE);
        btnAccept.setVisibility(View.GONE);
        btnReject.setVisibility(View.GONE);
        btnDone.setVisibility(View.GONE);

        // check to display approriate button
        if (booking.getStatus().equals(Booking.STATUS.PENDING.displayName())) {

            if(Helper.isUserLoggedIn() && //must check user is logging first
                    Helper.isLoggedInUserEmailMatch(booking.getBorrowerId()))//borrower
                btnCancel.setVisibility(View.VISIBLE);
            else{//owner
                btnAccept.setVisibility(View.VISIBLE);
                btnReject.setVisibility(View.VISIBLE);
            }

        } else if (booking.getStatus().equals(Booking.STATUS.DONE.displayName())) {

            //TODO: display if have no review yet
            if(Helper.isUserLoggedIn() && //must check user is logging first
                    Helper.isLoggedInUserEmailMatch(booking.getBorrowerId())) {
                AddReviewFragment fragmentDisplay = new AddReviewFragment();
                getChildFragmentManager().beginTransaction()
                        .replace(R.id.fragmentDisplayInBookingDetail, fragmentDisplay)
                        .commit();
            }
//                btnReview.setVisibility(View.VISIBLE);
        } else if(booking.getStatus().equals(Booking.STATUS.ACCEPTED.displayName())) {

            if(Helper.isUserLoggedIn() && //must check user is logging first
                    Helper.isLoggedInUserEmailMatch(booking.getBorrowerId()) == false)//owner
                btnDone.setVisibility(View.VISIBLE);
        }else{
            // do not display any buttons
        }

        // cancel booking
        itemViewModel = ViewModelProviders.of(getActivity()).get(ItemViewModel.class);
        bookingViewModel = ViewModelProviders.of(getActivity()).get(BookingViewModel.class);
//        reviewViewModel = ViewModelProviders.of(getActivity()).get(ReviewViewModel.class);

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
                    BaseActivity.startHomeActivityAtTab(R.id.bookings);
                }
            });
        });

        //accept booking
        btnAccept.setOnClickListener(v -> {
            btnAccept.setEnabled(false);
            showProgressBar();

            bookingViewModel.acceptBooking(booking.toHashMap()).observe(this, authNetworkResource -> {
                hideProgressBar();
                btnAccept.setEnabled(true);

                if (authNetworkResource.code == 401) {
                    Toast.makeText(getContext(), "Accept Booking Fail!", Toast.LENGTH_SHORT).show();
                } else if (authNetworkResource.data != null) {

                    //return to Items tab / Requests top nav
                    BaseActivity.startHomeActivityAtTab(R.id.items, HomeActivity.REQUESTS_TOPNAV_INDEX);
                }
            });
        });


//        btnReview.setOnClickListener(v -> {
//
//            AddReviewFragment fragmentDisplay = new AddReviewFragment();
//            getChildFragmentManager().beginTransaction()
//                    .replace(R.id.fragmentDisplayInBookingDetail, fragmentDisplay)
//                    .commit();
//
//            btnReview.setVisibility(View.GONE);
////            HashMap<String, Object> params = new HashMap<>();
////            params.put("itemId", booking.getItem().getId());
////            params.put("borrowerId", booking.getItem().getId());
////            params.put("title", booking.getItem().getId());
////            params.put("content", booking.getItem().getId());
//        });

        //reject booking
        btnReject.setOnClickListener(v -> {
            btnReject.setEnabled(false);
            showProgressBar();

            bookingViewModel.rejectBooking(booking.toHashMap()).observe(this, authNetworkResource -> {
                hideProgressBar();
                btnReject.setEnabled(true);

                if (authNetworkResource.code == 401) {
                    Toast.makeText(getContext(), "Reject Booking Fail!", Toast.LENGTH_SHORT).show();
                } else if (authNetworkResource.data != null) {

                    //return to Items tab / Requests top nav
                    BaseActivity.startHomeActivityAtTab(R.id.items, HomeActivity.REQUESTS_TOPNAV_INDEX);
                }
            });
        });

        //done booking
        btnDone.setOnClickListener(v -> {
            btnDone.setEnabled(false);
            showProgressBar();

            bookingViewModel.doneBooking(booking.toHashMap()).observe(this, authNetworkResource -> {
                hideProgressBar();
                btnDone.setEnabled(true);

                if (authNetworkResource.code == 401) {
                    Toast.makeText(getContext(), "Finish Booking Fail!", Toast.LENGTH_SHORT).show();
                } else if (authNetworkResource.data != null) {

                    //return to Items tab / Requests top nav
                    BaseActivity.startHomeActivityAtTab(R.id.items, HomeActivity.REQUESTS_TOPNAV_INDEX);

                }
            });
        });

        return view;
    }

}
