package com.ecommerce.ecomm.controller;

import com.ecommerce.ecomm.model.User;
import com.ecommerce.ecomm.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3001")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public String registerUser(@Valid @RequestBody User dto) {

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setPhone(dto.getPhone());

        userRepository.save(user);

        return "User Registered Successfully ✅";
    }
}
