package com.ecommerce.ecomm.repository;

import com.ecommerce.ecomm.model.Order;
import com.ecommerce.ecomm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long>
{
    List<Order> findByUser(User user);
}

