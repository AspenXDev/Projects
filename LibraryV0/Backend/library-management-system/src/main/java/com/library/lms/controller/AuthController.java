package com.library.lms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.library.lms.auth.JwtTokenProvider;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // DTO for incoming JSON
    public static class LoginRequest {
        public String username;
        public String password;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        try {
            Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.username, req.password)
            );

            String token = jwtTokenProvider.generateToken(auth);

            return ResponseEntity.ok().body(
                java.util.Map.of(
                    "message", "Login successful",
                    "token", token
                )
            );
        } catch (BadCredentialsException ex) {
            return ResponseEntity.badRequest().body(
                java.util.Map.of(
                    "error", "Bad Request",
                    "message", "Bad credentials"
                )
            );
        }
    }
}
