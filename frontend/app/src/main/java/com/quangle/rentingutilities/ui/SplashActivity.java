package com.quangle.rentingutilities.ui;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.quangle.rentingutilities.R;
import com.quangle.rentingutilities.core.model.Auth;
import com.quangle.rentingutilities.core.model.Item;
import com.quangle.rentingutilities.utils.MySharedPreferences;
import com.quangle.rentingutilities.viewmodel.ItemViewModel;
import com.quangle.rentingutilities.viewmodel.UserViewModel;

import java.util.List;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Auth auth = MySharedPreferences.getAuth(this);

        if (auth == null){
            UserViewModel.loggedInUser = null;//clear logged-in user
            UserViewModel.wishlistItemOfLoggedInUser = null;//clear wishlist of logged-in user

            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            finish();
        }
        else{

            //save user to UserViewModel
            UserViewModel userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);

            userViewModel.get().observe(this, userNetworkResource -> {

                if (userNetworkResource.data != null) {
                    UserViewModel.loggedInUser = userNetworkResource.data;//set logged-in user

                    //load wishlist of logged-in user to UserViewModel
                    ItemViewModel itemViewModel = ViewModelProviders.of(this).get(ItemViewModel.class);
                    itemViewModel.getWishlistOfUser(auth).observe(this, new Observer<List<Item>>() {
                        @Override
                        public void onChanged(List<Item> items) {

                            UserViewModel.wishlistItemOfLoggedInUser = items;

                            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                            finish();

                        }
                    });
                }
            });
        }

    }

}
