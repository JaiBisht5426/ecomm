package com.ecommerce.ecomm.config;

import com.ecommerce.ecomm.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // 🔹 Step 1: Get Authorization Header
        String authHeader = request.getHeader("Authorization");

        // 🔹 Step 2: Check if header contains Bearer token
        if (authHeader != null && authHeader.startsWith("Bearer ")) {

            String token = authHeader.substring(7);

            try {
                // 🔹 Step 3: Validate token first
                if (jwtUtil.validateToken(token)) {

                    // 🔹 Step 4: Extract user info (email)
                    String email = jwtUtil.extractEmail(token);

                    // 🔹 Step 5: Avoid duplicate authentication
                    if (SecurityContextHolder.getContext().getAuthentication() == null) {

                        UsernamePasswordAuthenticationToken authentication =
                                new UsernamePasswordAuthenticationToken(
                                        email,
                                        null,
                                        Collections.emptyList()
                                );

                        // 🔹 Step 6: Set authentication in context
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                } else {
                    System.out.println("Invalid or Expired Token ❌");
                }

            } catch (Exception e) {
                System.out.println("JWT Error: " + e.getMessage());
            }
        }

        // 🔹 Step 7: Continue filter chain
        filterChain.doFilter(request, response);
    }
}
