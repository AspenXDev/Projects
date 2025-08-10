# Backlog

## For MVP

1. Double check DB Schema (due 2025/08/12) against completed UserStories finished on 2025/08/10
2. Think about access control for each endpoint based on roles.

## For Deployment / Future Versions

The following are items noted for improvement but shall be addressed only in future versions due to constraints and limitations.

### Security

- Current version `v0` is designed for physical community center’s on-prem intranet use only.

  - Future versions to consider higher security for internet access (e.g., row-level security with PostgreSQL).

- JWT currently stored in `localStorage` is vulnerable to XSS, but is easy to implement and persists beyond page refreshes.
  - Future version to use `localStorage` in tandem with `React Context API` to read from `localStorage` to manage auth status in-memory across components.

### Notifications

MVP uses **on-screen confirmation** only.

- Future versions to add **email and SMS confirmations** for borrow/renew/reserve events.

### Data Management

MVP uses dummy data for demonstration.

- Future versions to develop data migration script for users/books/logs.
- CSV import/export functions also to be considered.

### Business Feasibility

MVP allows members to renew each and everytime loan expires.

- Future versions to track member's consecutive renewal of book, to deter abuse of privilege with `renew_count` .

Fines are paid directly to staff/librarian, and are tracked manually by the collector without verification.

- Future versions shall implement a payment gateway to process fines more securely.
