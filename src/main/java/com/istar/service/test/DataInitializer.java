//package com.istar.service.test;
//
//import com.istar.service.Entity.Administrator.UsersManagment.User;
//import com.istar.service.Repository.Administrator.UsersManagement.UserRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//@Configuration
//public class DataInitializer {
//
//    public static void main(String[] args) {
//        String hash = new BCryptPasswordEncoder().encode("123456");
//        System.out.println(hash);
//    }
//
//    @Bean
//    public CommandLineRunner initAdmin(UserRepository userRepository) {
//        return args -> {
//            if (userRepository.findByUsername("acc") == null) {
//                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//                User admin = new User();
//                admin.setUsername("acc");
//                admin.setPassword(encoder.encode("123456"));
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
