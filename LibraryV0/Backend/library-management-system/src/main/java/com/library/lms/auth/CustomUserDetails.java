
/**
 * Represents the custom implementation of user details for the Library
 * Management System's authentication and authorization processes.
 *
 * <p>This class is designed to work with the security framework by encapsulating
 * additional user attributes and behaviors beyond the default user details.</p>
 *
 * @author 
 *   Victor Chan
 * @version 1.0.0
 *
 * // Inline Note: Ensure that this class implements or extends the appropriate
 * // security interface/class (e.g., UserDetails) to integrate with the security backend.
 *
 * // TODO: Review and extend with additional custom user attributes (e.g., roles,
 * // permissions, account statuses) as required by the application.
 */
package com.library.lms.auth;

import com.library.lms.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {

    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Spring Security expects roles prefixed with "ROLE_"
        String roleName = user.getRole().getRoleName();
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_" + roleName));
    }

    @Override
    public String getPassword() {
        return user.getPasswordHash();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.getIsActive();
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.getIsActive();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.getIsActive();
    }

    @Override
    public boolean isEnabled() {
        return user.getIsActive();
    }
}
