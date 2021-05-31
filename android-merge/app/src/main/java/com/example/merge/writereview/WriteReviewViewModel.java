package com.example.merge.writereview;

import android.widget.RatingBar;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.ViewModel;

import com.example.merge.livedata.SingleLiveEvent;
import com.example.merge.network.RetrofitInstanceFactory;
import com.example.merge.network.WriteReviewDTO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WriteReviewViewModel extends ViewModel {
    public ObservableField<String> contentsField = new ObservableField<>();
    public ObservableField<String> titleField = new ObservableField<>();
    public ObservableInt ratingField = new ObservableInt();

    // To send events to activity.
    public SingleLiveEvent<Boolean> writeReviewResultEvent = new SingleLiveEvent<>();

    private Long productId;

    // MUST CALL!
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void writeReview() {
        String reviewBody = contentsField.get();
        String titleBody = titleField.get();
        Integer ratingBody = ratingField.get();

        if (reviewBody == null || titleBody == null || ratingField == null) {
            return;
        }

        RetrofitInstanceFactory.getInstance().writeReview(new WriteReviewDTO(productId, titleBody, reviewBody, ratingBody)).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if (response.isSuccessful()) {
                    // 서버가 200~299 응답을 줬을 때.
                    onWriteSuccess();
                } else {
                    // 서버가 200대가 아닌 응답을 줬을 때.
                    onWriteFailure();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                // 서버가 꺼졌거나 네트워크가 맛탱이가 가서 암튼 서버랑 얘기를 할 수 없을 때.
                t.printStackTrace();
                onWriteFailure();
            }
        });
    }

    public RatingBar.OnRatingBarChangeListener scoreChangeListener = new RatingBar.OnRatingBarChangeListener() {
        @Override
        public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
            ratingField.set((int) v);
        }
    };

    private void onWriteSuccess() {
        writeReviewResultEvent.setValue(true);
    }

    private void onWriteFailure() {
        writeReviewResultEvent.setValue(false);
    }
}