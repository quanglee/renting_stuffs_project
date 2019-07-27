package com.quangle.rentingutilities.ui;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.quangle.rentingutilities.R;
import com.quangle.rentingutilities.core.model.Auth;
import com.quangle.rentingutilities.core.model.Item;
import com.quangle.rentingutilities.core.model.User;
import com.quangle.rentingutilities.customAdapter.ItemAdapter;
import com.quangle.rentingutilities.utils.MySharedPreferences;
import com.quangle.rentingutilities.viewmodel.AuthViewModel;
import com.quangle.rentingutilities.viewmodel.ItemViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

public class ItemsFragment extends BaseFragment {

    ItemViewModel itemViewModel;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_items, container, false);

        showProgressBar();

        recyclerView = view.findViewById(R.id.home_items_list);
        assert recyclerView != null;
        setupRecyclerView(recyclerView);

        FloatingActionButton fabAdd = view.findViewById(R.id.fabAdd);
        fabAdd.setOnClickListener(v-> {
            Intent intent = new Intent(getActivity(), ItemActivity.class);
            ItemActivity.setItem(intent, new Item(), true);
            startActivity(intent);
        });

        return view;
    }

    private void setupRecyclerView(@NonNull RecyclerView tempRecyclerView) {

        //check user is login or not
        Auth auth = MySharedPreferences.getAuth(getContext());

        final ItemAdapter adapter = new ItemAdapter(getContext(), item -> {
            Intent intent = new Intent(getActivity(), ItemActivity.class);
            ItemActivity.setItem(intent, item, true);
            startActivity(intent);
        });
        tempRecyclerView.setAdapter(adapter);

        itemViewModel = ViewModelProviders.of(this).get(ItemViewModel.class);
        itemViewModel.getAllItemsOfUser(auth).observe(this, items -> {
            hideProgressBar();
            adapter.setNewItems(items);
        });
    }

}
