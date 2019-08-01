package com.quangle.rentingutilities.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.material.tabs.TabLayout;
import com.quangle.rentingutilities.R;
import com.quangle.rentingutilities.core.model.Auth;
import com.quangle.rentingutilities.utils.MySharedPreferences;

public class HomeActivity extends BaseActivity {

    //TAB INDEX
    public static final int HOME_TAB_INDEX = 0;
    public static final int ITEMS_TAB_INDEX = 1;
    public static final int BOOKINGS_TAB_INDEX = 2;
    public static final int PROFILE_TAB_INDEX = 3;

    BottomNavigationView bottomNavigationView;
    private TabLayout tabLayout;
    private TabsPagerAdapter itemsTabsPagerAdapter;
    private ViewPager itemsViewPager;
    private FrameLayout frameLayout;

    @SuppressLint("MissingSuperCall")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            itemSelectedOnMenu(menuItem);
            return true;
        });

        frameLayout = findViewById(R.id.fragmentDisplay);
        itemsViewPager = findViewById(R.id.itemsViewPager);
        tabLayout = findViewById(R.id.tabs);

        itemsTabsPagerAdapter = new TabsPagerAdapter(getSupportFragmentManager());
        itemsTabsPagerAdapter.addFrag(new ItemsFragment(), getResources().getString(R.string.items));
        itemsTabsPagerAdapter.addFrag(new WishListFragment(), getResources().getString(R.string.wishlist));
        itemsTabsPagerAdapter.addFrag(new RequestsFragment(), getResources().getString(R.string.requests));
        itemsViewPager.setAdapter(itemsTabsPagerAdapter);

        //display target tab
        Intent intent = getIntent();
        int selectedTab = intent.getIntExtra("selectedTab", HOME_TAB_INDEX);
        changeMenu(selectedTab);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentDisplay, itemsTabsPagerAdapter.getItem(tab.getPosition()))
                        .commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public void changeMenu(int index) {
        isUserLogged();
        itemSelectedOnMenu(bottomNavigationView.getMenu().getItem(index));
        View view = null;
        switch (index) {
            case HOME_TAB_INDEX:
                view = bottomNavigationView.findViewById(R.id.home);
                break;
            case ITEMS_TAB_INDEX:
                view = bottomNavigationView.findViewById(R.id.items);
                break;
            case BOOKINGS_TAB_INDEX:
                view = bottomNavigationView.findViewById(R.id.bookings);
                break;
            case PROFILE_TAB_INDEX:
                view = bottomNavigationView.findViewById(R.id.profile);
                break;
        }

        view.performClick();
    }

    public void isUserLogged() {
        Auth auth = MySharedPreferences.getAuth(this);
        bottomNavigationView.getMenu().clear();
        if (auth == null)
            bottomNavigationView.inflateMenu(R.menu.guest);
        else
            bottomNavigationView.inflateMenu(R.menu.user);
    }

    public void itemSelectedOnMenu(MenuItem menuItem) {
        Fragment fragmentDisplay = null;
        if (menuItem.getItemId() == R.id.items) {
            displayTabs(true);
            tabLayout.setupWithViewPager(itemsViewPager);
        } else
            displayTabs(false);

        switch (menuItem.getItemId()) {
            case R.id.home:
                setTitleActionBar("");
                fragmentDisplay = new HomeFragment();
                break;
            case R.id.items:
                setTitleActionBar(getResources().getString(R.string.yourItems));
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
            case R.id.profile:
                setTitleActionBar(getResources().getString(R.string.profile));
                fragmentDisplay = new ProfileFragment();
                break;
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentDisplay, fragmentDisplay)
                .commit();
    }

    public void displayTabs(boolean visibility) {
        if (visibility) {
            tabLayout.setVisibility(View.VISIBLE);
        } else {
            tabLayout.setVisibility(View.GONE);
        }
    }

}
