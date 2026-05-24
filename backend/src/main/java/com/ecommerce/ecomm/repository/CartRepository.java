package com.ecommerce.ecomm.repository;

import com.ecommerce.ecomm.model.CartItem;
import com.ecommerce.ecomm.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Transactional
public interface CartRepository extends JpaRepository<CartItem, Long> {

    CartItem getCartById(int id);

    List<CartItem> findByUserEmail(String email);

    CartItem findByUserEmailAndProduct_Id(String email, Long id);

    void deleteByProductId(Long productId);

//    List<CartItem> findByUser(User user);
}

