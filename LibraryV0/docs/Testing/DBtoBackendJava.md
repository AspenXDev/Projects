# Junit Test

## \src\test\java\com\library\lms\test\TestMemberAllDetails.java

to fetch all details (admin+active loans + reservations + fines)
for display on member-dashboard
for borrow book", "renew book", "return book", "pay fine" features
(require Librarian confimation )

### Test 1 with username=victor_chan

#### Seeded Data in SQL

-- =================================
-- Data Population for Library_DB
-- =================================

USE Library_DB;

---

-- USERS (Members + Librarians)

---

INSERT IGNORE INTO users (username, email, password_hash, role_id) VALUES
-- Members (role_id = 1)
('victor_chan', 'victor@example.com', '$2a$10$IKwTZknt0V8nxR9auxjkxubAlwcXXvK7pO2XqR6EjxQHAzjTaHomK', 1),
('alice_walker', 'alice@example.com', '$2a$10$IKwTZknt0V8nxR9auxjkxubAlwcXXvK7pO2XqR6EjxQHAzjTaHomK', 1),
('bob_smith', 'bob@example.com', '$2a$10$IKwTZknt0V8nxR9auxjkxubAlwcXXvK7pO2XqR6EjxQHAzjTaHomK', 1),

-- Librarians (role_id = 2)
('librarian_john', 'john@example.com', '$2a$10$IKwTZknt0V8nxR9auxjkxubAlwcXXvK7pO2XqR6EjxQHAzjTaHomK', 2),
('librarian_mary', 'mary@example.com', '$2a$10$IKwTZknt0V8nxR9auxjkxubAlwcXXvK7pO2XqR6EjxQHAzjTaHomK', 2);

---

-- MEMBERS

---

INSERT IGNORE INTO members (user_id, full_name, registration_date, membership_valid_until, membership_status) VALUES
(1, 'Victor Chan', '2024-01-01', '2025-12-31', 'Active'),
(2, 'Alice Walker', '2023-06-15', '2024-12-31', 'Active'),
(3, 'Bob Smith', '2024-03-10', '2025-03-10', 'Expired');

---

-- LIBRARIANS

---

INSERT IGNORE INTO librarians (user_id, full_name) VALUES
(4, 'John Wick'),
(5, 'Mary Johnson');

---

-- BOOKS (novels)

---

---

-- BOOKS SEEDING

---

INSERT IGNORE INTO books
(title, author, isbn, published_year, category, total_copies, available_copies, status, location_section, location_shelf, location_row) VALUES

-- Novels
('Of Mice and Men', 'John Steinbeck', '9780140177398', 1937, 'Novel', 3, 2, 'Borrowed', 'Fiction', 1, 1),
('To Kill A Mockingbird', 'Harper Lee', '9780061120084', 1960, 'Novel', 2, 1, 'Reserved', 'Fiction', 1, 2),
('Pride and Prejudice', 'Jane Austen', '9780141439518', 1813, 'Novel', 5, 5, 'Available', 'Fiction', 1, 3),
('Great Expectations', 'Charles Dickens', '9780141439563', 1861, 'Novel', 4, 4, 'Available', 'Fiction', 1, 4),
('Moby-Dick', 'Herman Melville', '9780142437247', 1851, 'Novel', 3, 3, 'Available', 'Fiction', 1, 5),
('Jane Eyre', 'Charlotte Bronte', '9780142437209', 1847, 'Novel', 3, 3, 'Available', 'Fiction', 1, 6),
('Wuthering Heights', 'Emily Bronte', '9780141439556', 1847, 'Novel', 3, 3, 'Available', 'Fiction', 2, 1),
('1984', 'George Orwell', '9780451524935', 1949, 'Novel', 6, 6, 'Available', 'Fiction', 2, 2),
('Animal Farm', 'George Orwell', '9780451526342', 1945, 'Novel', 5, 5, 'Available', 'Fiction', 2, 3),
('Frankenstein', 'Mary Shelley', '9780141439471', 1818, 'Novel', 2, 2, 'Available', 'Fiction', 2, 4),

-- Magazines
('National Geographic - January 2021', 'National Geographic Society', '978NG2021010', 2021, 'Magazine', 3, 3, 'Available', 'Magazines', 1, 1),
('National Geographic - February 2021', 'National Geographic Society', '978NG2021020', 2021, 'Magazine', 3, 3, 'Available', 'Magazines', 1, 2),
('National Geographic - March 2021', 'National Geographic Society', '978NG2021030', 2021, 'Magazine', 3, 3, 'Available', 'Magazines', 1, 3),
('National Geographic - April 2022', 'National Geographic Society', '978NG2022040', 2022, 'Magazine', 3, 3, 'Available', 'Magazines', 1, 4),
('National Geographic - May 2022', 'National Geographic Society', '978NG2022050', 2022, 'Magazine', 3, 3, 'Available', 'Magazines', 1, 5),
('National Geographic - June 2023', 'National Geographic Society', '978NG2023060', 2023, 'Magazine', 3, 3, 'Available', 'Magazines', 1, 6),

