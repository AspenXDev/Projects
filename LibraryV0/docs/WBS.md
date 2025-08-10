# 1.0 Frontend

## 1.1 Landing Page

### 1.1.1 Welcome / Info

### 1.1.2 Search Bar (accessible here too)

## 1.2 Authentication

### 1.2.1 Login Page

### 1.2.2 Register Page

### 1.2.3 Password Reset

## 1.3 Book Catalog

### 1.3.1 Book Search Results Page - Search by title, author, ISBN - Sorting & filtering options

### 1.3.2 Book Detail View - Show synopsis, availability, shelf location, return date - Reserve button (conditional)

### 1.3.3 Book Reservation Flow - Reserve book, view reservation queue position

## 1.4 Member Dashboard

### 1.4.1 Current Loans ("My Loans") - List borrowed books with due dates, renewal button (once) - Renew Book functionality (with validation)

### 1.4.2 Loan History - Past borrowed books, return dates, overdue flags

### 1.4.3 Reservations - List active reservations - Cancel reservation option

### 1.4.4 Fines and Payments - View outstanding fines - Pay fines online (payment UI)

### 1.4.5 Notifications - Due date reminders - Overdue alerts - Reservation ready notifications

### 1.4.6 Profile / Account Settings - Edit personal info - Change password

## 1.5 Staff Panel

### 1.5.1 Member Search (by ID)

### 1.5.2 Book Check-Out - Scan/enter ISBN for checkout - Confirm borrowing rules

### 1.5.3 Process Returns - Scan/enter ISBN for returns - Overdue fine calculation display

### 1.5.4 Mark Book Lost/Damaged - Update status & fees interface

## 1.6 Librarian Panel

### 1.6.1 Add New Book - Form with validation for book details

### 1.6.2 Edit Book Details - Search/select book to update info

### 1.6.3 Remove/Archive Book - Mark book as archived/removed with reason

### 1.6.4 Manage Categories/Genres - Add/Edit/Delete categories with confirmation

## 1.7 Admin Panel

### 1.7.1 User Role Management - View all users and roles - Change user roles with validation

### 1.7.2 User Account Activation - Activate/deactivate accounts with logs

### 1.7.3 System Activity Logs Viewer - Filterable logs display (actions, dates, users)

## 1.8 Common / Utility Pages

### 1.8.1 About / Help Page

### 1.8.2 Error / 404 Page

# 2.0 Backend (Spring Boot)

## 2.1 Authentication & Authorization

### 2.1.1 User Registration (Member, Staff, Librarian, Admin)

### 2.1.2 Login (issue JWT tokens)

### 2.1.3 Password Reset Flow

### 2.1.4 Role-based Access Control (RBAC) Middleware / Filters

### 2.1.5 Token validation and refresh

## 2.2 User Management

### 2.2.1 CRUD operations on Users

### 2.2.2 Role assignment & changes (admin only)

### 2.2.3 Account activation/deactivation

### 2.2.4 Logging user activity (audit trails)

## 2.3 Book Management

### 2.3.1 CRUD operations for Books (Librarian)

### 2.3.2 Manage Categories/Genres

### 2.3.3 Mark book as lost/damaged

### 2.3.4 Search Books (by title, author, ISBN, category)

### 2.3.5 Book availability status tracking

## 2.4 Loan Management

### 2.4.1 Borrow book (check member standing, availability)

### 2.4.2 Renew book (with validation)

### 2.4.3 Return book (calculate fines if overdue)

### 2.4.4 Loan history tracking

### 2.4.5 Staff checkout on behalf of member

## 2.5 Reservation Management

### 2.5.1 Reserve book (only if on loan)

### 2.5.2 Manage reservation queue

### 2.5.3 Cancel reservation

### 2.5.4 Notify member when reserved book available

### 2.5.5 Reservation expiry & reassign

## 2.6 Fine Management

### 2.6.1 Calculate overdue fines automatically

### 2.6.2 Retrieve outstanding fines per member

### 2.6.3 Record fine payments (online/offline)

### 2.6.4 Fine waiver (staff/librarian privilege)

## 2.7 Notification System

### 2.7.1 Due date reminders (2 days before)

### 2.7.2 Overdue notifications (daily)

### 2.7.3 Reservation availability alerts

### 2.7.4 On-screen notifications API

## 2.8 System Activity Logging

### 2.8.1 Record actions (borrowing, returns, user changes, etc.)

### 2.8.2 Provide admin-accessible filtered logs endpoint

## 2.9 Middleware / Filters

### 2.9.1 Authentication filter (JWT validation)

### 2.9.2 Authorization filter (Role-based access)

### 2.9.3 Input validation & sanitization middleware

### 2.9.4 Error handling (global exception handlers)
