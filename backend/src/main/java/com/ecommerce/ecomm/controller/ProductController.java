package com.ecommerce.ecomm.controller;

import com.ecommerce.ecomm.model.Product;
import com.ecommerce.ecomm.repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository)

    {
        this.productRepository = productRepository;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public String addProduct(@RequestBody Product product) {
        productRepository.save(product);
        return "Product Added ✅";
    }

    @GetMapping("/filter")
    public List<Product> filterProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category
    ) {

        if (name != null && category != null) {
            return productRepository
                    .findByNameContainingIgnoreCaseAndCategory(name, category);
        }

        if (name != null) {
            return productRepository
                    .findByNameContainingIgnoreCase(name);
        }

        if (category != null) {
            return productRepository
                    .findByCategory(category);
        }

        return productRepository.findAll();
    }

    @PostMapping("/bulk")
    public String addProducts(@RequestBody List<Product> products) {

        productRepository.saveAll(products);

        return "Products Added Successfully ✅";
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/product")
    public Product getProductById(@RequestParam int id)
    {
        return productRepository.getProductById(id);
    }
}
