# ibrary Management System

A full-stack web application to manage a community library’s printed material, users, and lending operations.

---

## 🚀 Tech Stack

**Frontend:**

- React.js
- Axios for HTTP requests

**Backend:**

- Java
- Spring Boot
- Spring Security (for authentication)
- RESTful API

**Database:**

- MySQL

**Others:**

- Git for version control
- Postman for API testing
- Docker

---

## Features Overview

### User Features

- Sign up / Log in
- Search for books
- View book availability
- Borrow and return books

### Admin Features

- Add/edit/delete books
- Manage users
- View borrowing history
- Process late fees
- ***

## Authentication Flow

1. User enters credentials in React login form.
2. React sends a `POST` request to `/api/auth/login`.
3. Spring Boot validates credentials using BCrypt password hashing.
4. If valid, returns an HTTP 200 and user info/token (depending on implementation).
5. React stores session info (e.g., localStorage? not decided yet).

### Tech

- Java 17+
- Node.js & npm
- Vite and Maven for scaffolding
- MySQL Server
- Git
