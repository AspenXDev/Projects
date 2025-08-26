//DEPRECATED



//package com.library.lms.auth;
//
//import java.util.Date;
//
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//
//@Component
//public class JwtTokenProvider {
//
//    private final String jwtSecret = "yourSecretKey123"; // ❗ Move to config in production
//    private final long jwtExpirationMs = 86400000; // 1 day
//
//    public String generateToken(Authentication authentication) {
//        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
//        Date now = new Date();
//        Date expiryDate = new Date(now.getTime() + jwtExpirationMs);
//
//        return Jwts.builder()
//            .setSubject(userPrincipal.getUsername())
//            .setIssuedAt(now)
//            .setExpiration(expiryDate)
//            .signWith(SignatureAlgorithm.HS512, jwtSecret)
//            .compact();
//    }
//
//    public String getUsernameFromJWT(String token) {
//        return Jwts.parser()
//            .setSigningKey(jwtSecret)
//            .parseClaimsJws(token)
//            .getBody()
//            .getSubject();
//    }
//}
