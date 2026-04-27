package com.ecommerce.ecomm.controller;

import com.ecommerce.ecomm.model.Product;
import com.ecommerce.ecomm.repository.ProductRepository;
import jakarta.validation.Valid;
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

    @PostMapping
    public String addProduct(@Valid @RequestBody Product product) {

        productRepository.save(product);

        return "Product Added Successfully ✅";
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

}
