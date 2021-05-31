package com.example.merge.entities;

import com.example.merge.untility.GoogleTranslate;

public class ProductReview {
    private Long id;
    private String title;
    private String contents;
    private Integer rating;
    private Member member;
    private ProductInfo product;
    private String createdAt;

    public ProductReview(Long id, String title, String contents, Integer rating, Member member, ProductInfo product, String createdAt) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.rating = rating;
        this.member = member;
        this.product = product;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return GoogleTranslate.translate(title);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return GoogleTranslate.translate(contents);
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

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public ProductInfo getProduct() {
        return product;
    }

    public void setProduct(ProductInfo product) {
        this.product = product;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}