package server.study.random.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import server.study.random.domain.Product;
import server.study.random.model.product.request.ProductSaveRequest;
import server.study.random.model.product.response.ProductResponse;
import server.study.random.repository.ProductRepository;
import server.study.random.service.ProductService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductRepository productRepository;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/products")
    public void save(@RequestBody @Valid List<ProductSaveRequest> productListSaveRequest) {
        for (ProductSaveRequest req : productListSaveRequest) {
            try {
                productService.save(req);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @PostMapping("/dupleProducts")
    public void saveDuple(@RequestBody @Valid ProductSaveRequest productSaveRequest) {
        productService.saveDuple(productSaveRequest);
    }

    @GetMapping("/products/{productId}")
    public ProductResponse sendProductByProductId(@PathVariable("productId") Long id, HttpServletResponse response) {
        Optional<Product> requestProduct = productRepository.findById(id);

        if (!requestProduct.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found");
        }

        return new ProductResponse(requestProduct.get());
    }
/*
    @GetMapping("/products/{productName}")
    public Long getProductByName(@PathVariable("productName") String productName) {
        Product product = productService.getProductByString(productName);

        return product.getId();
    }
*/
    @GetMapping("/products")
    public List<ProductResponse> getProducts(@RequestParam(required = false, defaultValue = "") String keyword) {
        if (keyword.equals("")) {
            return productService.getAll().stream().map(ProductResponse::new).collect(Collectors.toList());
        } else {
            return productService.findByIdAndName(keyword).stream().map(ProductResponse::new).collect(Collectors.toList());
        }
    }
}