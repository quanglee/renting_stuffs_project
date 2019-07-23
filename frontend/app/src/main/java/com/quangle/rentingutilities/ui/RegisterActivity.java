package com.quangle.rentingutilities.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;
import com.quangle.rentingutilities.R;
import com.quangle.rentingutilities.utils.Helper;
import com.quangle.rentingutilities.utils.Validation;
import com.quangle.rentingutilities.viewmodel.AuthViewModel;
import com.quangle.rentingutilities.viewmodel.UserViewModel;

import java.util.HashMap;

import androidx.lifecycle.ViewModelProviders;

public class RegisterActivity extends BaseActivity {

    @SuppressLint("MissingSuperCall")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_register);

        setTitleActionBar(getResources().getString(R.string.register));

        final Validation validation = new Validation(this);
        final TextInputLayout tiEmail = findViewById(R.id.tiEmail);
        final EditText etEmail = findViewById(R.id.etEmail);
        final TextInputLayout tiUsername = findViewById(R.id.tiUsername);
        final EditText etUsername = findViewById(R.id.etUsername);
        final TextInputLayout tiPassword = findViewById(R.id.tiPassword);
        final EditText etPassword = findViewById(R.id.etPassword);
        final Button btnSubmit = findViewById(R.id.btnSubmit);
        final UserViewModel userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        final AuthViewModel authViewModel = ViewModelProviders.of(this).get(AuthViewModel.class);

        btnSubmit.setOnClickListener(v -> {
            boolean isEmailValid = validation.required(tiEmail, etEmail.getText().toString()) &&
                    validation.isValidEmail(tiEmail, etEmail.getText().toString());
            boolean isUsernameValid = validation.required(tiUsername, etUsername.getText().toString());
            boolean isPasswordValid = validation.required(tiPassword, etPassword.getText().toString());

            Helper.hideKeyboard(this);
            if (isEmailValid && isUsernameValid && isPasswordValid) {
                btnSubmit.setEnabled(false);
                showProgressBar();
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("email", etEmail.getText().toString());
                hashMap.put("username", etUsername.getText().toString());
                hashMap.put("password", etPassword.getText().toString());
                userViewModel.create(hashMap).observe(this, userNetworkResource -> {
                    if (userNetworkResource.code == 409) {
                        hideProgressBar();
                        btnSubmit.setEnabled(true);
                        tiEmail.setError(getResources().getString(R.string.existingEmail));
                    } else if (userNetworkResource.data != null) {
                        authViewModel.login(hashMap).observe(this, authNetworkResource -> {
                            authNetworkResource.data.toSharedPreferences(this);
                            Intent intent = new Intent(this, HomeActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
                        });
                    }
                });
            }
        });
    }
}
