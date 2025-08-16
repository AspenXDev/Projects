# Software Requirements Specification (SRS) — Library Management System (MVP)

## 1. Introduction

### 1.1 Purpose

Define the functional and non-functional requirements for the LMS MVP supporting two roles: **Member** and **Librarian**.

### 1.2 Scope

Web application with Spring Boot backend, React frontend, and MySQL database, delivered per the six instructor milestones. No public deployment required.

### 1.3 Definitions

- **Member**: Library patron.
- **Librarian**: Catalog, circulation, and membership administration.
- **MVP**: Minimum Viable Product.

### 1.4 References

- Instructor’s Project Overview & Milestones
- BRD.md
- System Design Document (ERD, API, UI)
- Test Plan & Test Cases

---

## 2. System Overview

- **Frontend**: React, `react-router-dom`, Axios; `PrivateRoute` for route guarding by role.
- **Backend**: Spring Boot; Spring Security with JWT & BCrypt; RESTful APIs with validation and `@PreAuthorize`.
- **Database**: MySQL with relationships, constraints, and indexes.

Security is enforced **client-side** (route guard) and **server-side** (JWT validation, role checks, input validation).

---

## 3. Functional Requirements

### 3.1 Authentication & Authorization

- **FR-A1**: Users register as Member or Librarian.
- **FR-A2**: Users log in with email/username + password.
- **FR-A3**: System issues JWT on successful login; validates JWT on protected endpoints.
- **FR-A4**: Client restricts access via `PrivateRoute` based on role.
- **FR-A5**: Passwords stored using strong hashing (e.g., BCrypt).

### 3.2 Membership Management (Validity)

- **FR-M1**: On Member registration, system sets `membership_valid_until = registration_date + 1 year`.
- **FR-M2**: System blocks borrowing/renewal if membership is expired (today > `membership_valid_until`) or account inactive.
- **FR-M3**: Librarian can view and update a member’s validity date and status (e.g., extend 1 year, mark Active).
- **FR-M4**: UI surfaces membership status/expiry to the member on dashboard.

### 3.3 Book Catalog

- **FR-C1**: Search books by title, author, ISBN; optional filters (category, year).
- **FR-C2**: View book details (title, author, ISBN, category, year, copies available, status).
- **FR-C3 (Librarian)**: Add new books with validation.
- **FR-C4 (Librarian)**: Edit book details.
- **FR-C5 (Librarian)**: Remove/archive books (excluded from member search).

### 3.4 Lending

- **FR-L1 (Member)**: Borrow available copies if: membership valid; no overdue items; fines ≤ $10; under 3 active loans.
- **FR-L2 (Member)**: Return books; system records return date, updates availability.
- **FR-L3 (Member)**: Renew up to **2** times if not reserved by others and not overdue.
- **FR-L4 (Member)**: View current loans and loan his
