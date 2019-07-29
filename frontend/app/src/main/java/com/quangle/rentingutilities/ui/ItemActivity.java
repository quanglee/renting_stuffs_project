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
    private static final String ARG_CAN_EDIT = "canEdit";
    private Item item;
    private boolean canEdit;
    private TabLayout tabLayout;
    private TabsPagerAdapter itemTabsPagerAdapter;
    private ViewPager itemViewPager;
    public static int UPDATE_ITEMS = 1000;
    public boolean updateViews = false;

    public static void setItem(Intent intent, Item item) {
        intent.putExtra(ARG_ITEM, item);
    }

    public static void setItem(Intent intent, Item item, boolean canEdit) {
        intent.putExtra(ARG_ITEM, item);
        intent.putExtra(ARG_CAN_EDIT, canEdit);
    }

    @SuppressLint("MissingSuperCall")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_item);

        Intent intent = getIntent();
        item = (Item) intent.getSerializableExtra(ARG_ITEM);
        canEdit = intent.getBooleanExtra(ARG_CAN_EDIT, false);

        itemViewPager = findViewById(R.id.itemViewPager);
        tabLayout = findViewById(R.id.tabs);

        itemTabsPagerAdapter = new TabsPagerAdapter(getSupportFragmentManager());
        itemTabsPagerAdapter.addFrag(ItemDetailFragment.newInstance(item, canEdit), getResources().getString(R.string.detail));

        if (item.getId() == -1)
            setTitleActionBar("New item");
        else {
            setTitleActionBar("Item detail");
            tabLayout.setVisibility(View.VISIBLE);
            itemTabsPagerAdapter.addFrag(ItemReviewsFragment.newInstance(item), getResources().getString(R.string.reviews));
        }

        itemViewPager.setAdapter(itemTabsPagerAdapter);
        tabLayout.setupWithViewPager(itemViewPager);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (updateViews)
            setResult(UPDATE_ITEMS);
        finish();
    }
}
