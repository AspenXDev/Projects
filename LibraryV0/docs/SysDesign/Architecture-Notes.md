# Architecture

**Backend**

- Runs on port 8080
- Spring Boot properties:
  - server.port=8080
  - spring.datasource.url=jdbc:mysql://localhost:3306/librarydb

**Frontend**

- React app, runs on port 5173 (default Vite)
- Communicates with backend via REST APIs

## RBAC (Role-Based Access Control)

- Each user has a `roleId` linked to `roles` table
- Roles are not hardcoded, making it easy to add/change roles in future versions

## Authentication

- Uses **HS256 symmetric JWT tokens**
- Flow:
  1. `JwtUtil` validates signature & expiration
  2. `JwtAuthenticationFilter` sets Spring Security context if token is valid
  3. Rejects request if token is invalid
  4. Extracts username from token for authorization
- Secret key (jwt.secret) is long and difficult to guess:
  `jwt.secret=ThisHereIsMySuperLongSecretKeyForJWTsUsedInMyCapstoneProject!999`
