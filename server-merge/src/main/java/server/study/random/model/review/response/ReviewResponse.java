package server.study.random.model.review.response;

import lombok.Data;
import server.study.random.domain.Review;
import server.study.random.model.member.response.MemberResponse;
import server.study.random.model.product.response.ProductResponse;

@Data
public class ReviewResponse {

    private Long id;
    private String contents;
    private String title;
    private Integer rating;
    private MemberResponse member;
    private ProductResponse product;
    private Long createdAt;

    public ReviewResponse(Review review) {
        this.id = review.getId();
        this.contents = review.getContents();
        this.title = review.getTitle();
        this.rating = review.getRating();
        this.member = new MemberResponse(review.getMember());
        this.product = new ProductResponse(review.getProduct());
        this.createdAt = review.getCreatedAt().getTime();
    }
}
