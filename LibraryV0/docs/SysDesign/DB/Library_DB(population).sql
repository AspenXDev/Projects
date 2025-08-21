USE Library_DB;
-- ================================
-- Data Population for Library_DB
-- ================================

-- ----------------
-- USERS (Members + Librarians)
-- ----------------
INSERT IGNORE INTO users (username, email, password_hash, role_id) VALUES
-- Members (role_id = 1)
('victor_chan', 'victor@example.com', '$2a$10$abcdefghijklmnopqrstuv', 1),
('alice_walker', 'alice@example.com', '$2a$10$abcdefghijklmnopqrstuv', 1),
('bob_smith', 'bob@example.com', '$2a$10$abcdefghijklmnopqrstuv', 1),

-- Librarians (role_id = 2)
('librarian_john', 'john@example.com', '$2a$10$abcdefghijklmnopqrstuv', 2),
('librarian_mary', 'mary@example.com', '$2a$10$abcdefghijklmnopqrstuv', 2);

-- ----------------
-- MEMBERS
-- ----------------
INSERT IGNORE INTO members (user_id, full_name, registration_date, membership_valid_until, membership_status) VALUES
(1, 'Victor Chan', '2024-01-01', '2025-12-31', 'Active'),
(2, 'Alice Walker', '2023-06-15', '2024-12-31', 'Active'),
(3, 'Bob Smith', '2024-03-10', '2025-03-10', 'Expired');

-- ----------------
-- LIBRARIANS
-- ----------------
INSERT IGNORE INTO librarians (user_id, full_name) VALUES
(4, 'John Wick'),
(5, 'Mary Johnson');

-- ----------------
-- BOOKS (novels)
-- ----------------
INSERT IGNORE INTO books (title, author, isbn, published_year, category, total_copies, available_copies, status) VALUES
('Of Mice and Men', 'John Steinbeck', '9780140177398', 1937, 'Novel', 3, 2, 'Borrowed'),
('To Kill A Mockingbird', 'Harper Lee', '9780061120084', 1960, 'Novel', 2, 1, 'Reserved'),
('Pride and Prejudice', 'Jane Austen', '9780141439518', 1813, 'Novel', 5, 5, 'Available'),
('Great Expectations', 'Charles Dickens', '9780141439563', 1861, 'Novel', 4, 4, 'Available'),
('Moby-Dick', 'Herman Melville', '9780142437247', 1851, 'Novel', 3, 3, 'Available'),
('Jane Eyre', 'Charlotte Brontë', '9780142437209', 1847, 'Novel', 3, 3, 'Available'),
('Wuthering Heights', 'Emily Brontë', '9780141439556', 1847, 'Novel', 3, 3, 'Available'),
('1984', 'George Orwell', '9780451524935', 1949, 'Novel', 6, 6, 'Available'),
('Animal Farm', 'George Orwell', '9780451526342', 1945, 'Novel', 5, 5, 'Available'),
('Frankenstein', 'Mary Shelley', '9780141439471', 1818, 'Novel', 2, 2, 'Available');

-- ----------------
-- "Books" (National Geographic Magazines)
-- ----------------
INSERT INTO books (title, author, isbn, published_year, category, total_copies, available_copies, status, location_section, location_shelf, location_row)
VALUES
('National Geographic - January 2021', 'National Geographic Society', 'NG202101', 2021, 'Magazine', 3, 3, 'Available', 'Magazines', 1, 1),
('National Geographic - February 2021', 'National Geographic Society', 'NG202102', 2021, 'Magazine', 3, 3, 'Available', 'Magazines', 1, 2),
('National Geographic - March 2021', 'National Geographic Society', 'NG202103', 2021, 'Magazine', 3, 3, 'Available', 'Magazines', 1, 3),
('National Geographic - April 2022', 'National Geographic Society', 'NG202204', 2022, 'Magazine', 3, 3, 'Available', 'Magazines', 1, 4),
('National Geographic - May 2022', 'National Geographic Society', 'NG202205', 2022, 'Magazine', 3, 3, 'Available', 'Magazines', 1, 5),
('National Geographic - June 2023', 'National Geographic Society', 'NG202306', 2023, 'Magazine', 3, 3, 'Available', 'Magazines', 1, 6);



-- ----------------
-- LOANS
-- ----------------
INSERT IGNORE INTO loans (member_id, book_id, loan_date, due_date, return_date, renew_count, status) VALUES
(1, 1, '2025-08-01', '2025-08-31', NULL, 1, 'Active');

-- ----------------
-- RESERVATIONS
-- ----------------
INSERT IGNORE INTO reservations (member_id, book_id, reservation_date, status) VALUES
(1, 2, '2025-08-15 10:00:00', 'Waiting'),   -- Victor's reservation
(2, 2, '2025-08-10 09:00:00', 'Waiting');   -- Alice is first in queue because date is earlier

-- ----------------
-- FINES (none initially)
-- ----------------
-- No fines yet;