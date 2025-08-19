package com.library.lms.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    // --------------------
    // Login endpoint
    // --------------------
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        try {
            // Authenticate user
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getUsername(),
                            authRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(401).body("Invalid username or password");
        }

        // Load user details
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());

        // Generate JWT token
        final String jwt = jwtUtil.generateToken(userDetails);

        // Return token
        return ResponseEntity.ok(new AuthResponse(jwt));
    }

    // Optional test endpoint to verify authentication
    @GetMapping("/test")
    public ResponseEntity<?> testAuth() {
        return ResponseEntity.ok("You are authenticated!");
    }

    // --------------------
    // Request / Response DTOs
    // --------------------
    @Data
    public static class AuthRequest {
        private String username;
        private String password;
    }

    @Data
    @AllArgsConst
