package com.example.merge.network;

import android.annotation.SuppressLint;

import com.example.merge.entities.ProductReview;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ProductReviewDTO {
    @SerializedName("review_id")
    @Expose
    private Long reviewId;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("contents")
    @Expose
    private String contents;

    @SerializedName("rating")
    @Expose
    private Integer rating;

    @SerializedName("member")
    @Expose
    private MemberDTO member;

    @SerializedName("product")
    @Expose
    private ProductInfoDTO product;

    @Expose
    private Long createdAt;

    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public MemberDTO getMember() {
        return member;
    }

    public void setMember(MemberDTO member) {
        this.member = member;
    }

    public ProductInfoDTO getProduct() {
        return product;
    }

    public void setProduct(ProductInfoDTO product) {
        this.product = product;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public ProductReview toProductReview() {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = simpleDateFormat.format(new Date(createdAt));

        return new ProductReview(
                reviewId,
                title,
                contents,
                rating,
                member.toMember(),
                product.toProductInfo(),
                date
        );
    }
}
