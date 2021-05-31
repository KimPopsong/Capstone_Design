package com.example.merge.network;

public class WriteReviewDTO {
    private final Long productId;
    private final String title;
    private final String contents;
    private final Integer rating;

    public WriteReviewDTO(Long productId, String title, String contents, Integer rating) {
        this.productId = productId;
        this.title = title;
        this.contents = contents;
        this.rating = rating;
    }

    public Long getProductId() {
        return productId;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public Integer getRating() {
        return rating;
    }
}
