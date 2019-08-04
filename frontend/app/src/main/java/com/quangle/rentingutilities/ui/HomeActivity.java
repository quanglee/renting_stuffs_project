package com.quangle.rentingutilities.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.material.tabs.TabLayout;
import com.quangle.rentingutilities.R;
import com.quangle.rentingutilities.core.model.Auth;
import com.quangle.rentingutilities.core.model.Item;
import com.quangle.rentingutilities.core.model.User;
import com.quangle.rentingutilities.utils.MySharedPreferences;
import com.quangle.rentingutilities.viewmodel.ItemViewModel;
import com.quangle.rentingutilities.viewmodel.UserViewModel;

import java.util.List;

public class HomeActivity extends BaseActivity {

    //TAB INDEX
    public static final String ARG_TAB_ITEM_ID = "tabItemId";

    //TOP NAV INDEX OF ITEMS TAB
    public static final int ITEMS_TOPNAV_INDEX = 0;
    public static final int WISHLIST_TOPNAV_INDEX = 1;
    public static final int REQUESTS_TOPNAV_INDEX = 2;

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
        int tabItemId = intent.getIntExtra(ARG_TAB_ITEM_ID, R.id.home);
        int selectedTopNavItem = intent.getIntExtra("topItemsTabNavIndex", ITEMS_TOPNAV_INDEX);

        itemsViewPager.setCurrentItem(selectedTopNavItem);
        System.out.println("Tab Item ID " + tabItemId);
        System.out.println("Tab Login ID " + R.id.login);
        System.out.println("Tab Home ID " + R.id.home);
        System.out.println("Tab Items ID " + R.id.items);
        System.out.println("Tab Bookings ID " + R.id.bookings);

        changeMenu(tabItemId);

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

    public void changeMenu(int tabItemId) {
        isUserLogged();
        View view = bottomNavigationView.findViewById(tabItemId);
        view.performClick();
    }

    public void isUserLogged() {
        Auth auth = MySharedPreferences.getAuth(this);

        bottomNavigationView.getMenu().clear();
        if (auth == null){
            bottomNavigationView.inflateMenu(R.menu.guest);
//            UserViewModel.loggedInUser = null;//clear logged-in user
//            UserViewModel.wishlistItemOfLoggedInUser = null;//clear wishlist of logged-in user
        }
        else{
            bottomNavigationView.inflateMenu(R.menu.user);

            //save user to UserViewModel
//            UserViewModel userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
//
//            userViewModel.get().observe(this, userNetworkResource -> {
//
//                if (userNetworkResource.data != null) {
//                    UserViewModel.loggedInUser = userNetworkResource.data;//set logged-in user
//
//                    //load wishlist of logged-in user to UserViewModel
//                    ItemViewModel itemViewModel = ViewModelProviders.of(this).get(ItemViewModel.class);
//                    itemViewModel.getWishlistOfUser(auth).observe(this, new Observer<List<Item>>() {
//                        @Override
//                        public void onChanged(List<Item> items) {
//                            hideProgressBar();
//                            UserViewModel.wishlistItemOfLoggedInUser = items;
//
//                        }
//                    });
//                }
//            });
        }
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
                fragmentDisplay = itemsTabsPagerAdapter.getItem(itemsViewPager.getCurrentItem());
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
