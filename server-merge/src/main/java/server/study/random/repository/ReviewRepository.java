package server.study.random.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.study.random.domain.Review;

public interface ReviewRepository extends JpaRepository<Review,Long> {
}
