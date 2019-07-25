package com.quangle.rentingutilities.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.quangle.rentingutilities.R;
import com.quangle.rentingutilities.core.model.Item;

import androidx.viewpager.widget.ViewPager;

public class ItemActivity extends BaseActivity {

    private static final String ARG_ITEM = "item";
    private Item item;
    private TabLayout tabLayout;
    private TabsPagerAdapter itemTabsPagerAdapter;
    private ViewPager itemViewPager;

    public static void setItem(Intent intent, Item item) {
        intent.putExtra(ARG_ITEM, item);
    }

    @SuppressLint("MissingSuperCall")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_item);

        Intent intent = getIntent();
        item = (Item) intent.getSerializableExtra(ARG_ITEM);

        itemViewPager = findViewById(R.id.itemViewPager);
        tabLayout = findViewById(R.id.tabs);

        tabLayout.setVisibility(View.VISIBLE);
        itemTabsPagerAdapter = new TabsPagerAdapter(getSupportFragmentManager());
        itemTabsPagerAdapter.addFrag(ItemDetailFragment.newInstance(item), getResources().getString(R.string.detail));
        itemTabsPagerAdapter.addFrag(ItemReviewsFragment.newInstance(item), getResources().getString(R.string.reviews));

        itemViewPager.setAdapter(itemTabsPagerAdapter);
        tabLayout.setupWithViewPager(itemViewPager);
    }
}
