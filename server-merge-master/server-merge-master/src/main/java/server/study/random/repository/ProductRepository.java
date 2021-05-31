package server.study.random.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.study.random.domain.Product;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {

    Optional<Product> findByName(String name);
}
