// PATH: src/main/java/com/library/lms/controller/AuthController.java
package com.library.lms.controller;

import com.library.lms.auth.JwtTokenProvider;
import com.library.lms.dto.AuthRequest;
import com.library.lms.dto.AuthResponse;
import com.library.lms.model.User;
import com.library.lms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// (Optional) Allow calls from Vite dev server
@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AuthController(UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @GetMapping("/me")
    public ResponseEntity<User> me(@RequestHeader(value = "Authorization", required = false) String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String token = authHeader.substring("Bearer ".length());
        String username = jwtTokenProvider.getUsernameFromToken(token);
        if (username == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        User user = userService.getUserByUsername(username);
        if (user == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        User user = userService.authenticate(authRequest.getUsername(), authRequest.getPassword());
        if (user == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        String token = jwtTokenProvider.generateToken(user);
        return ResponseEntity.ok(new AuthResponse(user, token));
    }
}
