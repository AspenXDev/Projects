# Backlog

## For MVP (Aligned to Instructor Milestones)

1. **Database Schema Finalization**

   - Ensure schema matches updated user stories (Member + Librarian only).
   - Include RBAC (role-based access control) support in tables and relationships.
   - Due by Milestone 1 (Project Setup).
   - (Create separate BRD and SRS from README also)

2. **Access Control Definition**

   - Map each endpoint to allowed roles.
   - Apply **frontend route guarding** (`PrivateRoute`) and **backend authorization** (`@PreAuthorize`).
   - Document in EndPoints.md before Milestone 2.

3. **Authentication & Role Verification**

   - Implement JWT authentication with role claims.
   - Secure storage for JWT (localStorage) with client-side context/state check.
   - Ensure login/registration flows completed by Milestone 2.

4. **Book Management (Librarian)**

   - CRUD operations for books, categories, and material types.
   - Validation rules for required fields.
   - To be completed by Milestone 3.

5. **Book Search & Borrow/Return**

   - Search API with filters (title, author, ISBN).
   - Borrow/return endpoints & frontend integration.
   - Availability status updates in real time.
   - To be completed by Milestone 4.

6. **Overdue Notifications**

   - Scheduled backend task to flag overdue loans.
   - Display overdue notices on Member dashboard.
   - Completed by Milestone 5.

7. **Integration & Testing**
   - Connect frontend & backend, test all flows.
   - Finalize documentation (BRD, SRS, System Design, Test Plan, User Manual).
   - To bec completed by Milestone 6.

---

## For Deployment / Future Versions

The following enhancements are out of MVP scope due to time and scope constraints.

### Security

- Current MVP (v0) designed for local intranet use at a physical community library.
- Future versions for internet deployment will require:
  - HTTPS & stricter CORS policy
  - PostgreSQL row-level security
  - JWT storage in memory (React Context) to reduce XSS risk

### Notifications

- MVP: On-screen notifications only
- Future: Email and SMS notifications for borrow/renew/reserve events

### Data Management

- MVP: Dummy seed data for demo
- Future:
  - Data migration scripts (users/books/logs)
  - CSV import/export for batch updates

### Loan Renewal Policy

- MVP: Unlimited renewals allowed
- Future: Track `renew_count` and limit consecutive renewals to prevent abuse

### Fine Payments

- MVP: Manual tracking of fines by librarian
- Future: Integrated payment gateway for secure fine collection
