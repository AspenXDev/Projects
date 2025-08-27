// =========================
// UserRole.java (lookup table entity)
// =========================
package com.library.lms.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "roles")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_name", nullable = false, unique = true, length = 50)
    private String roleName;

    public UserRole() {}

    public UserRole(String roleName) {
        this.roleName = roleName;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRoleName() { return roleName; }
    public void setRoleName(String roleName) { this.roleName = roleName; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserRole)) return false;
        UserRole that = (UserRole) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() { return 31; }

    @Override
    public String toString() {
        return "UserRole{id=" + id + ", roleName='" + roleName + "'}";
    }
}
