package com.example.merge.network;

public class PatchReviewDTO {
    private final String title;
    private final String contents;
    private final Integer rating;

    public PatchReviewDTO(String title, String contents, Integer rating) {
        this.title = title;
        this.contents = contents;
        this.rating = rating;
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
