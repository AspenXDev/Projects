-- ===========users=====================
-- MVP Library Management DB Schema
-- ================================
CREATE DATABASE Library_DB;
USE Library_DB;

-- Roles: Only two roles as per BRD/SRS (Member, Librarian)
CREATE TABLE roles (
    role_id INT AUTO_INCREMENT PRIMARY KEY,
    role_name VARCHAR(50) UNIQUE NOT NULL
);

-- Preload rolesrolesrole_idrole_name
INSERT INTO roles (role_name) VALUES ('Member'), ('Librarian');

-- Users: System users linked to roles and for centralization of authentication (DRY and RBAC)
CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(155) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    role_id INT NOT NULL,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (role_id) REFERENCES roles(role_id)
);

-- ----------------
-- MEMBERS
-- ----------------
CREATE TABLE members (
    member_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL UNIQUE,
    full_name VARCHAR(100) NOT NULL,
    registration_date DATE NOT NULL,
    membership_valid_until DATE NOT NULL,
    membership_status ENUM('Active', 'Expired') DEFAULT 'Active',
    -- active_loans INT DEFAULT 0,         -- for future versions. derived, not stored
    -- overdue_items INT DEFAULT 0,        -- for future versions. derived
    -- total_unpaid_fines DECIMAL(5,2)     -- for future versions. derived
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	FOREIGN KEY (user_id) REFERENCES users(user_id)
);

-- ----------------
-- LIBRARIANS
-- ----------------
CREATE TABLE librarians (
    librarian_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL UNIQUE,
    full_name VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	FOREIGN KEY (user_id) REFERENCES users(user_id)
);

-- ----------------
-- BOOKS
-- ----------------
CREATE TABLE books (
    book_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    author VARCHAR(150) NOT NULL,
    isbn CHAR(13) UNIQUE NOT NULL,
    published_year INT,
    category VARCHAR(50),               -- future: make as ENUM
    total_copies INT NOT NULL DEFAULT 1,
    available_copies INT NOT NULL DEFAULT 1,
    location_section VARCHAR(50),       -- for users to physically find books. for Librarians to relocate books.
    location_shelf INT,					-- for users to physically find books. for Librarians to relocate books.
    location_row INT,					-- for users to physically find books. for Librarians to relocate books.
    status ENUM('Available', 'Borrowed','Reserved') DEFAULT 'Available',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- ----------------
-- RESERVATIONS
-- ----------------
CREATE TABLE reservations (
    reservation_id INT AUTO_INCREMENT PRIMARY KEY,
    member_id INT NOT NULL,
    book_id INT NOT NULL,
    reservation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- ensure FIFO
    hold_until TIMESTAMP,                                  -- 3-day hold after book becomes available
    status ENUM('Waiting', 'On Hold', 'Collected', 'Cancelled') DEFAULT 'Waiting',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (member_id) REFERENCES members(member_id),
    FOREIGN KEY (book_id) REFERENCES books(book_id)
    );

-- ----------------
-- LOANS
-- ----------------
CREATE TABLE loans (
    loan_id INT AUTO_INCREMENT PRIMARY KEY,
    member_id INT NOT NULL,
    book_id INT NOT NULL,
    loan_date DATE NOT NULL,
    due_date DATE NOT NULL,
    return_date DATE,
    renew_count INT DEFAULT 0,
	status ENUM('Active', 'Returned') DEFAULT 'Active',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (member_id) REFERENCES members(member_id),
    FOREIGN KEY (book_id) REFERENCES books(book_id)
);

-- ----------------
-- FINES
-- ----------------
CREATE TABLE fines (
    fine_id INT AUTO_INCREMENT PRIMARY KEY,
    loan_id INT NOT NULL,
    amount DECIMAL(5,2) NOT NULL,
    paid BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (loan_id) REFERENCES loans(loan_id)
);

-- ----------------
-- INDEXES
-- ----------------
CREATE INDEX idx_books_isbn ON books(isbn);
CREATE INDEX idx_reservations_book ON reservations(book_id, status, reservation_date);
CREATE INDEX idx_loans_member ON loans(member_id, status);