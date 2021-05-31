package server.study.random.model.review.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ReviewPatchRequest {
    @NotBlank
    private String title;

    @NotBlank
    private String contents;

    private Integer rating;
}
