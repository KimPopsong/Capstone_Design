package server.study.random.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.study.random.advice.exception.ProductException;
import server.study.random.domain.Product;
import server.study.random.model.product.request.ProductSaveRequest;
import server.study.random.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public Product save(ProductSaveRequest productSaveRequest) {

        validateDuplicatedProductName(productSaveRequest.getName());

        Product product = Product.createProduct(productSaveRequest.getCode(), productSaveRequest.getImage_name(), productSaveRequest.getName(),
                productSaveRequest.getManufacturer(), productSaveRequest.getKind(), productSaveRequest.getServing_per_container(), productSaveRequest.getEnerge(),
                productSaveRequest.getCarbohydrate(), productSaveRequest.getDietary_fiber(), productSaveRequest.getSugar(), productSaveRequest.getProtein(),
                productSaveRequest.getTotal_fat(), productSaveRequest.getSaturated_fat(), productSaveRequest.getTrans_fat(), productSaveRequest.getCholesterol(),
                productSaveRequest.getSodium(), productSaveRequest.getCalcium(), productSaveRequest.getIron());

        return productRepository.save(product);
    }

    @Transactional
    public void saveDuple(ProductSaveRequest productSaveRequest) {
        Product product = Product.createProduct(productSaveRequest.getCode(), productSaveRequest.getImage_name(), productSaveRequest.getName(),
                productSaveRequest.getManufacturer(), productSaveRequest.getKind(), productSaveRequest.getServing_per_container(), productSaveRequest.getEnerge(),
                productSaveRequest.getCarbohydrate(), productSaveRequest.getDietary_fiber(), productSaveRequest.getSugar(), productSaveRequest.getProtein(),
                productSaveRequest.getTotal_fat(), productSaveRequest.getSaturated_fat(), productSaveRequest.getTrans_fat(), productSaveRequest.getCholesterol(),
                productSaveRequest.getSodium(), productSaveRequest.getCalcium(), productSaveRequest.getIron());
    }

    private void validateDuplicatedProductName(String name) {
        if (productRepository.findAll().stream().anyMatch(p -> p.getName().equals(name))) {
            throw new ProductException(name + "이미 존재하는 식품입니다.");
        }
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public List<Product> findByIdAndName(String keyword) {
        return this.getAll().stream().filter(p ->
                p.getId().toString().equals(keyword) ||
                        p.getName().contains(keyword)
        ).collect(Collectors.toList());
    }

    public Product getProductByString(String productName) {

        return productRepository.findByName(productName)
                .orElseThrow(() -> new ProductException("No Product"));
    }
}
