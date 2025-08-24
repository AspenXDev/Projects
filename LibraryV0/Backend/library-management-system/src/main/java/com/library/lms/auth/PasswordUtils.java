package com.library.lms.auth;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtils {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    /** 
     * Generates BCrypt hash from a raw password.
     * Each call will generate a new hash due to salt.
     */
    public static String hashPassword(String rawPassword) {
        return encoder.encode(rawPassword);
    }

    /** 
     * Verify if raw password matches a stored hash.
     */
    public static boolean verifyPassword(String rawPassword, String storedHash) {
        return encoder.matches(rawPassword, storedHash);
    }

    /** 
     * CLI for testing
     */
    public static void main(String[] args) {
        String rawPassword = "secret123";
        String storedHash = "$2a$10$uTX7cQ8hEakuow3J1981lOye9L8PiQRH2QW3jxHjzUheyAZzJrL4W"; // check again...

        // Generate a new hash (will differ each run)
        String newHash = hashPassword(rawPassword);
        System.out.println("New hash (for reference): " + newHash);

        // Verify raw password against existing DB hash
        boolean matches = verifyPassword(rawPassword, storedHash);
        System.out.println("Does raw password match stored hash? " + matches);
    }
}
