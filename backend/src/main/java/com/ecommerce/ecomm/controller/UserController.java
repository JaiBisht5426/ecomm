package com.ecommerce.ecomm.controller;

import com.ecommerce.ecomm.model.User;
import com.ecommerce.ecomm.repository.UserRepository;
import com.ecommerce.ecomm.util.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;

    public UserController(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public String registerUser(@Valid @RequestBody User dto) {

        User user = new User();

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        if(dto.getEmail().equals("admin@gmail.com"))
        {
            user.setRole("ADMIN");
        }
        else{
            user.setRole("USER");
        }
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setPhone(dto.getPhone());

        userRepository.save(user);

        return "User Registered Successfully ✅";
    }

    @GetMapping("/home")
    public String home() {

        return "Home Page";
    }


    @PostMapping("/login")
    public String loginUser(@RequestBody User loginRequest) {

        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found ❌"));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password ❌");
        }

        String token = jwtUtil.generateToken(user.getEmail(), user.getRole());

        return token;
    }

    @GetMapping("/protected")
    public String protectedApi(Authentication auth) {
        return "Welcome " + auth.getName();
    }


}
