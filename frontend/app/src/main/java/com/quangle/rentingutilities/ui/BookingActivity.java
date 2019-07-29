package com.quangle.rentingutilities.ui;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.quangle.rentingutilities.R;
import com.quangle.rentingutilities.core.model.Booking;
import com.quangle.rentingutilities.core.model.Item;

import androidx.fragment.app.Fragment;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import okhttp3.RequestBody;

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
        Fragment fragmentDisplay = null;
        switch (fragmentOptions) {
            case "processBooking":
                item = (Item) intent.getSerializableExtra(ARG_ITEM);
                setTitleActionBar("PROCESS BOOKING");
                System.out.println("Place your process booking fragment here");
                fragmentDisplay = new ProcessBookingFragment();
                break;
            case "bookingDetail":
                booking = (Booking) intent.getSerializableExtra(ARG_BOOKING);
                System.out.println("Place your booking detail fragment here");
                setTitleActionBar("BOOKING DETAIL");
                booking = (Booking) intent.getSerializableExtra(ARG_BOOKING);
                fragmentDisplay = new BookingDetailFragment();
                Bundle i = new Bundle();
                i.putSerializable(ARG_BOOKING, booking);
                fragmentDisplay.setArguments(i);
                break;
        }

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentDisplay, fragmentDisplay)
                .commit();
    }

}
