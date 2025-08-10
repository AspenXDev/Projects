# Library Management System

This Readme outlines the architecture, design, development, and other considerations of a on-prem full-stack web application that manages a community library’s printed material, users, and lending operations.

## Objectives

- To fulfill NTUC Learning Hub's Fullstack Development Skillsfuture Career Transition Program's (SCTP) Capstone Project module requirement.
- To showcase knowledge and proficiency in the following: - Java and Java Spring Boot proficiency in MVC architecture - JavaScript and ReactJS - Database awareness of both SQL and NoSQL solutions and appropriateness - Software design and architecture - Linux
- To demonstrate 15+ years' project management experience and engineering background as transferrable skills for problem solving in software development too.

## Tech Stack

Frontend:

- React.js for responsive behavior on mainstream browsers
- Axios for HTTP requests

Backend:

- Node.js & npm
- Vite and Maven for scaffolding
- Java 17+
- Spring Boot
- Spring Security (BCrypt for authentication)
- RESTful API

Database:

- MySQL Server for the persistence and CRUD of structured data

  Others:

- GitHub with Git Bash for version control
- Postman for API testing
- (Docker?)

---

## Architecture and Design

MVC architecture is used for the below basic design.

### View

- Managed with PrivateRoute with { BrowserRouter as Router, Routes, Route } from 'react-router-dom' library
- Developed using HTML, CSS, ReactJS for responsive clean modern styling
- Utilizes `react-router-dom` for navigation and route protection:
- Components are routed using `{ BrowserRouter as Router, Routes, Route }`.
- `PrivateRoute` (custom component to be coded) guards sensitive pages, allowing access only to authenticated users.
- Communicates with the backend via REST API calls using `fetch` or `axios`.

### Model

- Developed in Java, using JPA Entities and Spring Data JPA Repositories.
- Handles data persistence and retrieval operations.
- Interacts with a MySQL database to store user, book, and transaction data.
- @PreAuthorize annotation (eg. `@PreAuthorize("hasRole('ADMIN')")`) to be used to block unauthorized API calls for full RBAC enforcement.

### Controller

- Implementation by Spring Boot REST Controllers.
- Handles HTTP requests and maps them to appropriate service methods.
- Service Layer (Part of Controller Logic)
- Encapsulates business logic.
- Interacts with the model/repositories and prepares data for the controller.
- Ensures separation of concerns between controller and persistence layer.
- Validates input, manages business logic, and returns JSON responses.

## Features Overview

### Functional Features

#### for Users

- Sign up / Log in
- Search for books
- View book availability
- Borrow/renew/return/reserve books

#### for Staff/Librarian/Admin

- Add/edit/delete books
- Manage users
- View borrowing history
- Process late and damage fees

## Non-Functional Features

### UI Policies

#### Navigation

| Role      | Pages/Components Access                                                                 |
| --------- | --------------------------------------------------------------------------------------- |
| Member    | Search Books, View Book Details, View Loans, Renew Loans, Reserve Books, Manage Profile |
| Staff     | All Member functions + Borrow/Return Interface + Fine Processing                        |
| Librarian | All Staff functions + Cataloging Tools                                                  |
| Admin     | All access including System Configuration                                               |

#### Access Control (ACL-style enforcement)

- All access is role-based (RBAC) (using page blocking with routes)
- Sensitive actions (deleting records, overriding due dates) are protected via middleware.
- Application-driven security. Security enforcement at UI, and application logic.

## Authentication Flow

User submits login form in React with username and password.

React sends a POST request to /api/auth/login with the credentials.

Spring Boot backend verifies credentials using BCrypt password hashing.

If authentication succeeds, backend generates a JWT containing user details and roles, and returns it in the response.

React stores the JWT token in the browser’s localStorage.

React’s PrivateRoute component reads the token from localStorage to grant or deny access to protected routes.

For subsequent API requests, React includes the JWT token in the Authorization header.

Backend validates JWT tokens on protected requests using a utility JWTUtil in Java to check token validity and expiration.

## Other PM-Use Materials

### RACI Matrix Mapping Stakeholders w/ Tasks

Member = Library member (owns a library card)
Staff = part-time assistance librarian(s)
Librarian = full-time qualified specialist at cataloguing and maintaining ops
Admin = sysadmin from IT; Manages various systems and facilities at community center

| Process/Task                                         | Member | Staff | Librarian | Admin |
| ---------------------------------------------------- | ------ | ----- | --------- | ----- |
| User Management                                      |        |       |           |       |
| User sign-up/creation                                | A      | C     | I         | R     |
| User profile management (self)                       | R      | C     | I         | C     |
| Reset user password (self)                           | R      | C     | I         | C     |
| User account status management (e.g., block/unblock) | I      | R     | C         | A     |
| Book & Collection Management                         |        |       |           |       |
| Search for a book                                    | R      | R     | R         | R     |
| View book details & availability                     | R      | R     | R         | R     |
| Add a new book to the collection                     | I      | C     | R         | A     |
| Edit book details                                    | I      | C     | R         | A     |
| Remove a book from the collection                    | I      | C     | R         | A     |
| Manage book locations (shelving)                     | I      | R     | C         | A     |
| Lending Operations                                   |        |       |           |       |
| Borrow a book                                        | R      | A     | I         | I     |
| Return a book                                        | R      | A     | I         | I     |
| Renew a book loan                                    | R      | A     | I         | I     |
| View own borrowing history                           | R      | C     | I         | I     |
| View member's borrowing history                      | I      | R     | A         | C     |
| Financial & Reporting                                |        |       |           |       |
| Process late fees                                    | I      | C     | R         | A     |
| Manage library budget/acquisitions                   | I      | C     | A         | R     |
| Generate usage reports                               | I      | C     | C         | R     |
| System & Maintenance                                 |        |       |           |       |
| Back up the database                                 | I      | I     | C         | R     |
| Update application software                          | I      | I     | C         | R     |

