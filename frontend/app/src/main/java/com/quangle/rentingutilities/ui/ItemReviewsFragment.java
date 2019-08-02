package com.quangle.rentingutilities.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.quangle.rentingutilities.R;
import com.quangle.rentingutilities.core.model.Item;

public class ItemReviewsFragment extends BaseFragment {

    private static final String ARG_ITEM = "item";
    private Item item;

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
        return view;
    }

}
