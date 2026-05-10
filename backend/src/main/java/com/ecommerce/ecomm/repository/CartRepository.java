package com.ecommerce.ecomm.repository;

import com.ecommerce.ecomm.model.CartItem;
import com.ecommerce.ecomm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<CartItem, Long> {

    CartItem getCartById(int id);

    List<CartItem> findByUserEmail(String email);

    CartItem findByUserEmailAndProduct_Id(String email, Long id);

//    List<CartItem> findByUser(User user);
}

