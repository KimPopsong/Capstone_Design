package com.example.merge.main;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.merge.entities.ProductInfo;
import com.example.merge.livedata.SingleLiveEvent;
import com.example.merge.network.ProductReviewDTO;
import com.example.merge.network.RetrofitInstanceFactory;

import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {

    private final MutableLiveData<List<ProductInfo>> _recentlyReviewedProducts = new MutableLiveData<>();
    public LiveData<List<ProductInfo>> recentlyReviewedProducts = _recentlyReviewedProducts;

    public SingleLiveEvent<Void> keywordSearchEvent = new SingleLiveEvent<>();
    public SingleLiveEvent<Void> cameraStartEvent = new SingleLiveEvent<>();

    public void startSearch() {
        keywordSearchEvent.call();
    }

    public void startCamera() {
        cameraStartEvent.call();
    }

    public void showAboutUs() {

    }

    public void showSettings() {

    }

    public void load() {
        RetrofitInstanceFactory.getInstance().getAllReviews().enqueue(new Callback<List<ProductReviewDTO>>() {
            @Override
            public void onResponse(@NonNull Call<List<ProductReviewDTO>> call, @NonNull Response<List<ProductReviewDTO>> response) {
                if (!response.isSuccessful()) {
                    return;
                }

                List<ProductReviewDTO> body = response.body();
                if (body == null) {
                    return;
                }

                List<ProductInfo> products = body
                        .stream()
                        .sorted((o1, o2) -> o2.getCreatedAt().compareTo(o1.getCreatedAt()))
                        .map(productReviewDTO -> productReviewDTO.getProduct().toProductInfo())
                        .distinct()
                        .limit(10)
                        .collect(Collectors.toList());

                _recentlyReviewedProducts.setValue(products);
            }

            @Override
            public void onFailure(@NonNull Call<List<ProductReviewDTO>> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
