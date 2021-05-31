package com.example.merge.signup;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.example.merge.livedata.SingleLiveEvent;
import com.example.merge.network.RetrofitInstanceFactory;
import com.example.merge.network.SignUpDTO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpViewModel extends ViewModel {
    public ObservableField<String> nameField = new ObservableField<>();
    public ObservableField<String> emailField = new ObservableField<>();
    public ObservableField<String> passwordField = new ObservableField<>();

    public SingleLiveEvent<Boolean> signUpResultEvent = new SingleLiveEvent<>();
    public SingleLiveEvent<Void> goBackEvent = new SingleLiveEvent<>();

    public void signUp() {
        String name = nameField.get();
        String email = emailField.get();
        String password = passwordField.get();

        if (name == null || email == null || password == null) {
            return;
        }

        RetrofitInstanceFactory.getInstance().signUp(new SignUpDTO(name, email, password)).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    onSignUpSuccess();
                } else {
                    onSignUpFailure();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void goBack(){
        goBackEvent.call();
    }

    private void onSignUpSuccess() {
        signUpResultEvent.setValue(true);
    }

    private void onSignUpFailure() {
        signUpResultEvent.setValue(false);
    }

}
