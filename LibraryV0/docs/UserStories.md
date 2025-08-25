## Role: Public User (no auth required, authenticated Member and Librarian inclusive)

### 1. Search for a Book

> As a `Public User`,
> I want to search for a book by title, author, or ISBN,
> so that I can find the book I’m interested in.

Acceptance Criteria:

- Search bar is visible on the main page.
- Keywords or ISBN can be entered.
- Matching books are displayed with title, author, and availability.
- “No books found” message appears if nothing matches.
- Results are sortable by title, author, or year.

---

### 2. View Book Details

> As a `Public User`,
> I want to view full details of a book,
> so that I can decide whether to become a reader to borrow or reserve it.

Acceptance Criteria:

- Clicking a search result opens the book details page.
- Details include title, author, ISBN, synopsis, publication year, category, and availability.
- Shelf location is shown if available.
- Expected return date is shown if on loan.

### 3. Register as Member

> As a `Public User`,
> I want to register myself as a member of the library,
> so that I can borrow the books and materials from the library.

Acceptance Criteria:

- On-screen confirmation shows success of account creation awaiting Librarian confirmation.
- User details and credentials are stored (Password is hashed).
- Fines, reservations, loans are not created.
- .

---

## Role: Member (authenticated Member with valid JWT and authorized for Member only)

### 3. Borrow a Book

> As a `Member`,
> I want to borrow an available book,
> so that I can read it.

Acceptance Criteria:

- Can borrow only if account is in good standing (no overdue books, fines ≤ \$10).
- Book status updates to `On Loan`.
- Due date is automatically set (14 days from checkout).
- On-screen confirmation shows due date and book details.

---

### 4. Renew a Book

> As a `Member`,
> I want to renew a book I currently have on loan,
> so that I can extend the borrowing period.

Acceptance Criteria:

- Renewal is available only for books not overdue and not reserved by others.
- Max 2 renewals per book.
- Due date extends by standard loan period.
- Confirmation appears on screen.
- Renewal prevented if conditions are not met.

---

### 5. Return a Book

> As a `Member`,
> I want to return a borrowed book,
> so that my loan record is updated and book becomes available.

Acceptance Criteria:

- Return is processed via Librarian interface.
- Late returns calculate overdue fees automatically.
- Return date is recorded in loan history.

---

### 6. Reserve a Book

> As a `Member`,
> I want to reserve a book currently on loan,
> so that I can borrow it when available.

Acceptance Criteria:

- Reservation available only if book status is `On Loan`.
- Can see position in reservation queue.
- Notification appears when book is ready for pickup.
- Book held for 3 days before moving to next member.
- Cannot reserve a book already borrowed by self.

---

### 7. Cancel a Reservation

> As a `Member`,
> I want to cancel my reservation,
> so that others can have access.

Acceptance Criteria:

- “My Reservations” page lists active reservations.
- Each reservation has a cancel option.
- Cancellation updates queue position for others.

---

### 8. View Loan History

> As a `Member`,
> I want to view my current and past loans,
> so that I can track my borrowing history.

Acceptance Criteria:

- “My Loans” lists all borrowed books with due dates.
- Returned books shown with borrow/return dates.
- Overdue loans flagged in red.
- Sorted by most recent first.

---

### 9. View Fines

> As a `Member`,
> I want to view my fines,
> so that I can clear dues with the Librarian.

Acceptance Criteria:

- Outstanding fines are visible on “My Account” page.
- Fine reason and amount displayed.
- Payment handled offline at library desk (MVP).

---

### 10. Receive Notifications (Scoped out due to constraints)

> As a `Member`,
> I want on-screen notifications for due dates, overdue books, and reservations,
> so that I can manage loans on time.

Acceptance Criteria:

- Notifications appear on Member Dashboard.
- Reminders show 2 days before due date.
- Overdue notifications persist until book is returned.
- Reservation-ready notifications appear when a book is available.

---

## Role: Librarian (authenticated Librarian with valid JWT and authorized for all Members with all Member privileges too)

### 11. Add a New Book

> As a `Librarian`,
> I want to add a new book to the collection,
> so that inventory stays up-to-date.

Acceptance Criteria:

- Can enter title, author, ISBN, publication year, category, material type, and location.
- ISBN format validated.
- New book defaults to `Available` status.

---

### 12. Edit Book Details

> As a `Librarian`,
> I want to edit book details,
> so that information remains accurate.

Acceptance Criteria:

- Search and select book to edit.
- Editable fields: title, author, ISBN, category, publication year, material type, location.
- Changes saved immediately.

---

### 13. Remove a Book

> As a `Librarian`,
> I want to remove a book,
> so that outdated or unavailable items are not displayed.

Acceptance Criteria:

- Book marked as `Archived`.
- Removed books excluded from search results.
- Reason for removal stored internally.

---

### 15. Manage Borrowing & Returns

> As a `Librarian`,
> I want to process borrowing, returns, and reservations for members,
> so that lending rules are enforced.

Acceptance Criteria:

- Can process borrow and return for any member.
- Overdue fines calculated automatically.
- Renewals handled according to rules.
- Reservation queues managed properly.

---

### 16. Manage Members

> As a `Librarian`,
> I want to create new members, review all members' information, update details of public users' application after confirmation, and delete dormant accounts,
> so that the library can manage its users.

Acceptance Criteria:

- Can process detail change for any member.
- Can set account.status to active for any new member applications from public users.
- Users.UpdatedAt is set to now each time a change is made.

---
