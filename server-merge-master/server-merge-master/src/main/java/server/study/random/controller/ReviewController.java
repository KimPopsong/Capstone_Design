package server.study.random.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.study.random.config.security.LoginMember;
import server.study.random.domain.Member;
import server.study.random.domain.Review;
import server.study.random.model.review.request.ReviewPatchRequest;
import server.study.random.model.review.request.ReviewSaveRequest;
import server.study.random.model.review.response.ReviewResponse;
import server.study.random.service.ReviewService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    /**
     * 리뷰 id로 리뷰 하나 조회
     *
     * @param reviewId 조회할 리뷰 id
     * @return 리뷰 응답
     * @throws Exception 그런 리뷰 없으면 터짐
     */
    @GetMapping("/reviews/{reviewId}")
    public ReviewResponse getReview(@PathVariable Long reviewId){
        System.out.println(reviewService.get(reviewId).getCreatedAt());
        return new ReviewResponse(reviewService.get(reviewId));
    }

    /**
     * 조건별로 리뷰 여러개 조회
     *
     * @param productId 조회할 리뷰의 제품 id. 모두 조회하고 싶으면 0을 쓰거나 안 쓰고 놔두면 됨
     * @return 리뷰 응답
     */
    @GetMapping("/reviews")
    public List<ReviewResponse> getReviews(@RequestParam(required = false, defaultValue = "0") Long productId) {
        if (productId == 0) {
            return reviewService.getAll().stream().map(ReviewResponse::new).collect(Collectors.toList());
        } else {
            return reviewService.filterByProductId(productId).stream().map(ReviewResponse::new).collect(Collectors.toList());
        }
    }

    /**
     * 리뷰 작성
     *
     * @param member            로그인한 사용자 정보
     * @param reviewSaveRequest 리뷰 작성 요청 body
     * @return 리뷰 잘 저장됐다는 응답
     */
    @PostMapping("/reviews")
    public ReviewResponse saveReview(@LoginMember Member member, @RequestBody @Valid ReviewSaveRequest reviewSaveRequest) {
        Review savedReview = reviewService.save(member, reviewSaveRequest.getProductId(), reviewSaveRequest.getTitle(),reviewSaveRequest.getContents(),  reviewSaveRequest.getRating());

        return new ReviewResponse(savedReview);
    }

    /**
     * 리뷰 삭제
     */

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity deleteReview(@PathVariable Long reviewId) {
        reviewService.deleteById(reviewId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * 리뷰 업데이트
     * @param reviewId
     * @param reviewPatchRequest
     * @return
     */
    @PatchMapping("/reviews/{reviewId}")
    public ResponseEntity patchReview(@PathVariable Long reviewId, ReviewPatchRequest reviewPatchRequest) {
        reviewService.patchById(reviewId, reviewPatchRequest);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
