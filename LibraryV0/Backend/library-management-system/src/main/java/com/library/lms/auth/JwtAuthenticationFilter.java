/**
 * Filters incoming HTTP requests to apply JWT-based authentication.
 * <p>
 * This filter is executed once per request and functions as follows:
 * <ul>
 *   <li>
 *     Checks the requested URL; if the URL begins with "/auth/", the filter bypasses JWT processing
 *     (e.g., for login or registration endpoints).
 *   </li>
 *   <li>
 *     Extracts the JWT token from the "Authorization" header in the format "Bearer {token}".
 *   </li>
 *   <li>
 *     Uses the JwtTokenProvider to obtain the username from the token.
 *   </li>
 *   <li>
 *     Loads user details via the injected UserDetailsService and retrieves the "role" claim from the token.
 *   </li>
 *   <li>
 *     Normalizes the extracted role (such as converting "Librarians" to "ROLE_LIBRARIAN") by stripping a 
 *     trailing 's' if present and converting to uppercase.
 *   </li>
 *   <li>
 *     If a valid role is obtained, an authentication token (UsernamePasswordAuthenticationToken) is created,
 *     including the mapped authority, and set into the Spring Security Context.
 *   </li>
 *   <li>
 *     In case of any failures (no token found, invalid token, or exception during JWT processing), the error is logged
 *     and the filter chain continues.
 *   </li>
 * </ul>
 * </p>
 *
 * @see org.springframework.web.filter.OncePerRequestFilter
 * @see jakarta.servlet.http.HttpServletRequest
 * @see jakarta.servlet.http.HttpServletResponse
 * @see org.springframework.security.authentication.UsernamePasswordAuthenticationToken
 * @see org.springframework.security.core.context.SecurityContextHolder
 * @since 1.0
 */
// PATH: src/main/java/com/library/lms/auth/JwtAuthenticationFilter.java
package com.library.lms.auth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

/**
 * JWT filter that authenticates requests with JWT tokens.
 * Skips authentication for /auth/** endpoints (login, register, reset password, etc.).
 * Normalizes whatever role claim is inside the token (like "Librarians" or "Members") into Spring’s expected "ROLE_LIBRARIAN" / "ROLE_MEMBER" authority.
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider, UserDetailsService userDetailsService) {
        this.jwtTokenProvider = jwtTokenProvider;
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
        String token = null;
        String username = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            username = jwtTokenProvider.getUsernameFromToken(token);
            logger.debug("Extracted token for user: {}", username);
        } else {
            logger.debug("No Bearer token found in Authorization header");
        }

        // Validate token and set authentication context
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            try {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                // Extract role from token and normalize
                String roleClaim = (String) io.jsonwebtoken.Jwts.parser()
                        .setSigningKey("mySuperSecretKey1234567890mySuperSecretKey1234567890mySuperSecretKey1234567890mySuperSecretKey1234567890mySuperSecretKey1234567890")
                        .parseClaimsJws(token)
                        .getBody()
                        .get("role");

                String mappedRole = mapRole(roleClaim);

                if (mappedRole != null) {
                    logger.debug("Mapped role '{}' to authority '{}'", roleClaim, mappedRole);

                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    Collections.singletonList(new SimpleGrantedAuthority(mappedRole))
                            );

                    SecurityContextHolder.getContext().setAuthentication(authToken);
                } else {
                    logger.debug("No valid role claim found in token");
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

    /**
     * Normalizes DB role names like "Librarians" or "Members" into Spring Security format.
     * "Librarians" -> "ROLE_LIBRARIAN"
     */
    private String mapRole(String roleClaim) {
        if (roleClaim == null) return null;
        String normalized = roleClaim.trim();

        // strip plural s
        if (normalized.endsWith("s")) {
            normalized = normalized.substring(0, normalized.length() - 1);
        }

        return "ROLE_" + normalized.toUpperCase();
    }
}
