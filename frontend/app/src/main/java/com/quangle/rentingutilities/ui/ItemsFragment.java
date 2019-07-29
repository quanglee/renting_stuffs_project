package com.quangle.rentingutilities.ui;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.quangle.rentingutilities.R;
import com.quangle.rentingutilities.core.model.Item;
import com.quangle.rentingutilities.customAdapter.ItemAdapter;
import com.quangle.rentingutilities.viewmodel.ItemViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

public class ItemsFragment extends BaseFragment {

    ItemViewModel itemViewModel;
    RecyclerView recyclerView;
    ItemAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_items, container, false);

        recyclerView = view.findViewById(R.id.home_items_list);
        assert recyclerView != null;
        setupRecyclerView(recyclerView);

        FloatingActionButton fabAdd = view.findViewById(R.id.fabAdd);
        fabAdd.setOnClickListener(v-> {
            Intent intent = new Intent(getActivity(), ItemActivity.class);
            ItemActivity.setItem(intent, new Item(), true);
            startActivityForResult(intent, ItemActivity.UPDATE_ITEMS);
        });

        return view;
    }

    private void setupRecyclerView(@NonNull RecyclerView tempRecyclerView) {
        adapter = new ItemAdapter(getContext(), item -> {
            Intent intent = new Intent(getActivity(), ItemActivity.class);
            ItemActivity.setItem(intent, item, true);
            startActivityForResult(intent, ItemActivity.UPDATE_ITEMS);
        });
        tempRecyclerView.setAdapter(adapter);

        itemViewModel = ViewModelProviders.of(this).get(ItemViewModel.class);
        getItems();
    }

    public void getItems() {
        showProgressBar();
        itemViewModel.getAllItemsOfUser().observe(this, items -> {
            hideProgressBar();
            adapter.setNewItems(items);
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ItemActivity.UPDATE_ITEMS) {
            getItems();
        }
    }
}
