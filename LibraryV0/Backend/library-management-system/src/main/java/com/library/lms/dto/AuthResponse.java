package com.library.lms.dto;

import com.library.lms.model.User;
import com.library.lms.model.Role; // <-- ensure this is imported

public class AuthResponse {
    private String username;
    private String role;
    private String token;

    public AuthResponse() {}

    // Existing constructor
    public AuthResponse(String username, String role, String token) {
        this.username = username;
        this.role = role;
        this.token = token;
    }

    // NEW: constructor to wrap User + token
    public AuthResponse(User user, String token) {
        if (user != null) {
            this.username = user.getUsername();
            Role roleObj = user.getRole();  // assuming getRole() returns Role
            this.role = roleObj != null ? roleObj.toString() : null; // use toString() if .name() doesn't exist
        }
        this.token = token;
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
}
