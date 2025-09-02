package com.library.lms.controller;

import com.library.lms.auth.JwtTokenProvider;
import com.library.lms.dto.AuthRequest;
import com.library.lms.dto.AuthResponse;
import com.library.lms.dto.RegistrationRequest;
import com.library.lms.model.Member;
import com.library.lms.model.User;
import com.library.lms.service.MemberService;
import com.library.lms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    private final UserService userService;
    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AuthController(UserService userService,
                          MemberService memberService,
                          JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.memberService = memberService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // ======================
    // Registration
    // ======================
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegistrationRequest request) {

        // Basic validation
        if (request == null
                || !StringUtils.hasText(request.getUsername())
                || !StringUtils.hasText(request.getEmail())
                || !StringUtils.hasText(request.getPassword())
                || !StringUtils.hasText(request.getFullName())) {
            return ResponseEntity.badRequest().body(new AuthResponse("username, email, password, and fullName are required"));
        }

        if (userService.existsByUsername(request.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new AuthResponse("Username already exists"));
        }
        if (userService.existsByEmail(request.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new AuthResponse("Email already exists"));
        }

        // Create User (role = Members, isActive = true)
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPasswordHash(request.getPassword()); // service will encode
        user.setRole(userService.getRoleByName("Members"));
        user.setIsActive(true);

        user = userService.createUser(user); // hashes & saves

        // Create linked Member
        Member member = new Member();
        member.setFullName(request.getFullName());
        memberService.createMember(member, user);

        // JWT
        String token = jwtTokenProvider.generateToken(user);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new AuthResponse(user, token));
    }

    // ======================
    // Login
    // ======================
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        if (authRequest == null
                || !StringUtils.hasText(authRequest.getUsername())
                || !StringUtils.hasText(authRequest.getPassword())) {
            return ResponseEntity.badRequest().body(Map.of(
                    "error", "username and password are required"
            ));
        }

        try {
            User user = userService.authenticate(authRequest.getUsername(), authRequest.getPassword());
            String token = jwtTokenProvider.generateToken(user);
            return ResponseEntity.ok(new AuthResponse(user, token));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                    "error", "Invalid credentials"
            ));
        }
    }

    // ======================
    // Current user
    // ======================
    @GetMapping("/me")
    public ResponseEntity<?> me(@RequestHeader(value = "Authorization", required = false) String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Missing token"));
        }
        String token = authHeader.substring("Bearer ".length());
        String username = jwtTokenProvider.getUsernameFromToken(token);
        if (!StringUtils.hasText(username)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid token"));
        }

        User user = userService.getUserByUsername(username);
        return ResponseEntity.ok(user);
    }
}
