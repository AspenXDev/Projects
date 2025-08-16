# Business Requirements Document (BRD) — Library Management System (MVP)

## 1. Project Overview

The Library Management System (LMS) is an MVP web application to streamline core library operations. It supports two roles:

- **Member** — search, borrow, renew, return, reserve; view history and fines.
- **Librarian** — all Member capabilities plus catalog/loan administration and membership management.

This MVP follows the instructor’s 40-hour milestone plan; deployment is **not required**.

## 2. Business Objectives

- Provide an easy way for members to find and borrow books.
- Reduce manual workload for librarians via simple catalog/loan/membership management.
- Demonstrate a working MVP aligned to the course milestones and deliverables.

## 3. Stakeholders

- **Members** (library patrons)
- **Librarians** (catalog & circulation)
- **Instructor/Training Provider** (review & acceptance)

## 4. Scope

### In Scope (MVP)

- Registration & login (Members, Librarians) with role-based access.
- Book catalog: add/edit/remove (Librarian), search & view (Member/Librarian).
- Lending: borrow, return, renew (with rules enforcement).
- Reservations: FIFO queue & 3-day hold.
- Overdue detection & **on-screen** notifications.
- Fine calculation and display (no online payments).
- **Membership validity management**: 1-year validity from registration; borrowing blocked if expired; Librarian can extend/mark active.

### Out of Scope (MVP)

- Public deployment/hosting; email/SMS notifications.
- Online fine payments/payment gateway.
- Advanced reporting/analytics; multi-branch libraries.
- Audit-log UI.

## 5. Business Rules (MVP — from instructor brief)

### Membership Rules

- Members can borrow a **maximum of 3 books** at a time.
- Membership is **valid for 1 year** from registration.
- Members **must have valid (non-expired) membership** to borrow.

### Lending Rules

- **Loan duration: 14 days**.
- **Maximum 2 renewals** per book (only if not reserved by others).
- **Cannot borrow** if having **overdue books**.
- **Cannot borrow** if accumulated **fines > $10**.

### Fine Calculation

- **$0.50 per day** for overdue books.
- Fine starts **the day after the due date**.
- **Maximum fine $20 per book**.

## 6. Constraints

- **Timeline**: 40 hours total (6 milestones).
- **Roles**: exactly **two** (Member, Librarian).
- **Tech**: Spring Boot (Java), React, MySQL.
- **Language**: English only.
- **Environment**: local/demo; deployment not required.

## 7. Assumptions

- Demo on a local machine or local network with sample data.
- Single-branch library; printed materials only.
- Librarians have basic computer literacy.

## 8. Milestones (per Instructor)

1. Project Setup (4h)
2. User Registration & Authentication (6h)
3. Book Management (Librarian) (8h)
4. Book Search & Borrow/Return (10h)
5. Overdue Notifications (4h)
6. Integration, Testing & Final Documentation (8h)

## 9. Acceptance Criteria

- All core features above implemented and functional with rules enforced (incl. membership validity, loan limits, renewals, fines).
- Code follows standards; basic tests; proper error handling; no critical bugs.
- Completed documentation (BRD, SRS, System Design, Test Plan & cases, User Manual).

## 10. Deliverables

- Running application (local demo)
- (Optional) GitHub repository
- Documentation: BRD, SRS, System Design (a
