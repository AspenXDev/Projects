package com.library.lms.dto;

import com.library.lms.model.Role;
import com.library.lms.model.User;

public class AuthResponse {

    private String username;
    private String fullName;
    private String role;
    private String token;
    private String message; // error message

    public AuthResponse() {}

    // Success constructor: user object + derived fullName + token
    public AuthResponse(User user, String fullName, String token) {
        if (user != null) {
            this.username = user.getUsername();
            this.fullName = fullName;
            Role roleObj = user.getRole();
            this.role = (roleObj != null ? roleObj.getRoleName() : null);
        }
        this.token = token;
    }

    // Error/message constructor
    public AuthResponse(String message) {
        this.message = message;
    }

    // --- Getters & Setters ---
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
