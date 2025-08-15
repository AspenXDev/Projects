# Work Breakdown Structure (WBS)

## 1. MVP Features (Aligned with Instructor Requirements)

### 1.1 Authentication & Authorization

- User registration (Member, Staff, Admin)
- User login with email/password
- JWT issuance and verification
- Password reset flow
- Role-based access control (RBAC)
- Backend protection for sensitive actions (`@PreAuthorize`)
- Client-side access control with `PrivateRoute`
- Input validation for all user-submitted data

### 1.2 Book Management

- Add new books (Staff/Admin)
- Update book details (Staff/Admin)
- Delete books (Staff/Admin)
- Search and filter books by title, author, genre, availability (All users)
- View book details (All users)

### 1.3 Loan Management

- Borrow book (Member)
- Return book (Member)
- View user loan history (Member)
- View all active loans (Staff/Admin)

### 1.4 Notifications & Status Updates

- Display loan status updates to users
- Notify users of overdue books (MVP can be manual flagging)

### 1.5 Core UI & Navigation

- Login/Register pages
- Book list page (search & filter)
- Book detail page
- Loan history page (user-specific)
- Admin dashboard (books & loans overview)

---

## 2. Full Version (Post-MVP Enhancements)

### 2.1 Enhanced Notifications

- Automated overdue email notifications
- Loan due reminders

### 2.2 Fine Management

- Automatic fine calculation for overdue books
- Fine payment tracking

### 2.3 Reservation System

- Reserve currently unavailable books
- Notify users when reserved book becomes available

### 2.4 Advanced Search & Recommendations

- Search by ISBN, publication date, tags
- Recommended books based on borrowing history

### 2.5 Reporting & Analytics

- Book borrowing trends
- Member activity reports
- System usage statistics

### 2.6 UI Enhancements

- Responsive design improvements
- Dark mode
- Accessibility compliance
