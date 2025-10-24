
/**
 * Utility class for generating and validating JSON Web Tokens (JWT).
 * <p>
 * This class provides methods for creating JWT tokens with claims, retrieving information from
 * the token such as the username (subject), and validating the token against a set of user details.
 * </p>
 * <p>
 * The token is signed using the HS256 algorithm with a secret key. The expiration time is set to 1 hour.
 * In production, avoid hardcoding sensitive information like the secret key; instead, load them from a secure configuration.
 * </p>
 *
 * @see <a href="https://github.com/jwtk/jjwt">Java JWT (JJWT) library</a>
 */
 
/**
 * Generates a JWT token for the given user.
 *
 * @param userDetails the user details containing information (e.g., username) to be embedded in the token.
 * @return the generated JWT token as a String.
 *
 * <p>
 * This method creates an empty claims map and delegates the token creation to {@link #createToken(Map, String)}.
 * </p>
 */
 
/**
 * Creates a JWT token with the specified claims and subject.
 *
 * @param claims  a map containing claims to be added to the token payload.
 * @param subject the subject of the token, typically the username.
 * @return a compact representation of the JWT token as a String.
 *
 * <p>
 * This method sets the issued at and expiration dates, signs the token with a key constructed from the secret,
 * and returns the compact JWT.
 * </p>
 */
 
/**
 * Extracts the username (subject) from the given JWT token.
 *
 * @param token the JWT token from which the username is to be extracted.
 * @return the username embedded in the token.
 *
 * <p>
 * It utilizes {@link #extractClaim(String, Function)} with a resolver function to extract the subject.
 * </p>
 */
 
/**
 * Validates the given JWT token against the provided user details.
 *
 * @param token       the JWT token to be validated.
 * @param userDetails the user details to be matched against the token's subject.
 * @return true if the token is valid and not expired, false otherwise.
 *
 * <p>
 * This method checks that the extracted username from the token equals the username in the given user details
 * and also verifies that the token has not expired.
 * </p>
 */
 
/**
 * Extracts a specific claim from the JWT token using the provided claims resolver function.
 *
 * @param <T>             the type of the claim to be extracted.
 * @param token           the JWT token from which the claim is extracted.
 * @param claimsResolver  a function to resolve and extract the specific claim from the token's body.
 * @return the extracted claim of type T.
 *
 * <p>
 * The method parses the token using the secret key and applies the resolver function to the resulting Claims object.
 * </p>
 */
 
/**
 * Checks whether the given JWT token has expired.
 *
 * @param token the JWT token to be checked.
 * @return true if the token has expired, false otherwise.
 *
 * <p>
 * It obtains the expiration timestamp from the token and compares it with the current date.
 * </p>
 */
package com.library.lms.auth;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    // Coursework secret; in production, move to config or environment variable
    private final String SECRET_KEY = "mySuperSecretKey1234567890!mySuperSecretKey1234567890!";

    private final long EXPIRATION_MS = 1000 * 60 * 60; // 1 hour

    /** Generate JWT token for a user */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    /** Create JWT with claims and subject */
    private String createToken(Map<String, Object> claims, String subject) {
        Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /** Extract username from JWT */
    public String getUsernameFromToken(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /** Validate token against user details */
    public boolean validateToken(String token, UserDetails userDetails) {
        return getUsernameFromToken(token).equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    /** Extract a claim using a resolver function */
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
        return claimsResolver.apply(
                Jwts.parserBuilder()
                        .setSigningKey(key)
                        .build()
                        .parseClaimsJws(token)
                        .getBody()
        );
    }

    /** Check if token has expired */
    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }
}