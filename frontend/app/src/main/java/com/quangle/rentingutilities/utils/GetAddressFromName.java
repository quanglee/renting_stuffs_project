package com.quangle.rentingutilities.utils;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;

import java.util.List;
import java.util.Locale;

public class GetAddressFromName extends AsyncTask<String, Address, Address> {

    private OnResult<Address> onResult;
    private Context context;


    public GetAddressFromName(Context context, OnResult<Address> onResult) {
        this.onResult = onResult;
        this.context = context;
    }

    @Override
    protected Address doInBackground(String... params) {
        Address address = null;
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        String addressString = params[0];
        addressString += ", BC";
        try {
            List<Address> addressList = geocoder.getFromLocationName(addressString, 1);
            if (addressList != null && addressList.size() > 0)
                address = addressList.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return address;
    }

    protected void onPostExecute(Address address) {
        onResult.execute(address);
    }
}
