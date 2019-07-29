package com.quangle.rentingutilities.ui;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.quangle.rentingutilities.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProcessBookingFragment extends BaseFragment {


    public ProcessBookingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_process_booking, container, false);
    }

}

//    private int mYear, mMonth, mDay, mHour, mMinute;
//    private Calendar startDate, returnDate;
//    private TextInputLayout tiStartDate, tiReturnDate;
//    private Button btnStartDate, btnReturnDate, btnProcessBooking;
//    private SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy hh:mm aaa");




//        setTitleActionBar("Process Booking");
//
//        btnStartDate = (Button)findViewById(R.id.btnStartDate);
//        btnReturnDate = (Button)findViewById(R.id.btnReturnDate);
//        btnProcessBooking = findViewById(R.id.btnProcessBooking);
//
//        tiStartDate = findViewById(R.id.tiStartDate);
//        tiReturnDate = findViewById(R.id.tiReturnDate);
//
//        tiStartDate.setEnabled(false);
//        tiReturnDate.setEnabled(false);
//
//        btnProcessBooking.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if(startDate == null){
//                    Toast.makeText(BookingActivity.this, "Please enter 'When you want to get?'",
//                            Toast.LENGTH_SHORT).show();
//                }else if(returnDate == null){
//                    Toast.makeText(BookingActivity.this, "Please enter 'When you want to return?'",
//                            Toast.LENGTH_SHORT).show();
//                }else if(startDate.getTime().getTime() > returnDate.getTime().getTime()){
//                    Toast.makeText(BookingActivity.this, "Return date must later than start date!",
//                            Toast.LENGTH_SHORT).show();
//                }else{
//                    //send request to database
//                    Toast.makeText(BookingActivity.this, "Valid date!",
//                            Toast.LENGTH_SHORT).show();
//
//                    //create new Booking
//                    Booking booking = new Booking();
////                    booking.setItemId();
////                    booking.setStartDate();
////                    booking.setReturnDate();
//
//                }
//            }
//        });
//
//        btnStartDate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Get Current Date
//                startDate = Calendar.getInstance();
//                mYear = startDate.get(Calendar.YEAR);
//                mMonth = startDate.get(Calendar.MONTH);
//                mDay = startDate.get(Calendar.DAY_OF_MONTH);
//
//                DatePickerDialog datePickerDialog = new DatePickerDialog(BookingActivity.this,
//                        new DatePickerDialog.OnDateSetListener() {
//
//                            @Override
//                            public void onDateSet(DatePicker view, int year,
//                                                  int monthOfYear, int dayOfMonth) {
//
//                                // Get Current Time
//                                final Calendar c = Calendar.getInstance();
//                                mHour = c.get(Calendar.HOUR_OF_DAY);
//                                mMinute = c.get(Calendar.MINUTE);
//
//                                // Launch Time Picker Dialog
//                                TimePickerDialog timePickerDialog = new TimePickerDialog(BookingActivity.this,
//                                        new TimePickerDialog.OnTimeSetListener() {
//
//                                            @Override
//                                            public void onTimeSet(TimePicker view, int hourOfDay,
//                                                                  int minute) {
//
//                                                //update time
//                                                startDate.set(year, monthOfYear, dayOfMonth, hourOfDay, minute);
//
//                                                tiStartDate.getEditText().setText(dateFormat.format(startDate.getTime()));
//                                            }
//                                        }, mHour, mMinute, false);
//
//                                timePickerDialog.show();
//
//                            }
//                        }, mYear, mMonth, mDay);
//
//                datePickerDialog.show();
//            }
//        });
//
//        btnReturnDate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Get Current Date
//                returnDate = Calendar.getInstance();
//                mYear = returnDate.get(Calendar.YEAR);
//                mMonth = returnDate.get(Calendar.MONTH);
//                mDay = returnDate.get(Calendar.DAY_OF_MONTH);
//
//                DatePickerDialog datePickerDialog = new DatePickerDialog(BookingActivity.this,
//                        new DatePickerDialog.OnDateSetListener() {
//
//                            @Override
//                            public void onDateSet(DatePicker view, int year,
//                                                  int monthOfYear, int dayOfMonth) {
//
//                                // Get Current Time
//                                final Calendar c = Calendar.getInstance();
//                                mHour = c.get(Calendar.HOUR_OF_DAY);
//                                mMinute = c.get(Calendar.MINUTE);
//
//                                // Launch Time Picker Dialog
//                                TimePickerDialog timePickerDialog = new TimePickerDialog(BookingActivity.this,
//                                        new TimePickerDialog.OnTimeSetListener() {
//
//                                            @Override
//                                            public void onTimeSet(TimePicker view, int hourOfDay,
//                                                                  int minute) {
//
//                                                //update time
//                                                returnDate.set(year, monthOfYear, dayOfMonth, hourOfDay, minute);
//
//                                                tiReturnDate.getEditText().setText(dateFormat.format(returnDate.getTime()));
//                                            }
//                                        }, mHour, mMinute, false);
//
//                                timePickerDialog.show();
//                            }
//                        }, mYear, mMonth, mDay);
//
//                datePickerDialog.show();
//            }
//        });
