package com.quangle.rentingutilities.viewmodel;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.quangle.rentingutilities.core.model.Auth;
import com.quangle.rentingutilities.networking.Api;
import com.quangle.rentingutilities.networking.NetworkResource;
import com.quangle.rentingutilities.networking.RetrofitService;

import java.util.HashMap;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

//        firebaseAuth.signInWithEmailAndPassword(params)
//        System.out.println(params.get("email"));


//        firebaseAuth.signInWithEmailAndPassword(email, password)
//                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            // goto to main activity (Home page)
//                            System.out.println("HERE GOTO HOME PAGE");
//                            startActivity(new Intent(getActivity(), HomeActivity.class));
//                        } else {
//                            Toast.makeText(getContext(), "Invalid Credentials", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });

        return networkResourceAuthMutableLiveData;

        /* AS WE USE LOGIN TO FIREBASE BY Android SDK, not via Node JS server
        Api api = RetrofitService.get();
        Call<Auth> authCall = api.login(params);
        authCall.enqueue(new Callback<Auth>() {
            @Override
            public void onResponse(Call<Auth> call, Response<Auth> response) {
                if (response.isSuccessful())
                    networkResourceAuthMutableLiveData.setValue(new NetworkResource<>(response.body()));
                else
                    networkResourceAuthMutableLiveData.setValue(new NetworkResource<>(response.code()));
            }

            @Override
            public void onFailure(Call<Auth> call, Throwable t) {
                System.out.println("ON FAILURE");
            }
        });

        return networkResourceAuthMutableLiveData;
        */
    }
}
