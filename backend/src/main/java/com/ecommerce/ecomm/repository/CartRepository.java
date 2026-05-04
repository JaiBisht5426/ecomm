package com.ecommerce.ecomm.repository;

import com.ecommerce.ecomm.model.CartItem;
import com.ecommerce.ecomm.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<CartItem, Long> {

    CartItem getCartById(int id);

    List<CartItem> findByUserEmail(String email);

    CartItem findByUserEmailAndProduct_Id(String email, Long id);
}

