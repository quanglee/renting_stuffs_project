package com.quangle.rentingutilities.ui;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.quangle.rentingutilities.R;
import com.quangle.rentingutilities.core.model.Booking;
import com.quangle.rentingutilities.viewmodel.ItemViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import androidx.lifecycle.ViewModelProviders;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProcessBookingFragment extends BaseFragment {

    private int itemId;

    private ItemViewModel itemViewModel;

    private int mYear, mMonth, mDay, mHour, mMinute;
    private Calendar startDate, returnDate;
    private TextInputLayout tiStartDate, tiReturnDate;
    private Button btnStartDate, btnReturnDate, btnProcessBooking;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy hh:mm aaa");

    public ProcessBookingFragment() {
        // Required empty public constructor
    }

    //Note must set itemId before create fragment
    public void setItemId(int id){
        itemId = id;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_process_booking, container, false);

        itemViewModel = ViewModelProviders.of(this).get(ItemViewModel.class);

        btnStartDate = (Button) v.findViewById(R.id.btnStartDate);
        btnReturnDate = (Button) v.findViewById(R.id.btnReturnDate);
        btnProcessBooking = v.findViewById(R.id.btnProcessBooking);

        tiStartDate = v.findViewById(R.id.tiStartDate);
        tiReturnDate = v.findViewById(R.id.tiReturnDate);

        tiStartDate.setEnabled(false);
        tiReturnDate.setEnabled(false);

        btnProcessBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(startDate == null){
                    Toast.makeText(getContext(), "Please enter 'When you want to get?'",
                            Toast.LENGTH_SHORT).show();
                }else if(returnDate == null){
                    Toast.makeText(getContext(), "Please enter 'When you want to return?'",
                            Toast.LENGTH_SHORT).show();
                }else if(startDate.getTime().getTime() > returnDate.getTime().getTime()){
                    Toast.makeText(getContext(), "Return date must later than start date!",
                            Toast.LENGTH_SHORT).show();
                }else{
                    //create new Booking
                    Booking booking = new Booking();
                    booking.setItemId(itemId);
                    booking.setStartDate(startDate.getTime());
                    booking.setReturnDate(returnDate.getTime());


                    itemViewModel.createBooking(booking.toHashMap())
                                    .observe(getActivity(), authNetworkResource -> {

                        if (authNetworkResource.code == 401) {
                            Toast.makeText(getContext(), "Booking Fail!", Toast.LENGTH_SHORT).show();
                        } else if (authNetworkResource.data != null) {
                            Toast.makeText(getContext(), "Booking Success!", Toast.LENGTH_SHORT).show();

                            //return to booking tab to view current added booking
                            BaseActivity.startHomeActivityAtTab(HomeActivity.BOOKINGS_TAB_INDEX);
                        }
                    });

                }
            }
        });

        btnStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get Current Date
                Calendar todayDate = Calendar.getInstance();
                mYear = todayDate.get(Calendar.YEAR);
                mMonth = todayDate.get(Calendar.MONTH);
                mDay = todayDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), R.style.AppTheme_DialogTheme,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                // Get Current Time
                                final Calendar c = Calendar.getInstance();
                                mHour = c.get(Calendar.HOUR_OF_DAY);
                                mMinute = c.get(Calendar.MINUTE);

                                // Launch Time Picker Dialog
                                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), R.style.AppTheme_DialogTheme,
                                        new TimePickerDialog.OnTimeSetListener() {

                                            @Override
                                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                                  int minute) {

                                                //update time
                                                startDate = Calendar.getInstance();
                                                startDate.set(year, monthOfYear, dayOfMonth, hourOfDay, minute);

                                                tiStartDate.getEditText().setText(dateFormat.format(startDate.getTime()));
                                            }
                                        }, mHour, mMinute, false);

                                timePickerDialog.show();

                            }
                        }, mYear, mMonth, mDay);

                datePickerDialog.show();
            }
        });

        btnReturnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get Current Date
                Calendar todayDate = Calendar.getInstance();
                mYear = todayDate.get(Calendar.YEAR);
                mMonth = todayDate.get(Calendar.MONTH);
                mDay = todayDate.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), R.style.AppTheme_DialogTheme,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                // Get Current Time
                                final Calendar c = Calendar.getInstance();
                                mHour = c.get(Calendar.HOUR_OF_DAY);
                                mMinute = c.get(Calendar.MINUTE);

                                // Launch Time Picker Dialog
                                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), R.style.AppTheme_DialogTheme,
                                        new TimePickerDialog.OnTimeSetListener() {

                                            @Override
                                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                                  int minute) {

                                                //update time
                                                returnDate = Calendar.getInstance();
                                                returnDate.set(year, monthOfYear, dayOfMonth, hourOfDay, minute);

                                                tiReturnDate.getEditText().setText(dateFormat.format(returnDate.getTime()));
                                            }
                                        }, mHour, mMinute, false);

                                timePickerDialog.show();
                            }
                        }, mYear, mMonth, mDay);

                datePickerDialog.show();
            }
        });

        return v;
    }

}