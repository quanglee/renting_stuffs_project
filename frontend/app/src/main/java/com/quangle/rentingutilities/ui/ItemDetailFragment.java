package com.quangle.rentingutilities.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.google.android.material.textfield.TextInputLayout;
import com.quangle.rentingutilities.R;
import com.quangle.rentingutilities.core.model.Item;
import com.quangle.rentingutilities.utils.Validation;
import com.squareup.picasso.Picasso;

public class ItemDetailFragment extends BaseFragment {

    private static final String ARG_ITEM = "item";
    private static final String ARG_CAN_EDIT = "canEdit";
    private Item item;
    private boolean canEdit = false;

    public static ItemDetailFragment newInstance(Item item) {
        ItemDetailFragment fragment = new ItemDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_ITEM, item);
        fragment.setArguments(args);
        return fragment;
    }

    public static ItemDetailFragment newInstance(Item item, boolean canEdit) {
        ItemDetailFragment fragment = new ItemDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_ITEM, item);
        args.putSerializable(ARG_CAN_EDIT, canEdit);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            item = (Item) getArguments().getSerializable(ARG_ITEM);
            canEdit = getArguments().getBoolean(ARG_CAN_EDIT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_item_detail, container, false);

        Validation validation = new Validation(getActivity());
        RatingBar ratingBar = view.findViewById(R.id.ratingBar);
        ImageView imageView = view.findViewById(R.id.imageView);
        TextInputLayout tiName = view.findViewById(R.id.tiName);
        EditText etName = view.findViewById(R.id.etName);
        TextInputLayout tiDescription = view.findViewById(R.id.tiDescription);
        EditText etDescription = view.findViewById(R.id.etDescription);
        TextInputLayout tiPrice = view.findViewById(R.id.tiPrice);
        EditText etPrice = view.findViewById(R.id.etPrice);
        TextInputLayout tiCategory = view.findViewById(R.id.tiCategory);
        EditText etCategory = view.findViewById(R.id.etCategory);
        TextInputLayout tiCondition = view.findViewById(R.id.tiCondition);
        EditText etCondition = view.findViewById(R.id.etCondition);
        TextInputLayout tiTags = view.findViewById(R.id.tiTags);
        EditText etTags = view.findViewById(R.id.etTags);
        Button btnBook = view.findViewById(R.id.btnBook);

        ratingBar.setRating((float) item.getAverageRating());
        Picasso.get().setLoggingEnabled(true);
        Picasso.get().load(item.getImageURL()).placeholder(R.drawable.baseline_account_circle_black_48px).into(imageView);
        etName.setText(item.getName());
        etDescription.setText(item.getDescription());
        etPrice.setText(Double.toString(item.getPrice()));
        etCategory.setText(item.getCategory());
        etCondition.setText(item.getCondition());
        etTags.setText(item.getTags());
        btnBook.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), BookingActivity.class);
            BookingActivity.setItem(intent, item);
            getActivity().startActivity(intent);
        });

        if (!canEdit) {
            etName.setEnabled(false);
            etDescription.setEnabled(false);
            etPrice.setEnabled(false);
            etCategory.setEnabled(false);
            etCondition.setEnabled(false);
            etTags.setEnabled(false);
        }

        return view;
    }

}
