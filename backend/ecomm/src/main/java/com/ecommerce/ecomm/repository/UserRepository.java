package com.ecommerce.ecomm.repository;

import com.ecommerce.ecomm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
