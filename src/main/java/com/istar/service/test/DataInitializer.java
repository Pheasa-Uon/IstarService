//package com.istar.Service.test;
//
//import com.istar.Service.Entity.User;
//import com.istar.Service.Repository.UserRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.Security.crypto.bcrypt.BCryptPasswordEncoder;
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
//                //admin.setStatus("ACTIVE"); // optional, depends on your Entity
//                //admin.setRole("ADMIN");    // optional
//                userRepository.save(admin);
//                System.out.println("Admin user created");
//            } else {
//                System.out.println("Admin user already exists");
//            }
//        };
//    }
//}
