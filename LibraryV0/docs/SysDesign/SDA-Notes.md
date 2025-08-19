# SDA-Notes

## Database Design

- **Roles & Users:**

  - `users.role_id` links to `roles.role_id` (Members, Librarians).
  - `users.is_active` used to disable accounts without deleting.

- **Entities & Ownership:**

  - `members.user_id` → `users.user_id` ensures ownership of loans, reservations, fines.
  - `librarians.user_id` → `users.user_id` provides full management privileges.

- **ENUM Fields for Data Integrity:**

  - `members.membership_status` (`Active`/`Expired`)
  - `books.status` (`Available`, `Borrowed`, `Reserved`)
  - `loans.status` (`Active`, `Returned`)
  - `reservations.status` (`Waiting`, `On Hold`, `Collected`, `Cancelled`)

## Backend Design

- **Entities & DTOs:**

  - DTOs prevent exposing lazy-loaded relationships (`Member`, `Librarian`, `Book`) directly.
  - Only required fields (e.g., book location: `location_section`, `location_shelf`, `location_row`) returned to Members.

- **CustomUserDetailsService:**

  - Maps `users.password_hash` to Spring Security password.
  - Maps `users.role_id` to `role_name`.
  - Checks `is_active` for account status.

- **JwtAuthenticationFilter:**

  - Validates token.
  - Loads user and roles from DB.
  - Sets Spring Security context.

- **RBAC Examples:**

  ```java
  // Member only own loans
  @PreAuthorize("hasRole('Members')")

  // Librarian full CRUD on books
  @PreAuthorize("hasRole('Librarians')")
  ```

- **Stateless JWT:**

  - No server-side session.
  - Reduces server load, simplifies horizontal scaling.

## Security & Passwords

- **Password Hashing:** `BCryptPasswordEncoder` compatible with `users.password_hash`.
- **Token Expiration:** Configurable via `application.properties` (e.g., 1 hour).
- **Token Refresh:** `/auth/refresh` endpoint to issue new token before expiration.

## Example Endpoint Mapping

| Endpoint   | Role/Access                       | Notes                                      |
| ---------- | --------------------------------- | ------------------------------------------ |
| `/books`   | Members (view), Librarians (CRUD) | Members can see physical location.         |
| `/loans`   | Members (own), Librarians (all)   | Members can borrow/return/renew own loans. |
| `/fines`   | Members (own), Librarians (full)  | Librarians can adjust fines.               |
| `/auth/**` | Public                            | Login, register, reset password.           |

---

Absolutely! I can provide a **complete endpoint mapping table** aligned strictly with your schema, roles, and ownership rules. Here's a polished, full version for your markdown:

---

## Full Endpoint Mapping

| Entity / Action  | Endpoint                                | Method | Role / Access             | Notes / Ownership Enforcement        |
| ---------------- | --------------------------------------- | ------ | ------------------------- | ------------------------------------ |
| **Auth**         | `/auth/register`                        | POST   | Public                    | Register new Member or Librarian     |
|                  | `/auth/login`                           | POST   | Public                    | Obtain JWT token                     |
|                  | `/auth/logout`                          | POST   | Authenticated             | Optional, for stateless JWT          |
|                  | `/auth/refresh`                         | POST   | Authenticated             | Issue new token                      |
|                  | `/auth/reset-password`                  | POST   | Public                    | Request reset email                  |
| **Members**      | `/members/{member_id}`                  | GET    | Members (own), Librarians | View member profile                  |
|                  | `/members/{member_id}`                  | PUT    | Members (own), Librarians | Update profile info                  |
|                  | `/members/{member_id}/loans`            | GET    | Members (own), Librarians | List loans                           |
|                  | `/members/{member_id}/reservations`     | GET    | Members (own), Librarians | List reservations                    |
|                  | `/members/{member_id}/fines`            | GET    | Members (own), Librarians | List fines                           |
| **Books**        | `/books`                                | GET    | Public                    | Search / list books                  |
|                  | `/books/{book_id}`                      | GET    | Public                    | View book details including location |
|                  | `/books`                                | POST   | Librarians                | Create book + set location           |
|                  | `/books/{book_id}`                      | PUT    | Librarians                | Update book info & location          |
|                  | `/books/{book_id}`                      | DELETE | Librarians                | Remove book                          |
| **Loans**        | `/loans`                                | POST   | Members (own), Librarians | Borrow book; member_id auto-set      |
|                  | `/loans/{loan_id}`                      | GET    | Members (own), Librarians | View loan                            |
|                  | `/loans?member_id={member_id}`          | GET    | Members (own), Librarians | List member loans                    |
|                  | `/loans?status=overdue`                 | GET    | Librarians                | List all overdue loans               |
|                  | `/loans/{loan_id}/return`               | PUT    | Members (own), Librarians | Mark as returned                     |
|                  | `/loans/{loan_id}/renew`                | PUT    | Members (own), Librarians | Renew loan                           |
|                  | `/loans/{loan_id}`                      | DELETE | Librarians                | Cancel loan                          |
| **Reservations** | `/reservations`                         | POST   | Members (own), Librarians | Create reservation                   |
|                  | `/reservations/{reservation_id}`        | GET    | Members (own), Librarians | View reservation                     |
|                  | `/reservations?member_id={member_id}`   | GET    | Members (own), Librarians | List reservations                    |
|                  | `/reservations/{reservation_id}/cancel` | PUT    | Members (own), Librarians | Cancel reservation                   |
| **Fines**        | `/fines/{fine_id}`                      | GET    | Members (own), Librarians | View fine                            |
|                  | `/fines?member_id={member_id}`          | GET    | Members (own), Librarians | List member fines                    |
|                  | `/fines/{fine_id}`                      | PUT    | Librarians                | Adjust / mark paid                   |
|                  | `/fines/{fine_id}`                      | DELETE | Librarians                | Remove fine if needed                |

---
