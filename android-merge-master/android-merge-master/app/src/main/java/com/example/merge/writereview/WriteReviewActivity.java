package com.example.merge.writereview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.akexorcist.localizationactivity.ui.LocalizationActivity;
import com.example.merge.R;
import com.example.merge.databinding.ActivityWriteReviewBinding;

public class WriteReviewActivity extends LocalizationActivity {

    WriteReviewViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_review);

        setView();
        setProducts();
        observeEvents();
    }

    private void setView() {
        ActivityWriteReviewBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_write_review);

        viewModel = new ViewModelProvider(this).get(WriteReviewViewModel.class);

        binding.setVm(viewModel);
        binding.setLifecycleOwner(this);
    }

    private void setProducts() {
        Bundle params = getIntent().getExtras();
        long productId = params.getLong("product_id", -1);

        viewModel.setProductId(productId);
    }

    private void observeEvents() {
        viewModel.writeReviewResultEvent.observe(this, succeeded -> {
            if (succeeded) {
                goToActivityProduct();
            } else {
                Toast.makeText(this, "You Can't", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void goToActivityProduct() {  // finish 되면서 이전 리뷰로 돌아가도록
        finish();
    }

    public static Intent callingIntentWithProductId(Context context, Long productId) {
        Intent i = new Intent(context, WriteReviewActivity.class);
        Bundle params = new Bundle();
        params.putLong("product_id", productId);
        i.putExtras(params);

        return i;
    }
}