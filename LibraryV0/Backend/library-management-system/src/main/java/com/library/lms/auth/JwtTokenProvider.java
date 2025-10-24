

/**
 * JwtTokenProvider is a Spring component responsible for generating and validating JWT tokens.
 * <p>
 * The JWT tokens are created using the HS512 algorithm and include the user's username as the subject,
 * as well as their role (if available) as an extra claim. This implementation is configured with a hardcoded
 * secret key for development and testing purposes. For production, a more secure hashed SECRET_KEY should be used.
 * </p>
 *
 * <p>
 * Note: The token is set to expire after 1 hour from the time of issuance.
 * </p>
 */
 
/**
 * Generates a JWT token for a given user.
 *
 * @param user the user for whom the token is generated; should contain a valid username and optional role details.
 * @return a JWT token as a String, which includes the username as the subject and the role as a claim,
 *         signed with the HS512 algorithm.
 */
 
/**
 * Extracts the username from the provided JWT token.
 *
 * @param token the JWT token from which the username (subject) is to be extracted.
 * @return the username if the token is valid and not expired; returns null if the token is invalid, expired, or cannot be parsed.
 *
 * @see io.jsonwebtoken.JwtException to handle cases of token parsing/validation failure.
 */
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
    // repeated mySuperSecretKey1234567890 five times for dev/test/MVP.
    // will not expose in codes, use hashed SHA256 key for prod.
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
