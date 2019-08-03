package com.quangle.rentingutilities.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.JsonObject;
import com.quangle.rentingutilities.R;
import com.quangle.rentingutilities.core.model.Auth;
import com.quangle.rentingutilities.core.model.Booking;
import com.quangle.rentingutilities.core.model.Item;
import com.quangle.rentingutilities.customAdapter.BookingAdapter;
import com.quangle.rentingutilities.customAdapter.ReviewAdapter;
import com.quangle.rentingutilities.utils.MySharedPreferences;
import com.quangle.rentingutilities.viewmodel.BookingViewModel;
import com.quangle.rentingutilities.viewmodel.ReviewViewModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

public class ItemReviewsFragment extends BaseFragment {

    private static final String ARG_ITEM = "item";
    private Item item;
    private RecyclerView recyclerView;
    private ReviewViewModel reviewViewModel;

    public static ItemReviewsFragment newInstance(Item item) {
        ItemReviewsFragment fragment = new ItemReviewsFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_ITEM, item);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            item = (Item) getArguments().getSerializable(ARG_ITEM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_review_items, container, false);

        // display all user's bookings.
        recyclerView = view.findViewById(R.id.review_items_list);
        assert recyclerView != null;
        setupRecyclerView(recyclerView);
        return view;
    }

    private void setupRecyclerView(@NonNull RecyclerView tempRecyclerView) {
        final ReviewAdapter adapter = new ReviewAdapter(getContext());
        tempRecyclerView.setAdapter(adapter);
        showProgressBar();
        reviewViewModel = ViewModelProviders.of(this).get(ReviewViewModel.class);
        reviewViewModel.getReviewsOfItem(String.valueOf(item.getId())).observe(this, new Observer<List<JsonObject>>() {
            @Override
            public void onChanged(List<JsonObject> reviews) {
                hideProgressBar();
                adapter.setNewReviews(reviews);
            }
        });
    }

}
