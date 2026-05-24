package com.ecommerce.ecomm.controller;

import com.ecommerce.ecomm.dto.ForgotPasswordRequest;
import com.ecommerce.ecomm.dto.ResetPasswordRequest;
import com.ecommerce.ecomm.model.User;
import com.ecommerce.ecomm.repository.UserRepository;
import com.ecommerce.ecomm.service.ForgotPassword;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class ForgotPasswordController
{
    @Autowired
    ForgotPassword authService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(
            @RequestBody ForgotPasswordRequest request
    ) {

        authService.forgotPassword(request.getEmail());

        return ResponseEntity.ok("Reset link sent");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@Valid @RequestBody ResetPasswordRequest request
    ) {

        User user = userRepository
                .findByResetToken(request.getToken())
                .orElseThrow(() ->
                        new RuntimeException("Invalid token"));

        if (user.getTokenExpiry()
                .isBefore(LocalDateTime.now())) {

            throw new RuntimeException("Token expired");
        }

        user.setPassword(
                passwordEncoder.encode(
                        request.getNewPassword()
                )
        );

        user.setResetToken(null);
        user.setTokenExpiry(null);

        userRepository.save(user);

        return ResponseEntity.ok(
                "Password reset successful"
        );
    }
}
