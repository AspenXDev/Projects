## Role: Member

### 1. Search for a Book

> As a `Member`,
> I want to search for a book by title, author, or ISBN,
> so that I can find the book I'm interested in.
> Acceptance Criteria:

- The search bar is visible on the main page.
- I can enter keywords or an ISBN to search.
- The system returns a list of matching books, showing title, author, and availability status.
- If no results match, the system displays a “No books found” message.
- Search results are sortable by title, author, or publication year.

---

### 2. View Book Details

> As a `Member`,
> I want to view full details of a book,
> so that I can learn more about it before deciding to borrow or reserve it.
> Acceptance Criteria:

- Clicking on a search result opens the book details page.
- The details page shows title, author, ISBN, synopsis, publication year, category, and availability.
- If available, I can see the shelf location.
- If on loan, I can see the expected return date and option to reserve.

---

### 3. Borrow a Book

> As a `Member`,
> I want to borrow an available book,
> so that I can read it.
> Acceptance Criteria:

- I can only borrow if my account is in good standing (no overdue books or unpaid fines).
- Upon borrowing, the book status changes to `On Loan`.
- The system sets a standard due date (e.g., 14 days from checkout).
- An on-screen confirmation message appears with the due date and book details.

---

### 4. Renew a Book

> As a `Member`,
> I want to renew a book I currently have on loan,
> so that I can extend the borrowing period without returning it.
> Acceptance Criteria:

- The renewal option is available in “My Loans” for books not overdue and not reserved by others.
- I can renew a book only once per loan.
- Renewal extends the due date by the standard loan period.
- The system sends a renewal confirmation notification.
- The system prevents renewal if conditions are not met.

---

### 5. Return a Book

> As a `Member`,
> I want to return a borrowed book,
> so that my loan record is updated and the book becomes available for others.
> Acceptance Criteria:

- Returns are processed by `Staff` at the circulation desk.
- Staff scan the barcode or enter ISBN to mark the book returned.
- Late returns automatically calculate overdue fees.
- The system records the return date in loan history.

---

### 6. Reserve a Book

> As a `Member`,
> I want to reserve a book that is currently on loan,
> so that I can borrow it when it is returned.
> Acceptance Criteria:

- Reservation option is available only if book status is `On Loan`.
- I can view my position in the reservation queue.
- When available, the first person in queue is notified.
- The book is held for 3 days before passing to the next person.
- I cannot reserve a book I already have on loan.
- The system prevents duplicate reservations for the same book by the same member.

---

### 7. Cancel a Reservation

> As a `Member`,
> I want to cancel my reservation for a book,
> so that I can free the spot for other members.
> Acceptance Criteria:

- The “My Reservations” page lists all my active reservations.
- Each reservation has a “Cancel” button.
- Cancelled reservations remove me from the queue.
- The next member in the queue moves up in position.

---

### 8. View Loan History

> As a `Member`,
> I want to view my current and past loans,
> so that I can track my borrowing history.
> Acceptance Criteria:

- “My Loans” page lists all books currently borrowed with due dates.
- “Loan History” shows all returned books with borrow/return dates.
- Overdue loans are flagged in red.
- History is sorted by most recent first.

---

### 9. View and Pay Fines

> As a `Member`,
> I want to view and pay my fines online,
> so that I can clear my dues without visiting the library.
> Acceptance Criteria:

- The “My Account” page lists outstanding fines and reasons.
- Fines can be paid via supported payment methods.
- Payment confirmation updates my account immediately.
- Receipts appear as on-screen confirmation message.

---

### 10. Receive Notifications

> As a `Member`,
> I want to know and keep track of due dates, overdue books, and reservation availability,
> so that I can manage my loans on time.
> Acceptance Criteria:

- Notifications will be on-screen.
- Reminders appear 2 days before due date. (on Member Dashboard)
- Overdue notifications persist until return. (on Member Dashboard)
- Reservation availability when the book is ready for pickup. (on Member Dashboard)

---

## Role: Staff

### 11. Check Out a Book for a Member

> As a `Staff` member,
> I want to check out a book for a member,
> so that they can borrow it without self-checkout.
> Acceptance Criteria:

- I can search for a member by ID.
- I can scan the book barcode or enter ISBN.
- The system enforces borrowing rules (good standing, loan limits).
- Confirmation is provided for both staff and member.

---

### 12. Process a Book Return

> As a `Staff` member,
> I want to process a book return,
> so that the book becomes available for others.
> Acceptance Criteria:

- I can find the loan record by member ID or ISBN.
- Late returns automatically calculate overdue fees.
- Returned books update status to `Available`.
- Return date is recorded in loan history.

---

### 13. Mark a Book as Lost or Damaged

> As a `Staff` member,
> I want to mark a book as lost or damaged,
> so that the system updates inventory and charges applicable fees.
> Acceptance Criteria:

- I can update a book’s status to `Lost` or `Damaged`.
- Applicable fees are calculated based on replacement cost.
- The system records reason and staff ID.
- Lost/damaged books are excluded from borrowing and reservations.

---

## Role: Librarian

### 14. Add a New Book

> As a `Librarian`,
> I want to add a new book to the collection,
> so that the library inventory stays up-to-date.
> Acceptance Criteria:

- I can enter title, author, ISBN, publication date, category, and location.
- ISBN format is validated.
- New book record defaults to status `Available`.

---

### 15. Edit Book Details

> As a `Librarian`,
> I want to edit existing book details,
> so that errors or outdated information can be corrected.
> Acceptance Criteria:

- I can search and select a book to edit.
- Editable fields include title, author, category, location, and publication year.
- Changes are saved and reflected immediately.

---

### 16. Remove a Book

> As a `Librarian`,
> I want to remove a book from the catalog,
> so that unavailable or outdated items are not shown to members.
> Acceptance Criteria:

- I can mark a book as `Archived` or `Removed`.
- Removed books are excluded from search results.
- Removal reason is stored in the system.

---

### 17. Manage Categories and Genres

> As a `Librarian`,
> I want to manage book categories and genres,
> so that books are organized for easier discovery.
> Acceptance Criteria:

- I can add, edit, or delete categories/genres.
- Updated categories appear in search filters.
- Deleting a category reassigns affected books to “Uncategorized.”

---

## Role: Admin

### 18. Manage User Roles

> As an `Admin`,
> I want to change a user’s role,
> so that I can grant or revoke permissions.
> Acceptance Criteria:

- I can view all users and their roles.
- I can change a role to `MEMBER`, `STAFF`, `LIBRARIAN`, or `ADMIN`.
- My own admin role cannot be downgraded.
- Changes are saved to the database.

---

### 19. Deactivate or Reactivate User Accounts

> As an `Admin`,
> I want to deactivate or reactivate user accounts,
> so that I can control system access.
> Acceptance Criteria:

- I can view account status (Active/Inactive).
- Deactivated accounts cannot log in.
- Reactivation restores previous role and permissions.
- Actions are logged with admin ID.

---

### 20. View System Activity Logs

> As an `Admin`,
> I want to view system activity logs,
> so that I can monitor and audit operations.
> Acceptance Criteria:

- Logs record actions like borrowing, returning, adding books, role changes.
- Logs display date/time, user ID, and action performed.
- Logs can be filtered by date range or action type.
