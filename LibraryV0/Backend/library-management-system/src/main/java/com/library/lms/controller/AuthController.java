// PATH: src/main/java/com/library/lms/controller/AuthController.java
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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    private final UserService userService;
    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AuthController(UserService userService, MemberService memberService, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.memberService = memberService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // ======================
    // Registration
    // ======================
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegistrationRequest request) {
        if (userService.existsByUsername(request.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new AuthResponse("Username already exists"));
        }
        if (userService.existsByEmail(request.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new AuthResponse("Email already exists"));
        }

        // Assign role explicitly
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPasswordHash(request.getPassword());
        user.setRole(userService.getRoleByName("Members")); // safe role assignment
        user = userService.createUser(user);

        // Create linked Member
        Member member = new Member();
        member.setFullName(request.getFullName());
        member = memberService.createMember(member, user);

        // Generate JWT
        String token = jwtTokenProvider.generateToken(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new AuthResponse(user, token));
    }

    // ======================
    // Login
    // ======================
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        User user = userService.authenticate(authRequest.getUsername(), authRequest.getPassword());
        String token = jwtTokenProvider.generateToken(user);
        return ResponseEntity.ok(new AuthResponse(user, token));
    }

    // ======================
    // Current user
    // ======================
    @GetMapping("/me")
    public ResponseEntity<User> me(@RequestHeader(value = "Authorization", required = false) String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String token = authHeader.substring("Bearer ".length());
        String username = jwtTokenProvider.getUsernameFromToken(token);
        User user = userService.getUserByUsername(username);
        return ResponseEntity.ok(user);
    }
}
