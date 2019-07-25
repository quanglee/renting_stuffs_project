package com.quangle.rentingutilities.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.quangle.rentingutilities.R;
import com.quangle.rentingutilities.core.model.Item;

public class BookingActivity extends BaseActivity {

    private static final String ARG_ITEM = "item";
    private Item item;

    public static void setItem(Intent intent, Item item) {
        intent.putExtra(ARG_ITEM, item);
    }

    @SuppressLint("MissingSuperCall")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_booking);

    }
}
