package com.quangle.rentingutilities.ui;

import android.os.Bundle;

import com.google.android.material.textfield.TextInputLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.quangle.rentingutilities.R;
import com.quangle.rentingutilities.core.model.User;
import com.quangle.rentingutilities.networking.NetworkResource;
import com.quangle.rentingutilities.utils.Helper;
import com.quangle.rentingutilities.utils.Validation;
import com.quangle.rentingutilities.viewmodel.UserViewModel;

import java.util.HashMap;

import androidx.lifecycle.Observer;
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
        final UserViewModel userViewModel = ViewModelProviders.of(getActivity()).get(UserViewModel.class);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isEmailValid = validation.required(tiEmail, etEmail.getText().toString()) &&
                        validation.isValidEmail(tiEmail, etEmail.getText().toString());
                boolean isPasswordValid = validation.required(tiPassword, etPassword.getText().toString());

                Helper.hideKeyboard(getContext(), getView());
                if (isEmailValid && isPasswordValid) {
                    btnSubmit.setEnabled(false);
                    showProgressBar();
                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("email", etEmail.getText().toString());
                    hashMap.put("password", etPassword.getText().toString());
                    userViewModel.login(hashMap)
                            .observe(LoginFragment.this, new Observer<NetworkResource<User>>() {
                        @Override
                        public void onChanged(NetworkResource<User> userNetworkResource) {
                            hideProgressBar();
                            btnSubmit.setEnabled(true);
                            if (userNetworkResource.code == 401)
                                tiEmail.setError(getResources().getText(R.string.errorLogin));
                            else if (userNetworkResource.data != null) {
                                Log.d(TAG, userNetworkResource.data.getToken());
                            }
                        }
                    });
                } else {
                    Helper.errorsInForm(getContext());
                }
            }
        });

        return view;
    }

}
