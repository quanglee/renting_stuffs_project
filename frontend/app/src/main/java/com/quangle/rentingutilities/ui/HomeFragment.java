package com.quangle.rentingutilities.ui;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.quangle.rentingutilities.R;
import com.quangle.rentingutilities.core.model.Auth;
import com.quangle.rentingutilities.core.model.Item;
import com.quangle.rentingutilities.customAdapter.ItemAdapter;
import com.quangle.rentingutilities.utils.MySharedPreferences;
import com.quangle.rentingutilities.viewmodel.ItemViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

public class HomeFragment extends BaseFragment {

    ItemViewModel itemViewModel;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        showProgressBar();

        recyclerView = view.findViewById(R.id.home_items_list);
        assert recyclerView != null;
        setupRecyclerView(recyclerView);

        return view;
    }

    private void setupRecyclerView(@NonNull RecyclerView tempRecyclerView) {
        final ItemAdapter adapter = new ItemAdapter(getContext(), item -> {
            Intent intent = new Intent(getActivity(), ItemActivity.class);
            ItemActivity.setItem(intent,item);
            startActivity(intent);
        });
        tempRecyclerView.setAdapter(adapter);

        itemViewModel = ViewModelProviders.of(this).get(ItemViewModel.class);
        itemViewModel.getAllItems().observe(this, new Observer<List<Item>>() {
            @Override
            public void onChanged(List<Item> items) {
                hideProgressBar();
                adapter.setNewItems(items);
            }
        });
    }
}
