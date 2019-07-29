package com.quangle.rentingutilities.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.quangle.rentingutilities.R;
import com.quangle.rentingutilities.core.model.Booking;
import com.quangle.rentingutilities.core.model.Item;

public class BookingActivity extends BaseActivity {

    private static final String ARG_BOOKING = "booking";
    private static final String ARG_ITEM = "item";
    private Booking booking;
    private Item item;
    private String fragmentOptions = "item";

    // USED WHEN USER CLICKS ON BOOKINGS LIST
    public static void setItem(Intent intent, Booking booking) {
        intent.putExtra(ARG_BOOKING, booking);
    }

    // USED WHEN USER CLICKS ON ITEM DETAIL AND START TO BOOK THIS ITEM
    public static void setItem(Intent intent, Item item) {
        intent.putExtra(ARG_ITEM, item);
    }

    @SuppressLint("MissingSuperCall")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_booking);

        // get intent
        Intent intent = getIntent();
        fragmentOptions = intent.getStringExtra("fragmentOptions");
        System.out.println(fragmentOptions);

//        switch (fragmentOptions) {
//            case ARG_ITEM:
//                item = (Item) intent.getSerializableExtra(ARG_ITEM);
//                System.out.println("Dfdfdf");
//                break;
//            case ARG_BOOKING:
//                booking = (Booking) intent.getSerializableExtra(ARG_BOOKING);
//                System.out.println(booking.getItem().getName());
//                break;
//        }


    }
}
