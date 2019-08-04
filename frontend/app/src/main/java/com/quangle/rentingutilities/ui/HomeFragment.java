package com.quangle.rentingutilities.ui;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.quangle.rentingutilities.R;
import com.quangle.rentingutilities.core.model.Item;
import com.quangle.rentingutilities.customAdapter.ItemAdapter;
import com.quangle.rentingutilities.utils.Helper;
import com.quangle.rentingutilities.viewmodel.ItemViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

public class HomeFragment extends BaseFragment {

    ItemViewModel itemViewModel;
    RecyclerView recyclerView;
    ItemAdapter itemAdapter;
    private String searched;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        itemViewModel = ViewModelProviders.of(this).get(ItemViewModel.class);
        recyclerView = view.findViewById(R.id.home_items_list);


        itemAdapter = new ItemAdapter(getContext(), item -> {
            Intent intent = new Intent(getActivity(), ItemActivity.class);
            ItemActivity.setItem(intent,item);
            startActivity(intent);
        });
        recyclerView.setAdapter(itemAdapter);
        getItems(null);

        setHasOptionsMenu(true);

        return view;
    }

    public void getItems(String itemName) {
        showProgressBar();
        itemViewModel.getAllItems(itemName).observe(this, items -> {
            hideProgressBar();
            itemAdapter.setNewItems(items);
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search, menu);

        MenuItem mSearch = menu.findItem(R.id.action_search);

        SearchView mSearchView = (SearchView) mSearch.getActionView();
        ImageView imageView = mSearchView.findViewById(R.id.search_close_btn);
        mSearchView.setQueryHint("Search");

        imageView.setOnClickListener(v -> {
            EditText et = mSearchView.findViewById(R.id.search_src_text);

            //Clear the text from EditText view
            et.setText("");

            //Clear query
            mSearchView.setQuery("", false);
            //Collapse the action view
            mSearchView.onActionViewCollapsed();
            //Collapse the search widget
            mSearch.collapseActionView();
            ((BaseActivity)getActivity()).setTitleActionBar("");
            getItems(null);
        });

        mSearch.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                mSearchView.onActionViewExpanded();
                mSearchView.setQuery(searched, false);
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                mSearchView.onActionViewCollapsed();
                return true;
            }
        });
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Helper.hideKeyboard(getContext(), getView());
                mSearch.collapseActionView();
                getItems(query);
                searched = query;
                ((BaseActivity)getActivity()).setTitleActionBar("Results for " + query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }

}
