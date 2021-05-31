package server.study.random.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.study.random.advice.exception.ProductException;
import server.study.random.advice.exception.ReviewException;
import server.study.random.domain.Member;
import server.study.random.domain.Product;
import server.study.random.domain.Review;
import server.study.random.model.review.request.ReviewPatchRequest;
import server.study.random.repository.ProductRepository;
import server.study.random.repository.ReviewRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;

    public Review get(Long reviewId) {
        return reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewException("No Review Exists"));
    }

    public List<Review> getAll() {
        return reviewRepository.findAll();
    }

    public List<Review> filterByProductId(Long productId) {
        // 언젠가 바꾸겠지...
        return this.getAll().stream().filter(r -> r.getProduct().getId().equals(productId)).collect(Collectors.toList());
    }

    @Transactional
    public Review save(Member member, Long productId, String title, String contents, Integer rating) {
        Product findProduct = productRepository.findById(productId)
                .orElseThrow(() -> new ProductException("No Product Exists"));

        List<Review> reviews = filterByProductId(productId);

        for (Review value : reviews) {
            if (value.getMember().getId().equals(member.getId())) {
                throw new ReviewException("Already Write");
            }
        }

        Review review = Review.createReview(contents, title, rating, member, findProduct);

        return reviewRepository.save(review);
    }

    @Transactional
    public void deleteById(Long reviewId) {
        Review findReview = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewException("No Review Exists"));

        reviewRepository.delete(findReview);
    }

    @Transactional
    public void patchById(Long reviewId, ReviewPatchRequest reviewPatchRequest) {
        Review findReview = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewException("No Review Exists"));

        findReview.patch(reviewPatchRequest.getTitle(), reviewPatchRequest.getContents(), reviewPatchRequest.getRating());
    }
}
