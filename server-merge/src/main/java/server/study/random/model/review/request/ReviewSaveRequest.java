package server.study.random.model.review.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.Date;

@Data
public class ReviewSaveRequest {
    @Positive
    private Long productId;

    @NotBlank
    private String contents;

    @NotBlank
    private String title;

    private Integer rating;
    private Date createdAt;
}
