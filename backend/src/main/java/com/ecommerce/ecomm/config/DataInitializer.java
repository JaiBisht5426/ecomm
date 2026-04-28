//package com.ecommerce.ecomm.config;
//
//import com.ecommerce.ecomm.model.User;
//import com.ecommerce.ecomm.repository.UserRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//@Configuration
//public class DataInitializer {
//
//    @Bean
//    CommandLineRunner init(UserRepository userRepository,
//                           BCryptPasswordEncoder passwordEncoder) {
//        return args -> {
//
//            // agar admin already exist nahi hai
//            if (userRepository.findByEmail("admin@gmail.com") == null) {
//
//                User admin = new User();
//                admin.setName("Admin");
//                admin.setEmail("admin@gmail.com");
//                admin.setPassword(passwordEncoder.encode("admin123")); // 👈 password
//                admin.setPhone("9234517902");
//                admin.setRole("ADMIN");
//
//                userRepository.save(admin);
//
//                System.out.println("Admin created ✅");
//            }
//        };
//    }
//}
