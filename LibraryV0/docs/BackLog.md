# Backlog — Library Management System

## Completed (Milestone 1)

1. **Database Schema Finalization**
   - Tables: `users`, `members`, `librarians`, `books`, `loans`, `reservations`, `fines`.
   - RBAC support via `roles` table.
   - Timestamp tracking (`created_at`, `updated_at`) for all mutable tables. (Jackson JSR for Timestamp in SQL<->ISO-8601 in Java)
   - Reservation queue (FIFO) and 3-day hold logic accounted for in design.
2. **Project Setup**
   - Scaffolded Spring Boot project with RESTful APIs.
     - Entity, enums, converters, repositories done. (Pending Services, Controllers and Auth for MVP)
   - Set up React project with React Router for navigation.
     - Placemarker files and templates added
   - Set up MySQL database completed.

---

## For MVP (Aligned to Updated Features)

### Milestone 2: Access & Auth

2. **Access Control Definition**

   - Map endpoints to roles (Member vs Librarian).
   - Frontend route guarding: `PrivateRoute`.
   - Backend authorization: `@PreAuthorize`.
   - Document in `EndPoints.md`.

3. **Authentication & Role Verification**
   - JWT-based authentication with role claims.
   - Secure storage in client context/state.
   - Implement login, registration, password reset, and RBAC checks.

---

### Milestone 3: Book & Loan Management

4. **Book Management (Librarian)**

   - CRUD operations for books.
   - Add search and filter functionality for books (e.g., by title, author, category).
   - Status management: `Available`, `Borrowed`, `Reserved`.
   - Category handling: ENUM support.

5. **Loan Management (Member)**
   - Borrow book with eligibility checks:
     - Active membership
     - No overdue loans
     - Unpaid fines ≤ $10
     - <3 active loans
   - Return book: update availability, compute fines.
   - Renew loan: eligibility checks, max 2 renewals.
   - View loans dashboard with fines/overdue flags.
