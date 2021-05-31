package com.example.merge.product;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.merge.Config;
import com.example.merge.entities.ProductInfo;
import com.example.merge.entities.ProductReview;
import com.example.merge.livedata.SingleLiveEvent;
import com.example.merge.network.ProductInfoDTO;
import com.example.merge.network.ProductReviewDTO;
import com.example.merge.network.RetrofitInstanceFactory;
import com.example.merge.untility.EmptyCallback;
import com.example.merge.untility.GoogleTranslate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductViewModel extends ViewModel {
    // 첫번째 탭의 ProductInfoFragment가 참조.
    private final MutableLiveData<ProductInfo> _productInfo = new MutableLiveData<>();
    public LiveData<ProductInfo> productInfo = _productInfo;

    // 번역된 상품 명
    private final MutableLiveData<String> _productInfoTranslated = new MutableLiveData<>();
    public LiveData<String> productInfoTranslated = _productInfoTranslated;

    // 두번째 탭의 ProductReviewFragment가 참조.
    private final MutableLiveData<List<ProductReview>> _productReviews = new MutableLiveData<>();
    public LiveData<List<ProductReview>> productReviews = _productReviews;

    private final MutableLiveData<String> _productImageUrl = new MutableLiveData<>();
    public LiveData<String> productImageUrl = _productImageUrl;  // For Product Image

    // 상품의 점수
    private final MutableLiveData<String> _productScore = new MutableLiveData<>();
    public LiveData<String> productScore = _productScore;

    public SingleLiveEvent<Void> writeNewReviewEvent = new SingleLiveEvent<>();

    public SingleLiveEvent<Void> goBackEvent = new SingleLiveEvent<>();

    private Long productId = -1L;
    double ratingAvg = 0;

    // 제품 정보와 리뷰 가져오기(서버로부터).
    public void setAndLoadProduct(Long productId, EmptyCallback onFinishLoad) {
        this.productId = productId;

        List<Long> count = new ArrayList<>(1);
        count.add(0L);

        loadProductInfo(productId, () -> {
            count.set(0, count.get(0) + 1);

            if (count.get(0) >= 2) {
                onFinishLoad.action();
            }
        });

        loadProductReview(productId, () -> {
            count.set(0, count.get(0) + 1);

            if (count.get(0) >= 2) {
                onFinishLoad.action();
            }
        });
    }

    public Long getProductId() throws Exception {
        if (this.productId == -1L) {
            throw new Exception("No Product Id");
        }

        return this.productId;
    }

    private void loadProductInfo(Long productId, EmptyCallback onFinish) {
        RetrofitInstanceFactory.getInstance().getProduct(productId).enqueue(new Callback<ProductInfoDTO>() {
            @Override
            public void onResponse(@NonNull Call<ProductInfoDTO> call, @NonNull Response<ProductInfoDTO> response) {
                if (!response.isSuccessful()) {
                    return;
                }

                ProductInfoDTO body = response.body();
                if (body == null) {
                    return;
                }

                ProductInfo info = body.toProductInfo();

                // 값을 성공적으로 가져 왔으면 뷰에게 알려주기 위해 _productInfo에 설정
                _productInfo.postValue(info);

                // 선택된 언어가 한국어이면, 번역 생략
                if (Config.selectedLanguage.equals("ko")) {
                    _productInfoTranslated.postValue(null);
                } else {
                    _productInfoTranslated.postValue(GoogleTranslate.translate(info.getName()));
                }

                _productImageUrl.postValue(Config.productImageBaseUrl + info.getImage_name());

                onFinish.action();
            }

            @Override
            public void onFailure(@NonNull Call<ProductInfoDTO> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void loadProductReview(Long productId, EmptyCallback onFinish) {
        RetrofitInstanceFactory.getInstance().getReviews(productId).enqueue(new Callback<List<ProductReviewDTO>>() {
            @Override
            public void onResponse(@NonNull Call<List<ProductReviewDTO>> call, @NonNull Response<List<ProductReviewDTO>> response) {

                if (!response.isSuccessful()) {
                    return;
                }

                List<ProductReviewDTO> body = response.body();
                if (body == null) {
                    return;
                }

                List<ProductReview> reviews = body.stream().map(ProductReviewDTO::toProductReview).collect(Collectors.toList());

                double ratingAverage = 0;

                for (int i = 0; i < reviews.size(); i++) {
                    int num = reviews.get(i).getRating();
                    ratingAverage += num;
                }

                ratingAvg = ratingAverage / reviews.size();
                ratingAvg = Math.round(ratingAvg * 10) / 10.0;  // round

                _productReviews.setValue(reviews);
                _productScore.setValue(String.valueOf(ratingAvg));

                onFinish.action();
            }

            @Override
            public void onFailure(@NonNull Call<List<ProductReviewDTO>> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });
    }

    // 두번째 탭의 ProductReviewFragment가 참조  새 리뷰 작성하는 화면 띄우기.
    public void onClickNewReview() {
        writeNewReviewEvent.call();
    }

    public void goBack() {
        goBackEvent.call();
    }
}
