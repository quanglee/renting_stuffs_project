package com.quangle.rentingutilities.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.location.Address;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.quangle.rentingutilities.R;
import com.quangle.rentingutilities.core.model.Auth;
import com.quangle.rentingutilities.core.model.User;
import com.quangle.rentingutilities.networking.NetworkResource;
import com.quangle.rentingutilities.utils.GetAddressFromName;
import com.quangle.rentingutilities.utils.Helper;
import com.quangle.rentingutilities.utils.MySharedPreferences;
import com.quangle.rentingutilities.utils.Validation;
import com.quangle.rentingutilities.viewmodel.AuthViewModel;
import com.quangle.rentingutilities.viewmodel.UserViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;

public class UserActivity extends BaseActivity {

    private Auth auth;
    private User user;
    private EditText etEmail, etUsername, etPassword, etAddress;
    private UserViewModel userViewModel;
    private AuthViewModel authViewModel;
    private Button btnSubmit;
    private TextInputLayout tiEmail;

    @SuppressLint("MissingSuperCall")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_user);

        auth = MySharedPreferences.getAuth(this);
        if (auth == null) {
            setTitleActionBar(getResources().getString(R.string.register));
            user = new User();
        } else
            setTitleActionBar("Edit");

        final Validation validation = new Validation(this);
        tiEmail = findViewById(R.id.tiEmail);
        etEmail = findViewById(R.id.etEmail);
        final TextInputLayout tiUsername = findViewById(R.id.tiUsername);
        etUsername = findViewById(R.id.etUsername);
        final TextInputLayout tiPassword = findViewById(R.id.tiPassword);
        etPassword = findViewById(R.id.etPassword);
        final TextInputLayout tiAddress = findViewById(R.id.tiAddress);
        etAddress = findViewById(R.id.etAddress);
        btnSubmit = findViewById(R.id.btnSubmit);
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        authViewModel = ViewModelProviders.of(this).get(AuthViewModel.class);

        btnSubmit.setOnClickListener(v -> {
            boolean isEmailValid = validation.required(tiEmail, etEmail.getText().toString()) &&
                    validation.isValidEmail(tiEmail, etEmail.getText().toString());
            boolean isUsernameValid = validation.required(tiUsername, etUsername.getText().toString());
            boolean isPasswordValid = auth != null || validation.required(tiPassword, etPassword.getText().toString());
            boolean isAddressValid = validation.required(tiAddress, etAddress.getText().toString());

            Helper.hideKeyboard(this);
            if (isEmailValid && isUsernameValid && isPasswordValid && isAddressValid) {
                btnSubmit.setEnabled(false);
                showProgressBar();
                new GetAddressFromName(this, address -> {
                    if (address == null) {
                        btnSubmit.setEnabled(true);
                        hideProgressBar();
                        tiAddress.setError(getResources().getString(R.string.invalidAddress));
                    } else {
                        setUser(address);
                        sendUser();
                    }
                }).execute(etAddress.getText().toString());


            }
        });

       if (auth != null) {
           showProgressBar();
           tiPassword.setVisibility(View.GONE);
           etEmail.setEnabled(false);

           userViewModel.get(auth).observe(this, userNetworkResource -> {
               hideProgressBar();
               if (userNetworkResource.data != null) {
                   user = userNetworkResource.data;
                   setFields();
               }
           });
       }
    }

    public void setFields() {
        etEmail.setText(user.getEmail());
        etUsername.setText(user.getUsername());
        etAddress.setText(user.getAddress());
    }

    public void setUser(Address address) {
        user.setEmail(etEmail.getText().toString());
        user.setUsername(etUsername.getText().toString());
        user.setPassword(etPassword.getText().toString());
        user.setAddress(etAddress.getText().toString());
        user.setLat(address.getLatitude());
        user.setLng(address.getLongitude());
    }

    public void sendUser() {
        LiveData<NetworkResource<User>> networkResourceLiveData;
        if (auth == null)
            networkResourceLiveData = userViewModel.create(user.toHashMap());
        else
            networkResourceLiveData = userViewModel.edit(auth, user.toHashMap());
        networkResourceLiveData.observe(this, userNetworkResource -> {
            if (userNetworkResource.code == 409) {
                hideProgressBar();
                btnSubmit.setEnabled(true);
                tiEmail.setError(getResources().getString(R.string.existingEmail));
            } else if (userNetworkResource.data != null) {
                if (auth == null) {
                    authViewModel.login(user.toHashMap()).observe(this, authNetworkResource -> {
                        authNetworkResource.data.toSharedPreferences(this);
                        Intent intent = new Intent(this, HomeActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    });
                } else {
                    Toast.makeText(this, "User successfully edited", Toast.LENGTH_SHORT).show();
                    hideProgressBar();
                    btnSubmit.setEnabled(true);
                }
            }
        });
    }
}
