package com.quangle.rentingutilities.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;

import com.quangle.rentingutilities.R;

public class HomeActivity extends BaseActivity {

    BottomNavigationView bottomNavigationView;

    @SuppressLint("MissingSuperCall")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_home);
        setTitleActionBar("");

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
    }

}
