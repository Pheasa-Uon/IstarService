package com.istar.service.Controller.Login;

import com.istar.service.Entity.Administrator.UsersManagment.User;
import com.istar.service.Repository.Administrator.UsersManagement.UserRepository;
import com.istar.service.Security.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*") // or configure via CorsConfig
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtUtils jwtUtils,
                          UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String token = jwtUtils.generateJwtToken(userDetails.getUsername());

        Optional<User> optionalUser = userRepository.findByUsername(userDetails.getUsername());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setLoginToken(token);
            user.setLastLoginAt(LocalDateTime.now());
            userRepository.save(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
        }

        return ResponseEntity.ok(Collections.singletonMap("token", token));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String authHeader,
                                         HttpServletRequest request) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().body("Missing or invalid Authorization header");
        }

        // ✅ Sanitize the token: remove Bearer, quotes, and trim spaces
        String token = authHeader.substring(7).replace("\"", "").trim();

        try {
            // ✅ Decode username from token
            String username = jwtUtils.getUserNameFromJwtToken(token);

            // ✅ Check user by username
            Optional<User> optionalUser = userRepository.findByUsername(username);

            if (optionalUser.isPresent()) {
                User user = optionalUser.get();

                // ✅ Clear token from database
                user.setLoginToken(null);
                userRepository.save(user);

                // ✅ Optional: Invalidate the session if exists
                HttpSession session = request.getSession(false);
                if (session != null) {
                    session.invalidate();
                }

                return ResponseEntity.ok("Logged out");
            } else {
                return ResponseEntity.status(401).body("Invalid token - user not found");
            }

        } catch (Exception e) {
            // ✅ Token parsing failed (e.g., malformed token)
            return ResponseEntity.status(401).body("Token parsing failed: " + e.getMessage());
        }
    }



    public static class LoginRequest {
        private String username;
        private String password;

        // Getters and setters
        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }

        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }
}
