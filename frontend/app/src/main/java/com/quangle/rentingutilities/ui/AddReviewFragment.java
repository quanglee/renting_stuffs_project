package com.quangle.rentingutilities.ui;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.quangle.rentingutilities.R;


public class AddReviewFragment extends BaseFragment {

    private Button btnCancel, btnReview;


    public AddReviewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_review, container, false);


//        btnCancel.setOnClickListener(v -> {
//            // close this fragment
//            getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
////            getFragmentManager().beginTransaction().
//        });

        return view;
    }

}
