package com.example.merge.product;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.akexorcist.localizationactivity.ui.LocalizationActivity;
import com.example.merge.Config;
import com.example.merge.R;
import com.example.merge.databinding.ActivityProductBinding;
import com.example.merge.product.info.ProductInfoFragment;
import com.example.merge.product.review.ProductReviewFragment;
import com.google.android.material.tabs.TabLayoutMediator;


/**
 * (search result...) -> ProductActivity(with productId)
 * ProductInfoFragment
 * ProductReviewFragment -> WriteReviewActivity(with productId)
 */

public class ProductActivity extends LocalizationActivity {

    ProductViewModel viewModel;
    ActivityProductBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLanguage(Config.selectedLanguage);
        setView();
        setProduct();
        observeEvents();
    }

    private void setView() {
        // setContentView 대신에 바인딩을 사용
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product);

        // 뷰모델을 가져와 줍니다. 이건 라이브러리가 책임
        this.viewModel = new ViewModelProvider(this).get(ProductViewModel.class);

        // 위에서 만든 바인딩에다가 뷰모델을 넣어줌
        // 이제 xml이랑 연결되어서 뷰에 자동으로 감
        binding.setVm(this.viewModel);

        // 라이프사이클 소유자도 설정해줘야 라이브데이터가 작동
        binding.setLifecycleOwner(this);

        // 뷰페이저 설정!
        ScreenSlidePagerAdapter adapter = new ScreenSlidePagerAdapter(this);
        binding.productViewPager.setAdapter(adapter);
        new TabLayoutMediator(
                binding.tabLayout,
                binding.productViewPager,
                (tab, position) -> tab.setText(getTabName(position))
        ).attach();

        // 새로고침 레이아웃 설정!
        binding.refreshLayout.setOnRefreshListener(this::setProduct);
    }

    private void observeEvents() {
        viewModel.goBackEvent.observe(this, Void -> {
            finish();
        });
    }

    private String getTabName(int position) {
        switch (position) {
            case 0:
                return getString(R.string.tab_product_info);

            case 1:
                return getString(R.string.tab_product_review);

            default:
                return "Blank Tab";
        }
    }

    private void setProduct() {
        Bundle params = getIntent().getExtras();

        if (params == null) {
            Toast.makeText(this, "Wrong Call", Toast.LENGTH_SHORT).show();

            return;
        }

        long productId = params.getLong("product_id", -1);

        if (productId == -1) {
            Toast.makeText(this, "Wrong Call", Toast.LENGTH_SHORT).show();
            return;
        }

        viewModel.setAndLoadProduct(productId, () -> binding.refreshLayout.setRefreshing(false));
    }

    private static class ScreenSlidePagerAdapter extends FragmentStateAdapter {
        public ScreenSlidePagerAdapter(FragmentActivity fa) {
            super(fa);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                default:
                    return new ProductInfoFragment();

                case 1:
                    return new ProductReviewFragment();
            }
        }

        @Override
        public int getItemCount() {
            return 2;
        }
    }

    public static Intent callingIntentWithProductId(Context context, Long productId) {
        Intent i = new Intent(context, ProductActivity.class);
        Bundle params = new Bundle();
        params.putLong("product_id", productId);
        i.putExtras(params);

        return i;
    }
}