-- Reference / Misc
('Encyclopedia Britannica - 2020 Edition', 'Encyclopedia Britannica', '978EB2020001', 2020, 'Reference', 2, 2, 'Available', 'Reference', 1, 1),
('Python Programming 101', 'Guido van Rossum', '978PY2021001', 2021, 'Education', 4, 4, 'Available', 'Education', 2, 1),
('Data Structures & Algorithms', 'Robert Lafore', '978DSA2021001', 2019, 'Education', 3, 3, 'Available', 'Education', 2, 2),
('Cooking Made Easy', 'Jamie Oliver', '978COOK2020001', 2020, 'Cooking', 5, 5, 'Available', 'Lifestyle', 1, 1),
('Gardening for Beginners', 'Alan Titchmarsh', '978GARD2021001', 2021, 'Lifestyle', 4, 4, 'Available', 'Lifestyle', 1, 2);

---

-- LOANS

---

INSERT IGNORE INTO loans (member_id, book_id, loan_date, due_date, return_date, renew_count, status) VALUES
(1, 1, '2025-08-01', '2025-08-31', NULL, 1, 'Active');

---

-- RESERVATIONS

---

INSERT IGNORE INTO reservations (member_id, book_id, reservation_date, status) VALUES
(1, 2, '2025-08-22 10:00:00', 'Waiting'),
(2, 2, '2025-08-23 09:00:00', 'Waiting');

---

-- FINES

---

-- No fines yet;

#### Eclipse Console Output

2025-08-26 15:38:07.819 [main] INFO c.l.lms.test.TestMemberAllDetails - Started TestMemberAllDetails in 6.49 seconds (process running for 7.754)
2025-08-26 15:38:07.929 [main] DEBUG o.s.b.a.ApplicationAvailabilityBean - Application availability state LivenessState changed to CORRECT
2025-08-26 15:38:07.931 [main] DEBUG o.s.b.a.ApplicationAvailabilityBean - Application availability state ReadinessState changed to ACCEPTING_TRAFFIC
WARNING: A Java agent has been loaded dynamically (C:\Users\hitor\.m2\repository\net\bytebuddy\byte-buddy-agent\1.14.19\byte-buddy-agent-1.14.19.jar)
WARNING: If a serviceability tool is in use, please run with -XX:+EnableDynamicAgentLoading to hide this warning
WARNING: If a serviceability tool is not in use, please run with -Djdk.instrument.traceUsage for more information
WARNING: Dynamic loading of agents will be disallowed by default in a future release
OpenJDK 64-Bit Server VM warning: Sharing is only supported for boot loader classes because bootstrap classpath has been appended
2025-08-26 15:38:08.573 [main] DEBUG org.hibernate.SQL -
select
m1_0.member_id,
m1_0.created_at,
m1_0.full_name,
m1_0.membership_status,
m1_0.membership_valid_until,
m1_0.registration_date,
m1_0.updated_at,
m1_0.user_id
from
members m1_0
join
users u1_0
on u1_0.user_id=m1_0.user_id
where
u1_0.username=?
Hibernate:
select
m1_0.member_id,
m1_0.created_at,
m1_0.full_name,
m1_0.membership_status,
m1_0.membership_valid_until,
m1_0.registration_date,
m1_0.updated_at,
m1_0.user_id
from
members m1_0
join
users u1_0
on u1_0.user_id=m1_0.user_id
where
u1_0.username=?
===== MEMBER DATA =====
ID: 1
Full Name: Victor Chan
2025-08-26 15:38:08.611 [main] DEBUG org.hibernate.SQL -
select
u1_0.user_id,
u1_0.email,
u1_0.is_active,
u1_0.password_hash,
u1_0.role_id,
r1_0.role_id,
r1_0.role_name,
u1_0.username
from
users u1_0
join
roles r1_0
on r1_0.role_id=u1_0.role_id
where
u1_0.user_id=?
Hibernate:
select
u1_0.user_id,
u1_0.email,
u1_0.is_active,
u1_0.password_hash,
u1_0.role_id,
r1_0.role_id,
r1_0.role_name,
u1_0.username
from
users u1_0
join
roles r1_0
on r1_0.role_id=u1_0.role_id
where
u1_0.user_id=?
Username: victor_chan
Email: victor@example.com
Registration Date: 2024-01-01
Valid Until: 2025-12-31
Status: Active

