package com.ecommerce.ecomm.repository;

import com.ecommerce.ecomm.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>
{
    List<Product> findByNameContainingIgnoreCase(String name);

    List<Product> findByCategory(String category);

    List<Product> findByNameContainingIgnoreCaseAndCategory(String name, String category);

    Product getProductById(Long id);
}

