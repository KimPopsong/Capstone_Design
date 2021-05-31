package com.example.merge.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.akexorcist.localizationactivity.ui.LocalizationActivity;
import com.example.merge.Config;
import com.example.merge.R;
import com.example.merge.camera.CameraActivity;
import com.example.merge.databinding.ActivityMainBinding;
import com.example.merge.product.ProductActivity;
import com.example.merge.product.ProductListAdapter;
import com.example.merge.search.SearchActivity;

public class MainActivity extends LocalizationActivity {
    MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLanguage(Config.selectedLanguage);
        setViewModel();
        setView();

        observeEvents();

        viewModel.load();
    }

    private void setViewModel() {
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
    }

    private void setView() {
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.setLifecycleOwner(this);
        binding.setVm(viewModel);

        binding.productsRecycler.setAdapter(new ProductListAdapter(item -> {
            showProductDetails(item.getId());
            return null; // 무쓸모하지만 자바가 시킴 ㅡㅡ
        }));
    }

    private void observeEvents() {
        viewModel.keywordSearchEvent.observe(this, aVoid -> {
            showSearchActivity();
        });

        viewModel.cameraStartEvent.observe(this, aVoid -> {
            showCameraActivity();
        });
    }

    private void showProductDetails(Long productId) {
        startActivity(ProductActivity.callingIntentWithProductId(this, productId));
    }

    private void showSearchActivity() {
        startActivity(new Intent(this, SearchActivity.class));
    }

    private void showCameraActivity() {
        startActivity(new Intent(this, CameraActivity.class));
    }
}
