## Database

    Used ENUM for `members.membership_status`, `books.status`, `reservations.status`, `loans.status` to reduce errors, and improve maintainability.

## Backend

    Above fields coded as ENUM in respective entity classes.
    DTOs used to prevent exposing lazy-loaded relationships or sensitive info directly.

## Authentication (JWT auth, RBAC, and endpoint protection)

### CustomUserDetailsService

    Uses `password_hash` column as `password`.
    Maps `role_id` to `Members` or `Librarians`.
    Uses `is_active` column to disable the account if inactive.

### JwtUtil

    generateToken() takes UserDetails object.
    validateToken() same as above (compatible with Spring Security filters).
    Parsed to `JwtAuthenticationFilter`.

### JwtAuthenticationFilter

    - Uses `UserDetails` from `CustomUserDetailsService` to ensure alignment with `users` table.
    - `jwtUtil.validateToken(jwt, userDetails)` takes `UserDetails` object.
    - Roles from the db (Members, Librarians) are mapped and applied to the UserDetails.
    - Skips authentication if token is missing or invalid, preventing exceptions.

`SecurityConfig.java` integrates JWT filter, `CustomUserDetailsService`, role-based access control (RBAC), and endpoint protection.

1. - `JwtAuthenticationFilter` runs before Spring’s `UsernamePasswordAuthenticationFilter`.
   - Extracts token, validates it, and populates `SecurityContext`.

2. **RBAC / Role Enforcement**

   - Controller or endpoint-level access via:
     - `.hasRole("Members")` or `.hasRole("Librarians")` in `SecurityFilterChain`
     - Or via `@PreAuthorize("hasRole('Members')")` annotations on controller methods.

3. **Stateless JWT**

   - Sessions disabled: `SessionCreationPolicy.STATELESS`.

4. **Password Hashing**

   - `BCryptPasswordEncoder` because your db keeps only `password_hash`.

5. **Example Endpoint Mapping**
   - `/books/available` → Members only
   - `/books/**` → Librarians only
   - `/auth/**` → public (e.g., login, registration)

(Member-only endpoints and Librarian-only endpoints are protected using SecurityConfig.java)

---

## Frontend
