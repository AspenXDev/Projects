package com.library.lms.auth;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class HashCreator {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "secret123";
        String hash = encoder.encode(rawPassword);
        System.out.println("BCrypt hash for 'secret123': " + hash);
    }
}
