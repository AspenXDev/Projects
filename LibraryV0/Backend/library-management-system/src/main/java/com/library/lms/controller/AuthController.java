package com.library.lms.controller;

import com.library.lms.auth.CustomUserDetails;
import com.library.lms.auth.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    public static class LoginRequest {
        public String username;
        public String password;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        logger.info("Login attempt for user: {}", req.username);

        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(req.username, req.password)
            );

            if (!(auth.getPrincipal() instanceof CustomUserDetails)) {
                return ResponseEntity.internalServerError().body(Map.of(
                        "error", "Internal Error",
                        "message", "Unexpected principal type"
                ));
            }

            CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
            String token = jwtUtil.generateToken(userDetails);

            return ResponseEntity.ok(Map.of(
                    "token", token,
                    "role", userDetails.getUser().getRole().getRoleName()
            ));

        } catch (BadCredentialsException ex) {
            return ResponseEntity.badRequest().body(Map.of(
                    "error", "Bad credentials"
            ));
        }
    }
}
