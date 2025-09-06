# Software Requirements Specification (SRS) — Library Management System (MVP)

## 1. Introduction

### 1.1 Purpose

Define the functional and non-functional requirements for the LMS MVP supporting two roles: **Member** and **Librarian**.

### 1.2 Scope

Web application with Spring Boot backend, React frontend, and MySQL database, delivered per the trainer's milestones. No public deployment required.

### 1.3 Definitions

- **Member**: Library customer.
- **Librarian**: Catalog, circulation, and membership administration.
- **MVP**: Minimum Viable Product.

### 1.4 References

- BRD.md
- System Design Document (ERD, API, UI)

---

## 2. System Overview

- **Frontend**: React, `react-router-dom`, Axios; `PrivateRoute` for route guarding by role.
- **Backend**: Spring Boot; Spring Security with JWT & BCrypt; RESTful APIs with validation and `@PreAuthorize`.
- **Database**: MySQL with relationships, and constraints. (Indexes for future version).

Security is enforced **client-side** (route guard) and **server-side** (JWT validation, role checks, input validation).

---

## 3. Functional Requirements

### 3.1 Authentication & Authorization

- **FR-A1**: Users can register as a Member.
- **FR-A2**: Users can log in with username and password.
- **FR-A3**: On successful login, the Backend issues a JWT.
- **FR-A4**: The Client stores the JWT securely in `localStorage`.
- **FR-A5**: An Axios interceptor automatically attaches the JWT to all protected API requests.
- **FR-A6**: The Client restricts navigation using `PrivateRoute`, checking for valid JWT and user role.
- **FR-A7**: The Backend enforces server-side role-based access control (RBAC) using `@PreAuthorize` annotations.
- **FR-A8**: Public endpoints (e.g., `GET /books`) are explicitly permitted using Spring Security’s `permitAll()`.
- **FR-A9**: User passwords are stored using the BCrypt hashing algorithm.
- **FR-A10**: JWTs are signed with a secret key of at least 256 bits to ensure cryptographic security.

#### 3.1.1 Auth Flow Chart

User Client (React) Backend (Spring Boot) Database (MySQL)
| | | |
|-- Register ----------->| | |
| |-- POST /auth/register ----------->|-- INSERT user ----------->|
| | |<- success ----------------|
| |<- Registration successful --------| |
| | | |
|-- Login -------------->| | |
| |-- POST /auth/login -------------->|-- SELECT user ----------->|
| | |-- BCrypt password check-->|
| | |<- JWT (signed, 256-bit)---|
| |<- JWT received -------------------| |
| |-- Store JWT in localStorage ------| |
| | | |
|-- Access protected --->| | |
| route (/dashboard) | | |
| |-- Axios attaches JWT ------------>|-- Verify JWT signature--->|
| | |-- @PreAuthorize check ----|
| | |-- hasRole('LIBRARIAN') -->|
| | |<- Authorized -------------|
| | | |
| | <- Protected data ----------------| |
|-- Access public ------>| | |
| route (/books) |-- GET /books ----------====------>|-- permitAll() ----------->|
| | |<- Books list -------------|
| |<- Books shown --------------------| |

### 3.2 Membership Management (Validity)

- **FR-M1**: On Member registration, system sets `members.membership_valid_until = members.registration_date + 1 year`.
- **FR-M2**: System blocks borrowing/renewal if membership is expired (today > `members.membership_valid_until`) or account inactive.
- **FR-M3**: Librarian can view and update a member’s validity (`membership_valid_until`) and status (`members.membership_status`).
- **FR-M4**: UI renders `members.membership status` to the member on dashboard.

### 3.3 Book Catalog

- **FR-C1**: Public non-authenticated users may view and search books by one or more criteria.
- **FR-C2**: Members and Librarians can view further book details.
- **FR-C3 (Librarian)**: Add new books.
- **FR-C4 (Librarian)**: Edit book details.
- **FR-C5 (Librarian)**: Remove books.

### 3.4 Borrowing

#### FR-L1: Create Loan (Member)

**Eligibility Criteria:** Member can create a loan if all are satisfied.
members.membership_status='Active'
members.membership_valid_until ≥ today
Member has no overdue items (return_date < today)
Total unpaid fines ≤ $10
Fewer than 3 active loans

**Implementation: **
BorrowController, BorrowService using LoanRepository with custom queries to check eligibility.

