package server.study.random.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    private String title;
    private String contents;
    private Integer rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    private Date createdAt;

    public static Review createReview(String contents, String title, Integer rating, Member member, Product product) {
        Review review = new Review();
        review.contents = contents;
        review.title = title;
        review.rating = rating;
        review.member = member;
        review.product = product;
        review.createdAt = new Date();

        return review;
    }

    public void patch(String title, String contents, Integer rating) {
        this.title = title;
        this.contents = contents;
        this.rating = rating;
        this.createdAt = new Date();
    }
}
