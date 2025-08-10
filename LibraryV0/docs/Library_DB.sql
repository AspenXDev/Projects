CREATE DATABASE Library_DB;
USE Library_DB;

-- 1. Roles
CREATE TABLE Roles (
  role_id INT AUTO_INCREMENT PRIMARY KEY,
  role_name VARCHAR(20) UNIQUE NOT NULL -- MEMBER, STAFF, LIBRARIAN, ADMIN
);

-- 2. Users (login, roles)
CREATE TABLE Users (
  user_id INT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(100) UNIQUE NOT NULL,
  email VARCHAR(255) UNIQUE NOT NULL,
  password_hash VARCHAR(255) NOT NULL,
  role_id INT NOT NULL,
  is_active BOOLEAN DEFAULT TRUE,
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (role_id) REFERENCES Roles(role_id)
);

-- 3. Members (profile linked to Users)
CREATE TABLE Members (
  member_id INT AUTO_INCREMENT PRIMARY KEY,
  user_id INT UNIQUE NOT NULL,
  name VARCHAR(255) NOT NULL,
  phone VARCHAR(20),
  registered_on DATETIME DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (user_id) REFERENCES Users(user_id)
);

-- 4. Categories (genres/classifications)
CREATE TABLE Categories (
  category_id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) UNIQUE NOT NULL,
  description TEXT
);

-- 5. Material Types (formats like Hardcover, Softcover, Journal)
CREATE TABLE MaterialTypes (
  material_type_id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50) UNIQUE NOT NULL
);

-- 6. Books (catalog level)
CREATE TABLE Books (
  book_id INT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  authorname VARCHAR(255),
  isbn CHAR(17) UNIQUE,
  synopsis TEXT,
  published_year YEAR,
  category_id INT,
  material_type_id INT,
  status ENUM('Available', 'Archived', 'Lost', 'Damaged') DEFAULT 'Available',
  FOREIGN KEY (category_id) REFERENCES Categories(category_id),
  FOREIGN KEY (material_type_id) REFERENCES MaterialTypes(material_type_id)
);

-- 7. Book Copies (physical copies with location)
CREATE TABLE BookCopies (
  copy_id INT AUTO_INCREMENT PRIMARY KEY,
  book_id INT NOT NULL,
  location_section VARCHAR(100),
  location_shelf INT,
  location_row INT,
  location_note TEXT,
  is_available BOOLEAN DEFAULT TRUE,
  FOREIGN KEY (book_id) REFERENCES Books(book_id)
);

-- 8. Loans (borrow/return/renew)
CREATE TABLE Loans (
  loan_id INT AUTO_INCREMENT PRIMARY KEY,
  copy_id INT NOT NULL,
  member_id INT NOT NULL,
  loan_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  due_date DATE NOT NULL,
  return_date DATE,
  returned BOOLEAN DEFAULT FALSE,
  late_fee DECIMAL(6,2) DEFAULT 0.00,
  renew_count INT DEFAULT 0,
  status ENUM('Active', 'Returned', 'Overdue', 'Cancelled') DEFAULT 'Active',
  FOREIGN KEY (copy_id) REFERENCES BookCopies(copy_id),
  FOREIGN KEY (member_id) REFERENCES Members(member_id)
);

-- 9. Reservations (queues & holds)
CREATE TABLE Reservations (
  reservation_id INT AUTO_INCREMENT PRIMARY KEY,
  copy_id INT NOT NULL,
  member_id INT NOT NULL,
  reservation_date DATETIME DEFAULT CURRENT_TIMESTAMP,
  status ENUM('Active', 'Cancelled', 'Completed') DEFAULT 'Active',
  queue_position INT NOT NULL,
  hold_expiry DATE,
  FOREIGN KEY (copy_id) REFERENCES BookCopies(copy_id),
  FOREIGN KEY (member_id) REFERENCES Members(member_id)
);

-- 10. Fines & Payments
CREATE TABLE Fines (
  fine_id INT AUTO_INCREMENT PRIMARY KEY,
  loan_id INT NOT NULL,
  amount DECIMAL(6,2) NOT NULL,
  paid BOOLEAN DEFAULT FALSE,
  paid_on DATETIME,
  FOREIGN KEY (loan_id) REFERENCES Loans(loan_id)
);

-- 12. Activity Logs (audit)
CREATE TABLE ActivityLogs (
  log_id INT AUTO_INCREMENT PRIMARY KEY,
  user_id INT,
  action VARCHAR(255),
  action_date DATETIME DEFAULT CURRENT_TIMESTAMP,
  details TEXT,
  FOREIGN KEY (user_id) REFERENCES Users(user_id)
);