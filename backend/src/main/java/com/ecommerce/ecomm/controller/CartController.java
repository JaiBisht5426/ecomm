package com.ecommerce.ecomm.controller;

import com.ecommerce.ecomm.model.CartItem;
import com.ecommerce.ecomm.model.Product;
import com.ecommerce.ecomm.repository.CartRepository;
import com.ecommerce.ecomm.repository.ProductRepository;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
public class CartController
{

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartController(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    @PostMapping
    public String addToCart(@RequestBody CartItem item, Authentication auth) {

        item.setUserEmail(auth.getName());
        Product product = productRepository.findById(item.getProduct().getId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        item.setProduct(product);

        cartRepository.save(item);

        return "Added to cart ✅";
    }

}
