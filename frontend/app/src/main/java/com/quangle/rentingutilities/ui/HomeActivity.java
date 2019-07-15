package com.quangle.rentingutilities.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import com.quangle.rentingutilities.R;
import com.quangle.rentingutilities.utils.MySharedPreferences;

public class HomeActivity extends BaseActivity {

    BottomNavigationView bottomNavigationView;

    @SuppressLint("MissingSuperCall")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                itemSelectedOnMenu(menuItem);
                return true;
            }
        });

        isUserLogged();

        itemSelectedOnMenu(bottomNavigationView.getMenu().getItem(0));
    }

    public void isUserLogged() {
        String userToken = MySharedPreferences.getLoggedUserToken(this);
        if (userToken == null)
            bottomNavigationView.inflateMenu(R.menu.guest);
        else
            bottomNavigationView.inflateMenu(R.menu.user);
    }

    public void itemSelectedOnMenu(MenuItem menuItem) {
        Fragment fragmentDisplay = null;
        switch (menuItem.getItemId()) {
            case R.id.home:
                setTitleActionBar("");
                fragmentDisplay = new HomeFragment();
                break;
            case R.id.items:
                setTitleActionBar(getResources().getString(R.string.items));
                fragmentDisplay = new ItemsFragment();
                break;
            case R.id.bookings:
                setTitleActionBar(getResources().getString(R.string.bookings));
                fragmentDisplay = new BookingsFragment();
                break;
            case R.id.login:
                setTitleActionBar(getResources().getString(R.string.login));
                fragmentDisplay = new LoginFragment();
                break;
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentDisplay, fragmentDisplay)
                .commit();
    }

}
