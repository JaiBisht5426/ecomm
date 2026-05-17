package com.ecommerce.ecomm.service;

import com.ecommerce.ecomm.model.User;
import com.ecommerce.ecomm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ForgotPassword
{
    @Autowired
    UserRepository userRepository;

    @Autowired
    EmailService emailService;
    public void forgotPassword(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        String token = UUID.randomUUID().toString();

        user.setResetToken(token);

        user.setTokenExpiry(
                LocalDateTime.now().plusMinutes(15)
        );

        userRepository.save(user);

        String resetLink =
                "http://localhost:3000/reset-password?token=" + token;

        emailService.sendEmail(
                user.getEmail(),
                "Reset Password",
                "Click here: " + resetLink
        );
    }
}
