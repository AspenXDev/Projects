package com.library.lms.auth;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class PasswordUtils {

    public static String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt(12));
    }

    public static boolean verifyPassword(String plainPassword, String hashed) {
        return BCrypt.checkpw(plainPassword, hashed);
    }
}
