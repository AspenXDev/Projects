package com.library.lms.controller;

import com.library.lms.auth.JwtTokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // DTO for login request
    public static class LoginRequest {
        @NotBlank(message = "Username cannot be blank")
        public String username;

        @NotBlank(message = "Password cannot be blank")
        public String password;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@Valid @RequestBody LoginRequest request) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.username, request.password)
            );

            String token = jwtTokenProvider.generateToken(auth);
            logger.info("User '{}' logged in successfully", request.username);

            return ResponseEntity.ok(Map.of(
                    "message", "Login successful",
                    "token", token
            ));

        } catch (BadCredentialsException ex) {
            logger.warn("Failed login attempt for user '{}': bad credentials", request.username);
            return ResponseEntity.badRequest().body(Map.of(
                    "error", "Bad Request",
                    "message", "Bad credentials"
            ));
        } catch (Exception ex) {
            logger.error("Unexpected error during login for user '{}'", request.username, ex);
            return ResponseEntity.internalServerError().body(Map.of(
                    "error", "Internal Server Error",
                    "message", "Login failed due to server error"
            ));
        }
    }
}
