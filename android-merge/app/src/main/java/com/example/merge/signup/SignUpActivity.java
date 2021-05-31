package com.example.merge.signup;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.akexorcist.localizationactivity.ui.LocalizationActivity;
import com.example.merge.Config;
import com.example.merge.R;
import com.example.merge.databinding.ActivitySignupBinding;
import com.example.merge.login.LoginActivity;

public class SignUpActivity extends LocalizationActivity {
    SignUpViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLanguage(Config.selectedLanguage);
        setView();
        observeEvents();
    }

    private void setView() {
        ActivitySignupBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_signup);

        viewModel = new ViewModelProvider(this).get(SignUpViewModel.class);

        binding.setVm(viewModel);
        binding.setLifecycleOwner(this);
    }

    private void observeEvents() {
        viewModel.signUpResultEvent.observe(this, succeeded-> {
            if (succeeded) {
                goToLoginActivity();
            } else {
                Toast.makeText(this, "Already Exists Email or Name", Toast.LENGTH_SHORT).show();
            }
        });

        viewModel.goBackEvent.observe(this, Void -> {
            finish();
        });
    }

    private void goToLoginActivity() {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
        finish();
    }
}
