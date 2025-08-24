-- =================================
-- Data Population for Library_DB
-- =================================

USE Library_DB;

-- ----------------
-- USERS (Members + Librarians)
-- ----------------
INSERT IGNORE INTO users (username, email, password_hash, role_id) VALUES
-- Members (role_id = 1)
('victor_chan', 'victor@example.com', '$2a$10$IKwTZknt0V8nxR9auxjkxubAlwcXXvK7pO2XqR6EjxQHAzjTaHomK', 1),
('alice_walker', 'alice@example.com', '$2a$10$IKwTZknt0V8nxR9auxjkxubAlwcXXvK7pO2XqR6EjxQHAzjTaHomK', 1),
('bob_smith', 'bob@example.com', '$2a$10$IKwTZknt0V8nxR9auxjkxubAlwcXXvK7pO2XqR6EjxQHAzjTaHomK', 1),

-- Librarians (role_id = 2)
('librarian_john', 'john@example.com', '$2a$10$IKwTZknt0V8nxR9auxjkxubAlwcXXvK7pO2XqR6EjxQHAzjTaHomK', 2),
('librarian_mary', 'mary@example.com', '$2a$10$IKwTZknt0V8nxR9auxjkxubAlwcXXvK7pO2XqR6EjxQHAzjTaHomK', 2);

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
-- ----------------
-- BOOKS SEEDING
-- ----------------

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





-- ----------------
-- LOANS
-- ----------------
INSERT IGNORE INTO loans (member_id, book_id, loan_date, due_date, return_date, renew_count, status) VALUES
(1, 1, '2025-08-01', '2025-08-31', NULL, 1, 'Active');

-- ----------------
-- RESERVATIONS
-- ----------------
INSERT IGNORE INTO reservations (member_id, book_id, reservation_date, status) VALUES
(1, 2, '2025-08-22 10:00:00', 'Waiting'),
(2, 2, '2025-08-23 09:00:00', 'Waiting');

-- ----------------
-- FINES
-- ----------------
-- No fines yet; ready for future inserts
