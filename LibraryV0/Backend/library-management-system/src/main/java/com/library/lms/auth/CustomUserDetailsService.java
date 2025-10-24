/**
 * CustomUserDetailsService handles user-specific data retrieval and authentication details.
 * <p>
 * This service is responsible for loading user details from the data source, which includes
 * user roles and permissions. It integrates with Spring Security to facilitate custom
 * authentication and authorization logic.
 * </p>
 *
 * <p>
 * Note: Inline comments within the service methods further explain the authentication
 * process and specific handling of user data (e.g., loading user by username).
 * </p>
 *
 * @author 
 *   Victor Chan
 * @version 1.0.0
 * @see org.springframework.security.core.userdetails.UserDetailsService
 */
package com.library.lms.auth;

import com.library.lms.model.User;
import com.library.lms.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        return new CustomUserDetails(user);
    }
}