=== ACTIVE LOANS ===
2025-08-26 15:38:08.620 [main] DEBUG org.hibernate.SQL -
select
l1_0.member_id,
l1_0.loan_id,
l1_0.book_id,
l1_0.created_at,
l1_0.due_date,
l1_0.loan_date,
l1_0.renew_count,
l1_0.return_date,
l1_0.status,
l1_0.updated_at
from
loans l1_0
where
l1_0.member_id=?
Hibernate:
select
l1_0.member_id,
l1_0.loan_id,
l1_0.book_id,
l1_0.created_at,
l1_0.due_date,
l1_0.loan_date,
l1_0.renew_count,
l1_0.return_date,
l1_0.status,
l1_0.updated_at
from
loans l1_0
where
l1_0.member_id=?
2025-08-26 15:38:08.626 [main] DEBUG org.hibernate.SQL -
select
b1_0.book_id,
b1_0.author,
b1_0.available_copies,
b1_0.category,
b1_0.created_at,
b1_0.isbn,
b1_0.location_row,
b1_0.location_section,
b1_0.location_shelf,
b1_0.published_year,
b1_0.status,
b1_0.title,
b1_0.total_copies,
b1_0.updated_at
from
books b1_0
where
b1_0.book_id=?
Hibernate:
select
b1_0.book_id,
b1_0.author,
b1_0.available_copies,
b1_0.category,
b1_0.created_at,
b1_0.isbn,
b1_0.location_row,
b1_0.location_section,
b1_0.location_shelf,
b1_0.published_year,
b1_0.status,
b1_0.title,
b1_0.total_copies,
b1_0.updated_at
from
books b1_0
where
b1_0.book_id=?

- LoanID: 1, Book: Of Mice and Men, Due: 2025-08-31, Status: Active

=== RESERVATIONS ===
2025-08-26 15:38:08.633 [main] DEBUG org.hibernate.SQL -
select
r1_0.member_id,
r1_0.reservation_id,
r1_0.book_id,
b1_0.book_id,
b1_0.author,
b1_0.available_copies,
b1_0.category,
b1_0.created_at,
b1_0.isbn,
b1_0.location_row,
b1_0.location_section,
b1_0.location_shelf,
b1_0.published_year,
b1_0.status,
b1_0.title,
b1_0.total_copies,
b1_0.updated_at,
r1_0.created_at,
r1_0.hold_until,
r1_0.reservation_date,
r1_0.status,
r1_0.updated_at
from
reservations r1_0
left join
books b1_0
on b1_0.book_id=r1_0.book_id
where
r1_0.member_id=?
Hibernate:
select
r1_0.member_id,
r1_0.reservation_id,
r1_0.book_id,
b1_0.book_id,
b1_0.author,
b1_0.available_copies,
b1_0.category,
b1_0.created_at,
b1_0.isbn,
b1_0.location_row,
b1_0.location_section,
b1_0.location_shelf,
b1_0.published_year,
b1_0.status,
b1_0.title,
b1_0.total_copies,
b1_0.updated_at,
r1_0.created_at,
r1_0.hold_until,
r1_0.reservation_date,
r1_0.status,
r1_0.updated_at
from
reservations r1_0
left join
books b1_0
on b1_0.book_id=r1_0.book_id
where
r1_0.member_id=?

- ReservationID: 1, Book: To Kill A Mockingbird, Date: 2025-08-22T18:00, Status: Waiting

=== FINES (via Loans) ===
2025-08-26 15:38:08.637 [main] DEBUG org.hibernate.SQL -
select
f1_0.loan_id,
f1_0.fine_id,
f1_0.amount,
f1_0.created_at,
f1_0.paid,
f1_0.updated_at
from
fines f1_0
where
f1_0.loan_id=?
Hibernate:
select
f1_0.loan_id,
f1_0.fine_id,
f1_0.amount,
f1_0.created_at,
f1_0.paid,
f1_0.updated_at
from
fines f1_0
where
f1_0.loan_id=?
Fines: None
2025-08-26 15:38:08.678 [SpringApplicationShutdownHook] INFO o.s.o.j.LocalContainerEntityManagerFactoryBean - Closing JPA EntityManagerFactory for persistence unit 'default'
2025-08-26 15:38:08.681 [SpringApplicationShutdownHook] INFO com.zaxxer.hikari.HikariDataSource - HikariPool-1 - Shutdown initiated...
2025-08-26 15:38:08.695 [SpringApplicationShutdownHook] INFO com.zaxxer.hikari.HikariDataSource - HikariPool-1 - Shutdown completed.

#### In JSON Format

{
"member": {
"id": 1,
"full_name": "Victor Chan",
"username": "victor_chan",
"email": "victor@example.com",
"registration_date": "2024-01-01",
"membership_valid_until": "2025-12-31",
"status": "Active"
},
"active_loans": [
{
"loan_id": 1,
"book_title": "Of Mice and Men",
"due_date": "2025-08-31",
"status": "Active"
}
],
"reservations": [
{
"reservation_id": 1,
"book_title": "To Kill A Mockingbird",
"date": "2025-08-22T18:00",
"status": "Waiting"
}
],
"fines": []
}

##### BackendJava matches seeded DB for username=victor_chan
