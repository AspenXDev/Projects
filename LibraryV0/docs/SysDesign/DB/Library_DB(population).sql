-- =================================
-- Data Population for Library_DB (Revamped)
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
-- BOOKS
-- ----------------
INSERT IGNORE INTO books 
(title, author, isbn, published_year, category, total_copies, available_copies, status, location_section, location_shelf, location_row) VALUES

-- Novels (some fully loaned/reserved)
('Of Mice and Men', 'John Steinbeck', '9780140177398', 1937, 'Novel', 3, 0, 'Borrowed', 'Fiction', 1, 1),
('To Kill A Mockingbird', 'Harper Lee', '9780061120084', 1960, 'Novel', 2, 0, 'Borrowed', 'Fiction', 1, 2),
('Pride and Prejudice', 'Jane Austen', '9780141439518', 1813, 'Novel', 5, 5, 'Available', 'Fiction', 1, 3),
('Great Expectations', 'Charles Dickens', '9780141439563', 1861, 'Novel', 4, 2, 'Borrowed', 'Fiction', 1, 4),
('Moby-Dick', 'Herman Melville', '9780142437247', 1851, 'Novel', 3, 0, 'Reserved', 'Fiction', 1, 5),
('Jane Eyre', 'Charlotte Bronte', '9780142437209', 1847, 'Novel', 3, 0, 'Borrowed', 'Fiction', 1, 6),
('Wuthering Heights', 'Emily Bronte', '9780141439556', 1847, 'Novel', 3, 1, 'Borrowed', 'Fiction', 2, 1),
('1984', 'George Orwell', '9780451524935', 1949, 'Novel', 6, 0, 'Borrowed', 'Fiction', 2, 2),
('Animal Farm', 'George Orwell', '9780451526342', 1945, 'Novel', 5, 0, 'Borrowed', 'Fiction', 2, 3),
('Frankenstein', 'Mary Shelley', '9780141439471', 1818, 'Novel', 2, 1, 'Borrowed', 'Fiction', 2, 4),
('The Catcher in the Rye', 'J.D. Salinger',  '9780316769488', 1951, 'Novel', 3, 2, 'Borrowed', 'Fiction', 2, 5),
('Lord of the Flies', 'William Golding', '9780399501487', 1954, 'Novel', 4, 4, 'Available', 'Fiction', 2, 6),

-- Magazines (some reserved)
('National Geographic - January 2025', 'National Geographic Society', '978NG2025010', 2025, 'Magazine', 3, 0, 'Reserved', 'Magazines', 1, 1),
('National Geographic - February 2025', 'National Geographic Society', '978NG2025020', 2025, 'Magazine', 3, 2, 'Borrowed', 'Magazines', 1, 2),
('National Geographic - March 2025', 'National Geographic Society', '978NG2025030', 2025, 'Magazine', 3, 3, 'Available', 'Magazines', 1, 3),
('Time - July 2025', 'Time Magazine', '978TIME202507', 2025, 'Magazine', 3, 0, 'Borrowed', 'Magazines', 1, 4),
('The Economist - August 2025', 'The Economist', '978ECON202508', 2025, 'Magazine', 3, 1, 'Borrowed', 'Magazines', 1, 5),

-- Education / Reference / Lifestyle
('Python Programming 101', 'Guido van Rossum', '978PY2021001', 2021, 'Education', 4, 2, 'Borrowed', 'Education', 2, 1),
('Data Structures & Algorithms', 'Robert Lafore', '978DSA2021001', 2019, 'Education', 3, 1, 'Borrowed', 'Education', 2, 2),
('Java Programming Basics', 'James Gosling', '978JAVA2023001', 2023, 'Education', 4, 4, 'Available', 'Education', 2, 3),
('Encyclopedia Britannica - 2020 Edition', 'Encyclopedia Britannica', '978EB2020001', 2020, 'Reference', 2, 0, 'Reserved', 'Reference', 1, 1),
('Cooking Made Easy', 'Jamie Oliver', '978COOK2020001', 2020, 'Cooking', 5, 0, 'Reserved', 'Lifestyle', 1, 1),
('Gardening for Beginners', 'Alan Titchmarsh', '978GARD2021001', 2021, 'Lifestyle', 4, 1, 'Borrowed', 'Lifestyle', 1, 2),
('Yoga for Beginners', 'Jane Doe', '978YOGA2023001', 2023, 'Lifestyle', 5, 5, 'Available', 'Lifestyle', 1, 3);

-- ----------------
-- LOANS
-- ----------------
-- Newer loans, mix of active and returned
INSERT INTO loans (member_id, book_id, loan_date, due_date, return_date, renew_count, status)
VALUES
(1, 8, '2025-08-01', '2025-08-15', NULL, 0, 'Active'),
(2, 6, '2025-08-05', '2025-08-19', NULL, 1, 'Active'),
(3, 9, '2025-08-10', '2025-08-24', NULL, 0, 'Active'),
(1, 4, '2025-07-01', '2025-07-15', '2025-07-14', 0, 'Returned'),
(2, 3, '2025-06-20', '2025-07-04', '2025-07-02', 0, 'Returned');

-- ----------------
-- RESERVATIONS
-- ----------------
-- Mix of Waiting, Collected, Cancelled
INSERT INTO reservations (member_id, book_id, reservation_date, hold_until, status)
VALUES
(1, 5, '2025-08-20 10:00:00', '2025-08-23 23:59:59', 'Waiting'),
(3, 18, '2025-08-15 09:00:00', '2025-08-18 23:59:59', 'Collected'),
(2, 15, '2025-08-12 14:00:00', '2025-08-15 23:59:59', 'Cancelled'),
(2, 1, '2025-08-25 11:00:00', '2025-08-28 23:59:59', 'Waiting'),
(3, 2, '2025-08-26 10:00:00', '2025-08-29 23:59:59', 'Waiting');

-- ----------------
-- FINES
-- ----------------
INSERT INTO fines (loan_id, amount, paid, created_at, updated_at)
VALUES
(4, 0.50, TRUE, '2025-07-15 10:00:00', '2025-07-16 09:00:00'),
(5, 1.00, TRUE, '2025-07-04 10:00:00', '2025-07-05 09:00:00');
