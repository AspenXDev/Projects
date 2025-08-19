# Architecture-Notes

## Backend

- **Framework & Port:** Spring Boot, running on port `8080`.
- **Database:** MySQL `Library_DB`. Entities map directly to tables: `users`, `members`, `librarians`, `books`, `loans`, `reservations`, `fines`.
- **Security & Auth:**

  - Stateless JWT-based authentication (`HS256`).
  - All endpoints require valid JWT except `/auth/**`.
  - JWT payload contains:

    ```json
    {
      "userId": 123,
      "username": "alice",
      "role": "Members",
      "exp": 1690000000
    }
    ```

  - Roles dynamically fetched from `roles` table for RBAC enforcement.

- **RBAC Enforcement:**

  - Backend: `@PreAuthorize("hasRole('Librarians')")` for Librarian-only endpoints.
  - Member-specific endpoints enforce ownership:

    ```java
    @PreAuthorize("hasRole('Members')
    ```

  - Security filter chain integrated with `JwtAuthenticationFilter` that validates token, sets Spring Security context, and loads roles.

- **JWT Flow:**

  1. Client sends JWT in `Authorization: Bearer <token>`.
  2. `JwtAuthenticationFilter` validates token.
  3. Spring Security context populated with user details and roles.
  4. `@PreAuthorize` annotations enforce access based on role and ownership.

## Frontend

- **Framework & Port:** ReactJS (Vite), default port `5173`.
- **PrivateRoute & Conditional Rendering:**

  - Members can only see their own profiles, loans, reservations, and fines.
  - Librarians access all management dashboards and CRUD features.
  - Components verify role before rendering; otherwise, redirect to login or 403 page.

---