### RACI Matrix for the Project SDLC

(This was an exercise of theoretical knowledge; Victor was RACI for all items in reality)
| Activity/Deliverable | Product Owner/Manager/SCRUM Master (Victor?) | Developer (Victor) | QA/Tester (Victor) | Ops Team (Victor?) |
| ----------------------------------------- | -------------------------------------------- | ------------------ | ------------------ | ------------------ |
| Project Initiation & Planning | R | C | C | C |
| Define project scope & requirements | A | C | C | I |
| Prioritize features & backlog | R | C | C | I |
| **Design &** **Development** | | | | |
| Design database schema | C | R | I | C |
| Develop backend APIs (Spring Boot) | I | R | C | I |
| Develop frontend UI (React) | I | R | C | I |
| Code reviews | A | R | C | I |
| **Testing & Quality** **Assurance** | | | | |
| Write unit tests | I | R | A | I |
| Perform integration testing | C | R | C | I |
| Conduct user acceptance testing (UAT) | R | C | A | I |
| **Deployment &** **Maintenance** | | | | |
| Configure production environment (Docker) | I | C | I | R |
| Deploy application to server | I | C | I | A |
| Monitor system performance | I | C | I | R |
| Respond to bug reports | A | R | C | C |

### Project Methodology and Resources

A pure Waterfall model could be too rigid for a full-stack application.
And a pure Agile might deprioritize upfront design, which is critical in a solo full-stack effort with database and architecture dependencies.

Considering the project's scale and the clear stakeholder roles, a hybrid approach combining aspects of Waterfall and Agile is likely the most suitable.

#### Waterfall for Planning and Design

Use a Waterfall-like approach for the initial phases: database schema, features, and tech stack.

#### Agile for Implementation and Deployment

Transition to Agile approach for the Implementation, Testing, and Deployment phases.
Work in sprints, developing and testing features incrementally (e.g., a "User Features" sprint followed by an "Admin Features" sprint).
This allows for flexibility and incorporating feedback from the product owner and testers along the way.

#### Waterfall (?) for Testing Stages

- Unit Testing (UT): Responsibility of devs. As code is written, create unit tests for individual functions and components to ensure they work as expected.
  - Use JUnit (Spring Boot) and Jest (React)
- Integration Testing (IT): Collaborative effort between the dev and the QA/Tester. Test different parts of the application -- like the React frontend and the Spring Boot backend, interact with each other -- including testing connection to the MySQL database.
- System Integration Testing (SIT): Can likely merge this with IT for a project of this size. The goal is to verify that the entire system functions as a cohesive whole.
- User Acceptance Testing (UAT): Led by the Product Owner (the librarian or a proxy). They will test the application to ensure it meets the initial requirements and is easy for end-users (librarians and members) to use. This is a critical final step before launch.

---

Although these test phases resemble Waterfall, testing will be executed iteratively across sprints. Each sprint will include UT and IT. UAT is reserved for sprint-end demos and post-MVP testing.

#### Resources

Given that this is a solo effort, the below time are estimated though scope is limited.

---

##### Phase 1: Planning and Design (Waterfall)

- Requirements and Scope: Defining features, user stories, and tech stack. \*_(0.5-1.0 man-weeks)_
- Database Schema Design: Finalizing tables, relationships, and data types. (0.5-1.0 man-weeks)
- API Design: Outlining all REST endpoints and data models. (0.5-1.0 man-weeks)
- Total for Planning & Design: (1.5-3.0 man-weeks)

##### Phase 2: Implementation (Agile Sprints)

- Sprint 1: Backend Setup & Authentication: Setting up Spring Boot, Spring Security, creating user/role models, and implement the login flow. (0.5-1.0 man-weeks)
- Sprint 2: Core User Features: Develop APIs for searching books, viewing details, and user profile management. (0.5-1.0 man-weeks)
- Sprint 3: Core Admin Features: APIs for adding/editing/deleting books, managing users, and handling loans. (0.5-1.0 man-weeks)
- Sprint 4: Frontend Development: Building the React UI for the user-facing features. (0.5-1.0 man-weeks)
- Sprint 5: Admin UI & Fines: Building the React UI for admin panels and implementing the late fees logic. (0.5-1.0 man-weeks)
- Total for Implementation: (2.5-5.0 man-weeks)

##### Phase 3: Testing & Security

- Unit & Integration Testing: Writing and running tests for both the frontend and backend. (0.5-1.0 man-weeks)
- UAT Preparation: Creating test cases and documentation for the product owner. (0.5-1.0 man-weeks)
- Total for Testing & Security: (1.0-2.0 man-weeks)

---

##### Phase 4: Deployment & Data Migration

- Containerization: Writing Dockerfiles for frontend and backend, and `docker-compose` file. (20-40 hours / 0.5-1 man-week)
- Server Setup: Configuring server environment, reverse proxy (e.g., Nginx), and database. (20-40 hours / 0.5-1 man-week)
- Data Migration: Writing and running the script to transfer existing data. (20-40 hours / 0.5-1 man-week)
- Total for Deployment & Migration: 60-120 hours (1.5-3 man-weeks)

#### Overall Project Estimate

- W/o Phase 4: 5 -10 man-weeks
- With Phase 4: 6.5 -13 man-weeks
  This gives a total estimated project time of 1 to 3 months of full-time work. The wide range accounts for the learning curve, bug fixing, and some scope creep that might occur.

---
