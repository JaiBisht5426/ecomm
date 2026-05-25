package com.ecommerce.ecomm.controller;

import com.ecommerce.ecomm.model.Product;
import com.ecommerce.ecomm.repository.CartRepository;
import com.ecommerce.ecomm.repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductRepository productRepository;
    private final CartRepository cartRepository;

    public ProductController(ProductRepository productRepository, CartRepository cartRepository)

    {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> addProduct(@Valid @RequestBody Product product)
    {
        Optional<Product> existingProduct = productRepository.findByName(product.getName());

        if(existingProduct.isPresent())
        {
            return ResponseEntity.badRequest().body(
                    Map.of("message", "Product already exists ❌")
            );
        }
        productRepository.save(product);
        return ResponseEntity.ok(Map.of("message", "Product Added ✅"));
    }

//    @PreAuthorize("hasRole('User')")
    @GetMapping("/viewproducts")
    public List<Product> viewProducts()
    {
        return productRepository.findAll();
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

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteProduct(@PathVariable Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        cartRepository.deleteByProductId(id);
        productRepository.delete(product);

        return "Product Deleted ❌";
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String updateProduct(@PathVariable Long id,
                                @RequestBody Product updatedProduct) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setName(updatedProduct.getName());
        product.setDescription(updatedProduct.getDescription());
        product.setPrice(updatedProduct.getPrice());
        product.setQuantity(updatedProduct.getQuantity());
        product.setCategory(updatedProduct.getCategory());
        product.setImageUrl(updatedProduct.getImageUrl());

        productRepository.save(product);

        return "Product Updated ✏️";
    }


}
