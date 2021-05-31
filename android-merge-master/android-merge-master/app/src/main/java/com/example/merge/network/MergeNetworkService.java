package com.example.merge.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * 레트로핏에서 사용할 요청들의 규격을 정의해놓는 인터페이스입니다.
 */
public interface MergeNetworkService {
    @POST("/members/login")
    Call<Void> login(@Body LoginRequestDTO loginInfo);

    @POST("/members")
    Call<Void> signUp(@Body SignUpDTO signUpInfo);

    @GET("/products/{id}")
    Call<ProductInfoDTO> getProduct(@Path("id") Long productId);

    @GET("/products")
    Call<List<ProductInfoDTO>> getProducts(@Query("keyword") String keyword);

    @GET("/reviews")
    Call<List<ProductReviewDTO>> getReviews(@Query("productId") Long productId);

    @GET("/reviews")
    Call<List<ProductReviewDTO>> getAllReviews();

    @POST("/reviews")
    Call<Void> writeReview(@Body WriteReviewDTO review);

    @DELETE("/reviews/{id}")
    Call<Void> deleteReview(@Path("id") Long reviewId);

    @PATCH("/reviews/{id}")
    Call<PatchReviewDTO> patchReview(@Path("id") Long reviewId);
}
