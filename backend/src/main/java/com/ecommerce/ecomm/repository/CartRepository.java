package com.ecommerce.ecomm.repository;

import com.ecommerce.ecomm.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<CartItem, Long> {

}

