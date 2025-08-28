package com.library.lms.config;

public class JwtConfig {
    public static final String SECRET_KEY = "mySuperSecretKey1234567890!mySuperSecretKey1234567890!"; // hardcoded secret in jwtUtil too
    public static final long EXPIRATION_TIME = 3600000 ; // 1 hour (in milliseconds)
    // jwt.secret=MySuperSecretKey12345678901234567890
    // jwt.expiration=3600000 
}