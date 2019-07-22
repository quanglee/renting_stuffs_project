package com.quangle.rentingutilities.ui;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.quangle.rentingutilities.R;
import com.quangle.rentingutilities.utils.MySharedPreferences;

public class ProfileFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ListView listView = view.findViewById(R.id.listView);

        String[] values = new String[] { "Logout" };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, android.R.id.text1, values);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((AdapterView<?> parent, View v, int position, long id) -> {
            String selected = values[position];
            if (selected.equals("Logout")) {
                MySharedPreferences.clearSharedPreferences(getActivity());
                ((HomeActivity) getActivity()).changeMenu();
            }
        });
        return view;
    }

}
