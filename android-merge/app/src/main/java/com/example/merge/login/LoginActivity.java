package com.example.merge.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.akexorcist.localizationactivity.ui.LocalizationActivity;
import com.example.merge.Config;
import com.example.merge.R;
import com.example.merge.databinding.ActivityLoginBinding;
import com.example.merge.main.MainActivity;
import com.example.merge.signup.SignUpActivity;

public class LoginActivity extends LocalizationActivity {

    LoginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setView();
        observeEvents();
    }

    private void setView() {
        ActivityLoginBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        binding.setVm(viewModel);
        binding.setLifecycleOwner(this);

        Spinner languageSelector = findViewById(R.id.language_spinner);

        // Set selected language to Config.selectedLanguage.
        languageSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Config.selectedLanguage = getResources().getStringArray(R.array.languages_values)[position];
                setLanguage(Config.selectedLanguage);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Config.selectedLanguage = "en";
            }
        });
    }

    private void observeEvents() {
        viewModel.loginResultEvent.observe(this, succeeded-> {
            if (succeeded) {
                goToActivityMain();
            } else {
                Toast.makeText(this, "Wrong Id or Password", Toast.LENGTH_SHORT).show();
            }
        });

        viewModel.signUpEvent.observe(this, aVoid -> goToActivitySignUp());
    }

    private void goToActivitySignUp() {
        Intent i = new Intent(this, SignUpActivity.class);
        startActivity(i);
    }

    private void goToActivityMain() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        Config.translateInfo();
    }
}