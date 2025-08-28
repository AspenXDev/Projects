-- =================================
-- Data Population for Library_DB
-- =================================

USE Library_DB;

-- ----------------
-- USERS (Members + Librarians)
-- ----------------
INSERT IGNORE INTO users (username, email, password_hash, role_id) VALUES
-- Members (role_id = 1)
('victor_chan', 'victor@example.com', '$2a$10$zLfEVSOd6iqs7O7t7F2S4.GBgYnbGU9Ony8MvQYiA1nt3sScXMDQK', 1),
('alice_walker', 'alice@example.com', '$2a$10$zLfEVSOd6iqs7O7t7F2S4.GBgYnbGU9Ony8MvQYiA1nt3sScXMDQK', 1),
('bob_smith', 'bob@example.com', '$2a$10$zLfEVSOd6iqs7O7t7F2S4.GBgYnbGU9Ony8MvQYiA1nt3sScXMDQK', 1),

-- Librarians (role_id = 2)
('librarian_john', 'john@example.com', '$2a$10$zLfEVSOd6iqs7O7t7F2S4.GBgYnbGU9Ony8MvQYiA1nt3sScXMDQK', 2),
('librarian_mary', 'mary@example.com', '$2a$10$zLfEVSOd6iqs7O7t7F2S4.GBgYnbGU9Ony8MvQYiA1nt3sScXMDQK', 2);

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
-- BOOKS (Novels, Magazines, Education, Lifestyle, Reference)
-- ----------------
INSERT IGNORE INTO books 
(title, author, isbn, published_year, category, total_copies, available_copies, status, location_section, location_shelf, location_row) VALUES

-- Novels
('Of Mice and Men', 'John Steinbeck', '9780140177398', 1937, 'Novel', 3, 2, 'Borrowed', 'Fiction', 1, 1),
('To Kill A Mockingbird', 'Harper Lee', '9780061120084', 1960, 'Novel', 2, 1, 'Reserved', 'Fiction', 1, 2),
('Pride and Prejudice', 'Jane Austen', '9780141439518', 1813, 'Novel', 5, 5, 'Available', 'Fiction', 1, 3),
('Great Expectations', 'Charles Dickens', '9780141439563', 1861, 'Novel', 4, 4, 'Available', 'Fiction', 1, 4),
('Moby-Dick', 'Herman Melville', '9780142437247', 1851, 'Novel', 3, 3, 'Available', 'Fiction', 1, 5),
('Jane Eyre', 'Charlotte Bronte', '9780142437209', 1847, 'Novel', 3, 2, 'Borrowed', 'Fiction', 1, 6),
('Wuthering Heights', 'Emily Bronte', '9780141439556', 1847, 'Novel', 3, 3, 'Available', 'Fiction', 2, 1),
('1984', 'George Orwell', '9780451524935', 1949, 'Novel', 6, 5, 'Borrowed', 'Fiction', 2, 2),
('Animal Farm', 'George Orwell', '9780451526342', 1945, 'Novel', 5, 4, 'Borrowed', 'Fiction', 2, 3),
('Frankenstein', 'Mary Shelley', '9780141439471', 1818, 'Novel', 2, 2, 'Available', 'Fiction', 2, 4),
('The Catcher in the Rye', 'J.D. Salinger',  '9780316769488', 1951, 'Novel', 3, 3, 'Available', 'Fiction', 2, 5),
('Lord of the Flies', 'William Golding', '9780399501487', 1954, 'Novel', 4, 4, 'Available', 'Fiction', 2, 6),

-- Magazines
('National Geographic - January 2021', 'National Geographic Society', '978NG2021010', 2021, 'Magazine', 3, 3, 'Available', 'Magazines', 1, 1),
('National Geographic - February 2021', 'National Geographic Society', '978NG2021020', 2021, 'Magazine', 3, 3, 'Available', 'Magazines', 1, 2),
('National Geographic - March 2021', 'National Geographic Society', '978NG2021030', 2021, 'Magazine', 3, 3, 'Available', 'Magazines', 1, 3),
('National Geographic - April 2022', 'National Geographic Society', '978NG2022040', 2022, 'Magazine', 3, 3, 'Available', 'Magazines', 1, 4),
('National Geographic - May 2022', 'National Geographic Society', '978NG2022050', 2022, 'Magazine', 3, 3, 'Available', 'Magazines', 1, 5),
('National Geographic - June 2023', 'National Geographic Society', '978NG2023060', 2023, 'Magazine', 3, 3, 'Available', 'Magazines', 1, 6),
('Time - July 2023', 'Time Magazine', '978TIME202307', 2023, 'Magazine', 3, 3, 'Available', 'Magazines', 1, 7),
('The Economist - August 2023', 'The Economist', '978ECON202308', 2023, 'Magazine', 3, 3, 'Available', 'Magazines', 1, 8),

