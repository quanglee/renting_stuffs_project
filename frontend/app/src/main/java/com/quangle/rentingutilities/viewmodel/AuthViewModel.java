package com.quangle.rentingutilities.viewmodel;

import com.google.firebase.auth.FirebaseAuth;
import com.quangle.rentingutilities.core.model.Auth;
import com.quangle.rentingutilities.networking.NetworkResource;

import java.util.HashMap;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AuthViewModel extends ViewModel {

    MutableLiveData<NetworkResource<Auth>> networkResourceAuthMutableLiveData = new MutableLiveData<>();
    private FirebaseAuth firebaseAuth;

    public AuthViewModel() {
        if (firebaseAuth ==null) {
            firebaseAuth = FirebaseAuth.getInstance();
        }
    }

    public LiveData<NetworkResource<Auth>> login(HashMap<String, Object> params) {
        firebaseAuth.signInWithEmailAndPassword(params.get("email").toString(), params.get("password").toString())
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        firebaseAuth.getCurrentUser().getIdToken(true).addOnSuccessListener(getTokenResult -> {
                            String accessToken = getTokenResult.getToken();
                            Auth auth = new Auth(accessToken);
                            networkResourceAuthMutableLiveData.setValue(new NetworkResource<>(auth));
                        });
                    } else {
                        networkResourceAuthMutableLiveData.setValue(new NetworkResource<>(401));
                    }
                });
        return networkResourceAuthMutableLiveData;
    }
}