**Process:**
Validate membership status:
Fetch the member record.
Confirm membership*status = 'Active' and membership_valid_until ≥ today.
`boolean isEligible = memberRepository.existsByIdAndMembershipStatusAndMembershipValidUntilAfter(memberId, "Active", LocalDate.now());`
\_If not valid, return an error to the member.*

Check overdue items:
Query the loans table for any active loans (status = 'Active') with due*date < today.
`List<Loan> overdueLoans = loanRepository.findByMemberIdAndStatusAndDueDateBefore(memberId, "Active", LocalDate.now());`
\_If overdue loans exist, block borrowing and inform the member.*

Check unpaid fines:
Query the fines table for any unpaid fines linked to the member.
`List<Fine> unpaidFines = fineRepository.findByMemberIdAndPaidFalse(memberId);`
Sum the amounts.
`BigDecimal totalUnpaidFines = unpaidFines.stream().map(Fine::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);`
**If total > $10, block borrowing.**

Check active loans count:
Count the number of active loans for the member (status = 'Active').
`long activeLoansCount = loanRepository.countByMemberIdAndStatus(memberId, "Active");`
**If count ≥ 3, block borrowing.**

Create loan:
Insert a new row in loans with status = 'Active', loan_date = today, due_date = today + 14 days, and renew_count = 0.
`loanRepository.save(new Loan(member, book, LocalDate.now(), LocalDate.now().plusDays(14), null, 0, "Active"));`

Update book availability:
Decrement `books.available_copies` by 1.
`bookRepository.updateAvailableCopiesById(bookId, book.getAvailableCopies() - 1);`
If available_copies = 0, set books.status = 'Out for loan'.

Return confirmation:

Return loan details and updated book availability to the member.

**Outcome:**
Loan successfully created.
Book copy availability is updated.
Member’s active loans count (derived in app) increases by 1.

#### FR-L2: Return Loan (Member)

**Implementation:**
ReturnController, BorrowService using LoanRepository.

**Process:**
Member submits return request.
Backend sets `loans.return_date` to today.
Computes fines if overdue (0.50/day, max $20).
`books.available_copies` is incremented.

**Outcome:**
`loans.status` set to 'Returned'. (from 'Active')
books.status set to Available.
Book copy becomes available for reservation queue 1 member's notification.
Fines recorded in fines table.

#### FR-L3: Renew Loan (Member)

Eligibility Criteria: Loan can be renewed if...
`loans.due_date !< today.
Loan is not reserved by other members.
`loans.renew_count` < 2.

**Implementation:**
RenewController, BorrowService using LoanRepository.

**Process:**
System checks renewal eligibility above
Increment `loans.renew_count`.
Add 14 days to `loans.due_date`.

**Outcome:**
Loan due date updated.
Renewal count updated.

#### FR-L4: View Loans (Member)

**Implementation: **
LoanController, LoanService, LoanRepository.

**Process:**
Member requests current loans.
System retrieves loans with book details, due dates, renewal counts, fines, overdue status.

**Outcome:**
Member sees complete list of active loans with all relevant information.

#### FR-L5: Update Book Availability (System)

**Implementation:**
LoanService backend logic; (no database triggers in MVP)

**Process:**\
On loan creation: decrement available_copies.
On loan return: increment available_copies.

**Outcome:**
available_copies always reflects actual available books.

### 3.5 Reservations (Hold/Reservation feature not in MVP. For future version)

#### FR-R1: Place Reservation (Member)

**Eligibility Criteria:**
Member cannot reserve a book they currently have on loan.
Member cannot reserve a book already reserved by themselves.

**Implementation:**
ReservationController, ReservationService, ReservationRepository

**Process:**
Member requests a reservation for a book.
System checks eligibility and creates a new row in reservations with status = 'Waiting'.

**Outcome:**
Member is added to the reservation queue for the book.

#### FR-R2: Notify & Hold Book (Notification feature not in MVP. For future version)

**Process:**
When a book’s available_copies > 0 and reservation queue exists, system sets the first reservation’s status = 'On Hold' and hold_until = now() + 3 days.

**Outcome:**
Member can pick up the book within 3 days.
FR-R3: Cancel or Expire Reservation

**Process:**
Member may cancel a reservation manually.
If hold_until expires without collection, status becomes Cancelled and next reservation in queue moves to On Hold.

**Outcome:**
Reservation queue updates automatically to maintain FIFO order.
