package com.example.merge.login;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.example.merge.BuildConfig;
import com.example.merge.livedata.SingleLiveEvent;
import com.example.merge.network.LoginRequestDTO;
import com.example.merge.network.RetrofitInstanceFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {
    public ObservableField<String> emailField = new ObservableField<>();
    public ObservableField<String> passwordField = new ObservableField<>();
    public ObservableField<Boolean> switchField = new ObservableField<>();

    // To send events to activity.
    public SingleLiveEvent<Boolean> loginResultEvent = new SingleLiveEvent<>();
    public SingleLiveEvent<Void> signUpEvent = new SingleLiveEvent<>();

    public LoginViewModel() {
        if (BuildConfig.FLAVOR.equals("mocklogin")) {
            // 만약 빌드설정이 mocklogin 이면 자동으로 아이디(이메일)와 비밀번호를 채워줌!
            emailField.set("test@naver.com");
            passwordField.set("test");
        }
        //TODO : 언어 자동선택

        if (AutoLogin.getEmailFromPreference().equals("")) {
            switchField.set(false);
        }else{
            switchField.set(true);
            emailField.set(AutoLogin.getEmailFromPreference());
            passwordField.set(AutoLogin.getPasswordFromPreference());
        }
    }

    public void login() {
        String email = emailField.get();
        String password = passwordField.get();

        // Check Login
        if (email == null || password == null) {
            return;
        }

        // Auto Login
        if (switchField.get()) {  // If switch is true
            AutoLogin.setPreferences(email, password);
        } else {
            AutoLogin.setPreferences("", "");
        }

        RetrofitInstanceFactory.getInstance().login(new LoginRequestDTO(email, password)).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    onLoginSuccess();
                } else {
                    onLoginFailure();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void onLoginSuccess() {
        loginResultEvent.setValue(true);
    }

    private void onLoginFailure() {
        loginResultEvent.setValue(false);
    }

    public void signUp() {
        signUpEvent.call();
    }
}
