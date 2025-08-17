# Work Breakdown Structure (WBS) — Upgraded MVP

## 1. MVP Features (Aligned with Program Requirements)

### 1.1 Authentication & Authorization

- **User registration (Member, Librarian)**

  - Error Handling: Reject duplicate username/email, invalid role

- **User login with email/password**

  - Error Handling: Invalid credentials, inactive account

- JWT issuance and verification
- Password reset flow
- Role-based access control (RBAC)
- Backend protection with `@PreAuthorize`
- Client-side protection with `PrivateRoute`
- Input validation for all forms

---

### 1.2 Book Management

- **Add new books (Librarian)**

  - Error Handling: Prevent duplicates, validate mandatory fields

- **Update book details (Librarian)**

  - Error Handling: Reject updates on non-existent books

- **Delete/archive books (Librarian)**

  - Error Handling: Prevent deletion if book is loaned out

- Search & filter books by: title, author, ISBN, year, genre, availability, location, status
- View book details (All users)
- View metadata incl. `created_at`, `updated_at` (Librarians only)

---

### 1.3 Loan Management

#### Borrow Book (Member)

- **Eligibility checks:**

  - Membership active & valid
  - No overdue loans
  - Unpaid fines ≤ \$10
  - Fewer than 3 active loans
  - Reservation check:

    - If book has active reservations → only first in queue can borrow
    - If not first in queue → reject
    - If first in queue → borrow, then consume/remove reservation

  - Error Handling: Reject on violation with clear message

- **Loan creation:**

  - Insert row in `loans` (14-day due date, status = Active)
  - Decrement `books.available_copies`
  - Update `books.status = Borrowed` if none left
  - Error Handling: Rollback on failure or negative copies

- **Result:** Return loan details + updated availability

#### Return Book (Member)

- Mark loan as returned (`return_date = today`)
- Increment `books.available_copies`
- Set `books.status = Available` if copies > 0
- Compute fines (0.50/day, max \$20)
- Error Handling: Reject if already returned; rollback on failure

#### Renew Loan (Member)

- Eligibility: not overdue, not reserved by others, renew count < 2
- Extend due date by 14 days, increment renew count
- Error Handling: Reject on violation; rollback on failure

#### View Loans (Member)

- Show active loans with book details, due dates, renewal counts, fines, overdue flags
- Error Handling: Return empty list if none

#### Availability Updates (System)

- On borrow: decrement copies
- On return: increment copies
- Update `books.status` accordingly
- Error Handling: Rollback if inconsistent

---

### 1.4 Reservation Management

#### Place Reservation (Member)

- Add member to reservations queue (FIFO)
- Error Handling: Prevent duplicate reservation; reject if member already has a loan of same book (app logic, not-DB level enforced in MVP)

#### Process Reservation (System)

- When a copy becomes available:

  - Mark first in queue as **On Hold** (3-day window)
  - Notify member (dashboard/email)

- If 3 days expire unused:

  - Cancel hold
  - Assign next in queue

#### Cancel Reservation (Member)

- Remove reservation from queue
- Error Handling: Reject if already cancelled/collected

#### Borrow from Reservation

- If member is first in queue and within 3-day hold → proceed with Borrow Book flow, consuming the reservation
- Error Handling: Reject if not first in queue or hold expired

---

### 1.5 Notifications & Status Updates

- Display loan status updates to users
- Notify users of overdue books (MVP = manual flagging)

---

### 1.6 Core UI & Navigation

- Login/Register pages
- Book list (search & filter)
- Book detail page
- Loan history (per user)
- Admin dashboard (books & loans overview)

---

## 2. Full Version (Post-MVP Enhancements)

### 2.1 Enhanced Notifications

- Automated overdue email alerts
- Loan due reminders

### 2.2 Fine Management

- Auto fine calculation
- Fine payment tracking
  - No partial payments accepted

### 2.3 Reservation System

- Advanced handling of multiple queues
- Rich notification options (SMS/email)

### 2.4 Advanced Search & Recommendations

- Search by tags, publication year, extended metadata
- Recommendations based on borrowing history

### 2.5 Reporting & Analytics

- Borrowing trends
- Member activity reports
- Usage statistics

### 2.6 UI Enhancements

- Responsive design improvements
- Dark mode
- Accessibility compliance

---
