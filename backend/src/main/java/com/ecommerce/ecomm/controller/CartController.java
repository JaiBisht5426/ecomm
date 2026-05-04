package com.ecommerce.ecomm.controller;

import com.ecommerce.ecomm.model.CartItem;
import com.ecommerce.ecomm.model.Product;
import com.ecommerce.ecomm.repository.CartRepository;
import com.ecommerce.ecomm.repository.ProductRepository;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "*")
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

        String email = auth.getName();

        Product product = productRepository.findById(item.getProduct().getId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // 🔥 Check if already exists
        CartItem existingItem = cartRepository
                .findByUserEmailAndProduct_Id(email, product.getId());

        if (existingItem != null) {
            // ✅ update quantity
            existingItem.setQuantity(existingItem.getQuantity() + item.getQuantity());
            cartRepository.save(existingItem);
        } else {
            // ✅ new item
            item.setUserEmail(email);
            item.setProduct(product);
            cartRepository.save(item);
        }

        return "Added to cart ✅";
    }


    @GetMapping
    public List<CartItem> getCartItems(Authentication auth) {

        String email = auth.getName();

        return cartRepository.findByUserEmail(email);
    }

//    @GetMapping
//    public CartItem getCartByid(@RequestParam int id)
//    {
//        return cartRepository.getCartById(id);
//    }
}
