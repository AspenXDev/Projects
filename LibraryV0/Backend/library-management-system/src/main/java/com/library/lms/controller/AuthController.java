package com.library.lms.controller;

import com.library.lms.auth.CustomUserDetailsService;
import com.library.lms.auth.JwtUtil;
import com.library.lms.dto.AuthRequest;
import com.library.lms.dto.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {   // <-- class starts here

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest authRequest) {
        // authenticate user
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getUsername(),
                        authRequest.getPassword()
                )
        );

        // load full UserDetails
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(authRequest.getUsername());

        // generate JWT token using UserDetails
        final String token = jwtUtil.generateToken(userDetails);

        return new AuthResponse(token);
    }
}
