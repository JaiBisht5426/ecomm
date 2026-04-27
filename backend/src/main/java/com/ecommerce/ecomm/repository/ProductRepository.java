package com.ecommerce.ecomm.repository;

import com.ecommerce.ecomm.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}

