package com.library.lms.auth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * JWT filter that authenticates requests with JWT tokens.
 * Skips authentication for /auth/** endpoints (login, register, reset password, etc.).
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String path = request.getServletPath();
        logger.debug("JwtAuthenticationFilter executing for path: {}", path);

        // Skip JWT authentication for /auth/** endpoints
        if (path.startsWith("/auth/")) {
            logger.debug("Skipping JWT filter for public path: {}", path);
            filterChain.doFilter(request, response);
            return;
        }

        // Check Authorization header
        String authHeader = request.getHeader("Authorization");
        String username = null;
        String token = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            username = jwtUtil.getUsernameFromToken(token);
            logger.debug("Extracted token for user: {}", username);
        } else {
            logger.debug("No Bearer token found in Authorization header");
        }

        // Validate token and set authentication context
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            logger.debug("No authentication in context, loading user: {}", username);

            try {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                if (jwtUtil.validateToken(token, userDetails)) {
                    logger.debug("JWT token validated successfully for user: {}", username);

                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities()
                            );
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                } else {
                    logger.debug("JWT token validation failed for user: {}", username);
                }
            } catch (Exception e) {
                logger.debug("Exception while validating JWT for user {}: {}", username, e.getMessage());
            }
        } else {
            if (username == null) {
                logger.debug("No username extracted from token");
            } else {
                logger.debug("Authentication already exists in context for user: {}", username);
            }
        }

        // Continue the filter chain
        filterChain.doFilter(request, response);
    }
}