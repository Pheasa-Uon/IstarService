//package com.istar.service.test;
//
//import com.istar.service.model.User;
//import com.istar.service.repository.UserRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//@Configuration
//public class DataInitializer {
//
//    public static void main(String[] args) {
//        String hash = new BCryptPasswordEncoder().encode("P@ssw0rd");
//        System.out.println(hash);
//    }
//
//    @Bean
//    public CommandLineRunner initAdmin(UserRepository userRepository) {
//        return args -> {
//            if (userRepository.findByUsername("admin") == null) {
//                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//                User admin = new User();
//                admin.setUsername("admin");
//                admin.setPassword(encoder.encode("P@ssw0rd"));
//                //admin.setStatus("ACTIVE"); // optional, depends on your model
//                //admin.setRole("ADMIN");    // optional
//                userRepository.save(admin);
//                System.out.println("Admin user created");
//            } else {
//                System.out.println("Admin user already exists");
//            }
//        };
//    }
//}
