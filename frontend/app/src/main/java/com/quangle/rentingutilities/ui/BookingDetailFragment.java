package com.quangle.rentingutilities.ui;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.quangle.rentingutilities.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookingDetailFragment extends BaseFragment {


    public BookingDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            item = (Item) getArguments().getSerializable(ARG_ITEM);
//            canEdit = getArguments().getBoolean(ARG_CAN_EDIT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_booking_detail, container, false);

        return view;
    }

}
