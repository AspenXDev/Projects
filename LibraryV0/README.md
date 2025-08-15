# Library Management System (LMS)

A full-stack web application for managing a community library’s printed materials and lending operations. MVP supports two user roles: **Member** and **Librarian**.

This project fulfills the NTUC Learning Hub Fullstack Development SCTP Capstone Project requirement.  
**Note:** Deployment is **not required** for this submission.

---

## Objectives

- Develop a functional web application using **Spring Boot**, **React.js**, and **MySQL**.
- Demonstrate knowledge of:
  - Java & Spring Boot (MVC architecture)
  - ReactJS & modern frontend development
  - Database design & CRUD operations
  - Software design, architecture, and testing
- Showcase transferrable skills from project management and engineering experience.

---

## Tech Stack

**Frontend**

- React.js for responsive browser behavior
- Axios for API communication
- `react-router-dom` for routing & guarded routes

**Backend**

- Java 17+
- Spring Boot & Spring Security (BCrypt for authentication)
- RESTful APIs (JWT-based auth)

**Database**

- MySQL Server for data persistence

**Other Tools**

- Git & GitHub for version control
- Postman for API testing

---

## Architecture & Design

The application follows **MVC architecture**:

**View**

- React components and pages managed via `react-router-dom`
- `PrivateRoute` ensures access based on **Member or Librarian** role
- Communicates with backend via REST API calls (Axios)

**Model**

- JPA Entities & Spring Data JPA Repositories
- Data validation and business-rule enforcement

**Controller / Service**

- Spring Boot REST Controllers map HTTP requests
- Service layer encapsulates business logic and coordinates persistence
- JSON responses returned to the frontend

**Security (Defense-in-Depth)**

- **Client-side**: Route guarding via `PrivateRoute` to prevent navigation to protected pages
- **Server-side**: Method/endpoint authorization (e.g., `@PreAuthorize`), JWT validation, and input validation/sanitization
  > Sensitive actions are protected by **client-side route guarding (`PrivateRoute`)** and **server-side authorization (`@PreAuthorize`)**, plus input validation to remain secure even if the frontend is bypassed.

---

## Features Overview

### Member

- Sign up / Log in
- Search books by title, author, or ISBN
- View book details and availability
- Borrow, renew (max 2), return, and reserve books
- View own loan history and fines
- On-screen notifications for due dates, overdue books, and reservation availability

### Librarian

- All Member functions
- Add, edit, and remove (archive) books
- Manage loans, renewals, and returns
- Manage categories/material types
- View and manage reservation queues

---

## Navigation & Access Control (RBAC)

| Role      | Pages / Components Access                                                                           |
| --------- | --------------------------------------------------------------------------------------------------- |
| Member    | Search Books, Book Details, Borrow/Renew/Return, Reserve, My Loans/History, My Fines, Profile       |
| Librarian | All Member pages + Administer Books (Add/Edit/Archive), Manage Loans/Returns/Renewals, Reservations |

- RBAC enforced in **React routes** (client) and **Spring Security** (server).
- Sensitive mutations are **always re-checked** server-side.

---

## Authentication Flow

1. User submits login form in React.
2. React POSTs to `/api/auth/login` with credentials.
3. Backend validates credentials (BCrypt) and issues a **JWT** with role claims.
4. React stores the JWT (e.g., `localStorage`) and uses it in the `Authorization: Bearer <token>` header.
5. `PrivateRoute` reads role claims to guard routes.
6. Spring Security validates JWT and authorizes protected endpoints.

---

## Project Plan & Milestones (Instructor)

Total project duration: **40 hours**

- **Milestone 1: Project Setup (4 hrs)**  
  Initialize Spring Boot & React projects, set up MySQL, basic docs.

- **Milestone 2: User Registration & Authentication (6 hrs)**  
  Auth endpoints, registration/login flows, frontend forms, documentation updates.

- **Milestone 3: Book Management (Admin) (8 hrs)**  
  _Note: “Admin” maps to **Librarian** in this MVP._  
  CRUD endpoints, Librarian dashboard/UI, update ERD & API docs.

- **Milestone 4: Book Search & Borrow/Return (10 hrs)**  
  Search (backend + frontend), borrow/return endpoints & UI, real-time availability updates.

- **Milestone 5: Overdue Notifications (4 hrs)**  
  Scheduled checks for overdue loans; UI notifications.

- **Milestone 6: Integration, Testing & Final Documentation (8 hrs)**  
  Integrate frontend-backend, testing (unit/integration), finalize documentation, presentation prep.

---

## Deliverables (Instructor / Training Provider)

### Running Application

A fully functional LMS demonstrating all core functionalities.

### (Optional) GitHub Repository

Source code, versioned and documented.

### Documentation

- **BRD** and **SRS**
- **System Design Document** (architecture, database/ERD, API, UI mock-ups)
- **Test Plan** and sample **Test Cases**

### User Manual

- Basic usage guide for Members and Librarians

### Developer Documentation (optional)

- Setup, run scripts, environment notes, and coding conventions

---

## Resources & Methodology

- Solo development (Victor): GitHub, Postman, MySQL Workbench, IntelliJ/VS Code.
- Hybrid approach: upfront design (DB, API), then iterative feature development and testing aligned to the milestones.
- Testing: JUnit (backend), Jest/RTL (frontend), Postman collections for endpoints.

---