-- Education / Reference / Lifestyle
('Python Programming 101', 'Guido van Rossum', '978PY2021001', 2021, 'Education', 4, 4, 'Available', 'Education', 2, 1),
('Data Structures & Algorithms', 'Robert Lafore', '978DSA2021001', 2019, 'Education', 3, 3, 'Available', 'Education', 2, 2),
('Java Programming Basics', 'James Gosling', '978JAVA2023001', 2023, 'Education', 4, 4, 'Available', 'Education', 2, 3),
('Encyclopedia Britannica - 2020 Edition', 'Encyclopedia Britannica', '978EB2020001', 2020, 'Reference', 2, 2, 'Available', 'Reference', 1, 1),
('Cooking Made Easy', 'Jamie Oliver', '978COOK2020001', 2020, 'Cooking', 5, 5, 'Available', 'Lifestyle', 1, 1),
('Gardening for Beginners', 'Alan Titchmarsh', '978GARD2021001', 2021, 'Lifestyle', 4, 4, 'Available', 'Lifestyle', 1, 2),
('Yoga for Beginners', 'Jane Doe', '978YOGA2023001', 2023, 'Lifestyle', 5, 5, 'Available', 'Lifestyle', 1, 3);

-- ----------------
-- LOANS
-- ----------------
-- Victor Chan borrowed "1984", returned 2 days late
INSERT INTO loans (member_id, book_id, loan_date, due_date, return_date, renew_count, status)
VALUES (1, 8, '2025-07-01', '2025-07-15', '2025-07-17', 0, 'Returned');

-- Alice Walker borrowed "Jane Eyre", still active
INSERT INTO loans (member_id, book_id, loan_date, due_date, return_date, renew_count, status)
VALUES (2, 6, '2025-08-10', '2025-08-24', NULL, 0, 'Active');

-- Bob Smith borrowed "Animal Farm", still active
INSERT INTO loans (member_id, book_id, loan_date, due_date, return_date, renew_count, status)
VALUES (3, 9, '2025-08-15', '2025-08-29', NULL, 1, 'Active');

-- ----------------
-- RESERVATIONS
-- ----------------
-- Victor Chan reserved "Moby-Dick"
INSERT INTO reservations (member_id, book_id, reservation_date, hold_until, status)
VALUES (1, 5, '2025-08-20 10:00:00', '2025-08-23 23:59:59', 'Waiting');

-- Bob Smith reserved "Cooking Made Easy" = collected
INSERT INTO reservations (member_id, book_id, reservation_date, hold_until, status)
VALUES (3, 18, '2025-08-15 09:00:00', '2025-08-18 23:59:59', 'Collected');

-- Alice Walker reserved "National Geographic - June 2023" = cancelled
INSERT INTO reservations (member_id, book_id, reservation_date, hold_until, status)
VALUES (2, 15, '2025-08-12 14:00:00', '2025-08-15 23:59:59', 'Cancelled');

-- ----------------
-- FINES
-- ----------------
-- Victor Chan returned 2 days late = 2 * 0.50 = $1.00
INSERT INTO fines (loan_id, amount, paid, created_at, updated_at)
VALUES (1, 1.00, TRUE, '2025-07-17 10:00:00', '2025-07-18 09:00:00');

-- Alice Walker late fee 3 days = $1.50
INSERT INTO fines (loan_id, amount, paid, created_at, updated_at)
VALUES (2, 1.50, TRUE, '2025-08-24 10:00:00', '2025-08-25 09:00:00');

-- Bob Smith late fee 10 days = $5.00
INSERT INTO fines (loan_id, amount, paid, created_at, updated_at)
VALUES (3, 5.00, FALSE, '2025-08-29 10:00:00', '2025-08-30 09:00:00');