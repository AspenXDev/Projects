For All APIs in the WBS, all CRUD operations shall be detailed here.

# 2.1 Auth API

User login with email/password
Issue and verify JWT
Register new users (members/staff/librarian/admin)
Password reset flow
Role management (Member, Staff, Admin)

| Method | Endpoint               | Description                 |
| ------ | ---------------------- | --------------------------- |
| POST   | `/auth/register`       | Register a new user         |
| POST   | `/auth/login`          | Login and get token         |
| POST   | `/auth/logout`         | Logout (token invalidation) |
| POST   | `/auth/refresh`        | Refresh auth token          |
| POST   | `/auth/reset-password` | Request password reset      |

# 2.2 Member API

View and update member info (name, email, phone, address)
View loan history and current loans
Manage reservations, fines, and notifications
| Method | Endpoint | Description |
| ------ | ----------------------------------- | ------------------------- |
| GET | `/members/{member_id}` | Get member profile |
| PUT | `/members/{member_id}` | Update member info |
| GET | `/members/{member_id}/loans` | Get member’s loans |
| GET | `/members/{member_id}/reservations` | Get member’s reservations |

# 2.3 Book API

Add/edit/delete books (staff/admin only)
Search and list books by title, author, genre, availability
View detailed book info
Track physical copies if needed (change location etc)

| Method | Endpoint           | Description          |
| ------ | ------------------ | -------------------- |
| GET    | `/books`           | Search or list books |
| GET    | `/books/{book_id}` | Get book details     |
| POST   | `/books`           | Add a new book       |
| PUT    | `/books/{book_id}` | Update book info     |
| DELETE | `/books/{book_id}` | Remove a book        |

# 2.4 Loan Management API

Create new loans when members borrow books
Update loan records when books are returned or renewed
Retrieve loan history and status for members and staff
Identify overdue loans and calculate late fees
Support cancellation or extension of loans (renewals)
Possibly integrate with notifications for reminders

| HTTP Method | Endpoint                  | Description                             |
| ----------- | ------------------------- | --------------------------------------- |
| POST        | `/loans`                  | Create a new loan (borrow book)         |
| GET         | `/loans/{loan_id}`        | Get details of a specific loan          |
| GET         | `/loans?member_id=...`    | List loans for a particular member      |
| GET         | `/loans?status=overdue`   | List overdue loans                      |
| PUT / PATCH | `/loans/{loan_id}/return` | Mark a loan as returned                 |
| PUT / PATCH | `/loans/{loan_id}/renew`  | Renew or extend the due date            |
| DELETE      | `/loans/{loan_id}`        | Cancel a loan (rare case but possible?) |

# 2.5 Genre & Classification API

CRUD genres or categories
Assign books to genres
Possibly manage sub-genres or classification schemes (e.g., Dewey Decimal)
| Method | Endpoint | Description |
| ------ | -------------------- | ----------------- |
| GET | `/genres` | List all genres |
| GET | `/genres/{genre_id}` | Get genre details |
| POST | `/genres` | Add a new genre |
| PUT | `/genres/{genre_id}` | Update genre info |
| DELETE | `/genres/{genre_id}` | Delete a genre |

# 2.6 Notifications API

| Column          | Type          | Description                 |
| --------------- | ------------- | --------------------------- |
| notification_id | INT PK        | Unique notification ID      |
| user_id         | INT FK        | Recipient user              |
| message         | TEXT          | Notification content        |
| type            | VARCHAR(50)   | Reminder, alert, info, etc. |
| status          | VARCHAR(20)   | Sent, read, dismissed       |
| created_at      | DATETIME      | When created                |
| sent_at         | DATETIME NULL | When sent                   |
