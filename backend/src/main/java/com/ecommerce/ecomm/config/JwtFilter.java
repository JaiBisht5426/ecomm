package com.ecommerce.ecomm.config;

import com.ecommerce.ecomm.util.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

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

        // 🔹 1. Authorization header lo
        String authHeader = request.getHeader("Authorization");

        // 🔹 2. Check karo Bearer token hai ya nahi
        if (authHeader != null && authHeader.startsWith("Bearer ")) {

            String token = authHeader.substring(7);

            try {
                // 🔹 3. Validate token
                if (jwtUtil.validateToken(token)) {

                    // 🔹 4. Claims extract karo
                    Claims claims = jwtUtil.extractClaims(token);

                    String email = claims.getSubject();
                    String role = claims.get("role", String.class);

                    // 🔹 5. Agar already authenticated nahi hai
                    if (SecurityContextHolder.getContext().getAuthentication() == null) {

                        UsernamePasswordAuthenticationToken authentication =
                                new UsernamePasswordAuthenticationToken(
                                        email,
                                        null,
                                        List.of(new SimpleGrantedAuthority("ROLE_" + role))
                                );

                        // 🔹 6. Set authentication
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                } else {
                    System.out.println("Invalid or Expired Token ❌");
                }

            } catch (Exception e) {
                System.out.println("JWT Error ❌: " + e.getMessage());
            }
        }

        // 🔹 7. Aage request bhejo
        filterChain.doFilter(request, response);
    }
}
