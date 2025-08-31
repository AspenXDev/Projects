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

### Completed Milestone 2: Access & Auth

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

Check
Perfect timing — we can definitely restart on your **capstone Library Management System (LMS)** and align on where we are vs. what’s left. Thanks for pasting your **BRD, SRS, schema, and filetrees** — that’s gold. Let me summarize where things stand and what’s left, based on our previous work and your rules (no Lombok, minimal DTOs, coherent MVP).

---

### As of 20250831

#### Backend

**Project setup** (Spring Boot, JWT, BCrypt, package structure preserved).
**Authentication & Authorization**:

- `AuthController`, `AuthRequest`, `AuthResponse`.
- JWT token generation, validation, filters.
- `SecurityConfig` with `permitAll` on GET `/books`, all other CRUD requiring `Librarian` role.
- Role-based access (Members vs. Librarians).
- **Entities & DB schema alignment**:
  - Tables: `users`, `members`, `librarians`, `books`, `loans`, `reservations`, `fines`, `roles`.
  - Enum converters for status fields (`BookStatus`, `ReservationStatus`, `UserRole`).
- **Repositories**: All major repos created (`UserRepository`, `BookRepository`, `LoanRepository`, etc.).
- **Services**:
  - Aligned CRUD for Users, Members, Librarians, Books, Reservations, Loans, Fines.
  - ServiceImpl classes created in same pattern, error handling included.
- **Controllers**: Stubs for each domain (`BookController`, `LoanController`, etc.) present.
- **Error handling**: GlobalExceptionHandler + custom exceptions.

#### Frontend

- **Project setup**: React, `react-router-dom`, Axios.
- **Auth**:
  - `LoginForm`, `LoginPage`, `AuthContext`, `PrivateRoute`.
  - Role-based guarding for routes (Member vs. Librarian dashboards).
- **UI foundation**:
  - Simple color scheme, clean look.
  - Navbar, logout button.
- **Book catalog**:

  - `BooksList`, `BookCard` (member view).
  - `BooksPublicList`, `PublicBookCard` (public view).

- **Reservations**:

  - Components (`ReservationsList`, `ReservationCard`) exist but not fully wired.

- **Dashboard stubs**:
  - MemberDashboard, LibrarianDashboard exist.

---

## Gap to MVP

### Backend

1. **Membership Management**
   - Block borrowing if `membership_status = Expired` or `membership_valid_until < today`.
   - Add librarian ability to extend membership validity.
   - Return membership validity in `MemberDTO` for frontend display.
2. **Borrowing Flow**
   - Implement eligibility checks in `LoanServiceImpl` (membership, overdue, fines, active loans < 3).
   - Loan creation: update book availability, set loan/due dates.
   - Return loan: set `return_date`, calculate fines, increment `available_copies`.
   - Renew loan: enforce max 2 renewals, not overdue, not reserved.
3. **Fines**
   - Fine calculation logic (0.50/day, max \$20).
   - Fines persisted on return if overdue.
   - Fines API (`FineController`) to fetch unpaid fines per member.
4. **Reservations**

   - Place reservation with eligibility checks.
   - FIFO queue + 3-day hold.
   - Auto-expiry after hold period.
   - On book return, move next reservation → “On Hold”.

5. **Overdue Notifications**

   - Basic endpoint to fetch overdue loans for logged-in member.
   - On-screen rendering (no email/SMS).

6. **Testing**
   - Expand `TestGetBooks` into minimal but functional test coverage for borrow/return/renew.

---

### Frontend

1. **Membership status display**

   - Show `Active` vs. `Expired` + validity date on MemberDashboard.
   - Disable borrow buttons if expired.

2. **Borrowing UI**

   - Borrow button in `BooksList` for eligible members.
   - Return button in `Loans.jsx`.
   - Renew button with eligibility checks.

3. **Reservations UI**

   - Reserve button for books with `available_copies = 0`.
   - Show user’s reservations with status (`Waiting`, `On Hold`, etc.).

4. **Fines UI**

   - `Fines.jsx` → show unpaid fines + totals.
   - Display fine warnings when borrowing blocked.

5. **Overdue notifications**

   - Dashboard section for “Overdue books” with red highlight.

6. **Final polish**
   - Confirm navigation flows (Member: dashboard → books → loans/reservations/fines).
   - Librarian: dashboard → members → extend membership, manage catalog.

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
