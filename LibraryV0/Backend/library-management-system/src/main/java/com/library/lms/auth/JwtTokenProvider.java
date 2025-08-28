// PATH: src/main/java/com/library/lms/auth/JwtTokenProvider.java
package com.library.lms.auth;

import com.library.lms.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.JwtException;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    private static final String SECRET_KEY = "mySuperSecretKey1234567890mySuperSecretKey1234567890mySuperSecretKey1234567890mySuperSecretKey1234567890mySuperSecretKey1234567890";
    // repeated mySuperSecretKey1234567890 five times.
    /////repeating patterns are okay for development/test, more randomness to prevent predictability required for prod.
    private static final long EXPIRATION_TIME = 3600000; // 1 hour

    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("role", user.getRole() != null ? user.getRole().getRoleName() : null)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject();
        } catch (JwtException | IllegalArgumentException e) {
            return null; // invalid/expired token
        }
    }
}
