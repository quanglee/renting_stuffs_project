package com.quangle.rentingutilities.ui;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.textfield.TextInputLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.quangle.rentingutilities.R;
import com.quangle.rentingutilities.utils.Helper;
import com.quangle.rentingutilities.utils.Validation;
import com.quangle.rentingutilities.viewmodel.AuthViewModel;

import java.util.HashMap;

import androidx.lifecycle.ViewModelProviders;

public class LoginFragment extends BaseFragment {

    private String TAG = LoginFragment.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        final Validation validation = new Validation(getContext());
        final EditText etEmail = view.findViewById(R.id.etEmail);
        final EditText etPassword = view.findViewById(R.id.etPassword);
        final TextInputLayout tiEmail = view.findViewById(R.id.tiEmail);
        final TextInputLayout tiPassword = view.findViewById(R.id.tiPassword);
        final Button btnSubmit = view.findViewById(R.id.btnSubmit);
        final Button btnRegister = view.findViewById(R.id.btnRegister);
        final AuthViewModel authViewModel = ViewModelProviders.of(getActivity()).get(AuthViewModel.class);

        btnSubmit.setOnClickListener(v -> {
            boolean isEmailValid = validation.required(tiEmail, etEmail.getText().toString()) &&
                    validation.isValidEmail(tiEmail, etEmail.getText().toString());
            boolean isPasswordValid = validation.required(tiPassword, etPassword.getText().toString());

            Helper.hideKeyboard(getContext(), getView());
            if (isEmailValid && isPasswordValid) {
                btnSubmit.setEnabled(false);
                showProgressBar();
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("email", etEmail.getText().toString());
                hashMap.put("password", etPassword.getText().toString());
                authViewModel.login(hashMap).observe(this, authNetworkResource -> {
                        hideProgressBar();
                        btnSubmit.setEnabled(true);

                        if (authNetworkResource.code == 401) {
                            tiEmail.setError(getResources().getText(R.string.errorLogin));
                        } else if (authNetworkResource.data != null) {
                            authNetworkResource.data.toSharedPreferences(getActivity());
                            Intent intent = new Intent(getActivity(), HomeActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            getActivity().finish();
                        }
                    });
            } else {
                Helper.errorsInForm(getContext());
            }
        });

        btnRegister.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), UserActivity.class));
        });

        return view;
    }

}
