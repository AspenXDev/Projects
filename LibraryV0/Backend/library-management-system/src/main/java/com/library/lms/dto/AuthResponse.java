// PATH: src/main/java/com/library/lms/dto/AuthResponse.java
package com.library.lms.dto;

import com.library.lms.model.Role;
import com.library.lms.model.User;

public class AuthResponse {

    private String username;
    private String role;
    private String token;
    private String message; // for error messages

    public AuthResponse() {}

    // Success constructor
    public AuthResponse(User user, String token) {
        if (user != null) {
            this.username = user.getUsername();
            Role roleObj = user.getRole();
            this.role = roleObj != null ? roleObj.getRoleName() : null;
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

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
