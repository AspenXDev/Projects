---
# EndPoints
Start with http://localhost:8081/
## 1. Legend

| Term              | Meaning                                                                                      |
| ----------------- | -------------------------------------------------------------------------------------------- |
| **Members (own)** | Member role can only access their own records (ownership enforced via `member.user.userId`). |
| **Librarians**    | Full access to all relevant entities.                                                        |
| **Authenticated** | Any logged-in user with valid JWT.                                                           |
| **Public**        | Any public user using app on-premise.                                                        |

**Frontend Guarding:**

- `PrivateRoute` components and conditional rendering to restrict access based on role.
- Members can see their own profile pages and dashboards.
- Librarians can access all management/admin panels.

**Backend RBAC:**

- `@PreAuthorize("hasRole('Librarians')")` for Librarian-only endpoints.
- `@PreAuthorize("hasRole('Members') and @authService.isOwner(#memberId)")` for member-own endpoints.

---

## 2. Auth API

| Method | Endpoint               | Role/Access   | Description                                 |
| ------ | ---------------------- | ------------- | ------------------------------------------- |
| POST   | `/auth/register`       | Public        | Register new user (Member or Librarian)     |
| POST   | `/auth/login`          | Public        | Login and obtain JWT                        |
| POST   | `/auth/logout`         | Authenticated | Invalidate JWT (optional for stateless JWT) |
| POST   | `/auth/refresh`        | Authenticated | Refresh JWT token                           |
| POST   | `/auth/reset-password` | Public        | Request password reset link/email           |

**Notes:**

- JWT payload includes `role_name` for RBAC enforcement.

---

## 3. Member API

| Method | Endpoint                            | Role/Access               | Description                  |
| ------ | ----------------------------------- | ------------------------- | ---------------------------- |
| GET    | `/members/{member_id}`              | Members (own), Librarians | Get member profile           |
| PUT    | `/members/{member_id}`              | Members (own), Librarians | Update member info           |
| GET    | `/members/{member_id}/loans`        | Members (own), Librarians | List loans for member        |
| GET    | `/members/{member_id}/reservations` | Members (own), Librarians | List reservations for member |
| GET    | `/members/{member_id}/fines`        | Members (own), Librarians | List fines for member        |

**Notes:**

- Ownership checks prevent members from accessing other members’ data.
- Librarians bypass ownership checks.

---

## 4. Book API

| Method | Endpoint           | Role/Access | Description                                                    |
| ------ | ------------------ | ----------- | -------------------------------------------------------------- |
| GET    | `/books`           | Public      | List/search books (`?status=Available`, `?title=...`)          |
| GET    | `/books/{book_id}` | Public      | View book details including `location_section`, `shelf`, `row` |
| POST   | `/books`           | Librarians  | Add new book including physical location                       |
| PUT    | `/books/{book_id}` | Librarians  | Update book details & location                                 |
| DELETE | `/books/{book_id}` | Librarians  | Remove a book                                                  |

**Notes:**

- Members can view all book info including physical placement.
- Librarians can CRUD books and manage physical location.

---

## 5. Loan Management API

| Method | Endpoint                       | Role/Access               | Description                                                                                      |
| ------ | ------------------------------ | ------------------------- | ------------------------------------------------------------------------------------------------ |
| POST   | `/loans`                       | Members, Librarians       | Borrow books (Members link automatically to their own ID). Librarians can create for any member. |
| GET    | `/loans/{loan_id}`             | Members (own), Librarians | Get loan details                                                                                 |
| GET    | `/loans?member_id={member_id}` | Members (own), Librarians | List loans for a member (members only their own)                                                 |
| GET    | `/loans?status=overdue`        | Librarians                | List all overdue loans                                                                           |
| PUT    | `/loans/{loan_id}/return`      | Members (own), Librarians | Mark loan returned                                                                               |
| PUT    | `/loans/{loan_id}/renew`       | Members (own), Librarians | Renew loan                                                                                       |
| DELETE | `/loans/{loan_id}`             | Librarians                | Cancel a loan                                                                                    |

**@PreAuthorize example:**

```java
// Members can only operate on their own loans
@PreAuthorize("hasRole('Members') and @authService.isOwner(#loanId)")
```

---

## 6. Reservation API

| Method | Endpoint                                | Role/Access               | Description                                      |
| ------ | --------------------------------------- | ------------------------- | ------------------------------------------------ |
| POST   | `/reservations`                         | Members, Librarians       | Create reservation (Members only for themselves) |
| GET    | `/reservations/{reservation_id}`        | Members (own), Librarians | View reservation                                 |
| GET    | `/reservations?member_id={member_id}`   | Members (own), Librarians | List reservations for a member                   |
| PUT    | `/reservations/{reservation_id}/cancel` | Members (own), Librarians | Cancel reservation                               |

---

## 7. Fines API

| Method | Endpoint                       | Role/Access               | Description           |
| ------ | ------------------------------ | ------------------------- | --------------------- |
| GET    | `/fines/{fine_id}`             | Members (own), Librarians | Get fine details      |
| GET    | `/fines?member_id={member_id}` | Members (own), Librarians | List fines for member |

---

## 8. Role Enforcement Matrix (Visual)

| Entity       | Members (own)                | Librarians              |
| ------------ | ---------------------------- | ----------------------- |
| Profile      | view/edit own                | view/edit any           |
| Books        | view                         | CRUD including location |
| Loans        | view/create/return/renew own | full CRUD               |
| Reservations | view/create/cancel own       | full CRUD               |
| Fines        | view own                     | view all                |
| Auth         | login/register/reset own     | manage if needed        |

---

## 9. Notes for Frontend & Backend

**Frontend**

- Use `PrivateRoute` to hide unauthorized pages.
- Members see only their own dashboards.
- Librarians see global management dashboards.

**Backend**

- Use `@PreAuthorize` for role checks.
- All endpoints require JWT except `/auth/**`.
- JWT payload contains `username` and `role_name`.

---

## 10. Role Enforcement Matrix

| Entity / Action | Members (own)                      | Librarians               |
| --------------- | ---------------------------------- | ------------------------ |
| Profile         | view / edit own                    | view / edit any          |
| Books           | view                               | create / update / delete |
| Loans           | view / borrow / return / renew own | full CRUD                |
| Reservations    | view / create / cancel own         | full CRUD                |
| Fines           | view own                           | full CRUD                |
| Auth            | login / register / reset own       | full CRUD                |

---
