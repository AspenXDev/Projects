# Library Management System (LMS) – User Manual

## 1. Introduction

The Library Management System (LMS) is a web-based application designed to manage books, members, loans, and fines efficiently. This guide covers installation, setup, and basic usage for both backend and frontend.

---

## 2. System Requirements

- **Operating System:** Windows 10+, macOS, or Linux
- **Java:** JDK 17+
- **Maven:** 3.8+
- **Node.js:** 18+
- **npm:** 9+ or yarn 1.22+
- **Database:** MySQL 8+ or compatible

---

## 3. Backend Setup

### 3.1 Clone Repository

```bash
git clone https://github.com/AspenXDev/LibraryV0.git
cd LibraryV0/01Backend/library-management-system
```

### 3.2 Configure Database

1. Create a MySQL database:

```sql
CREATE DATABASE library_db;
```

2. Import the pre-populated SQL:

```sql
SOURCE 00Database/Library_DB(population).sql;
```

3. Update `application.properties` with your database credentials.

### 3.3 Build and Run Backend

```bash
# Using Maven wrapper
./mvnw clean install
./mvnw spring-boot:run
```

The backend will be available at: `http://localhost:8081`

---

## 4. Frontend Setup

### 4.1 Navigate to Frontend Folder

```bash
cd ../../02Frontend
```

### 4.2 Install Dependencies

```bash
npm install
```

> **Note:** Do **not** commit `node_modules` to GitHub; it can be regenerated with `npm install`.

### 4.3 Run Frontend

```bash
npm run dev
```

The frontend will be available at: `http://localhost:5173` (default Vite port)

---

## 5. Configuration Notes

- **Environment Variables:** Update API base URLs in `src/services/api.js` if backend runs on a different port.
- **Excluding IDE & Build Artifacts:**
  Do **not** commit Eclipse `.metadata`, `source`, `logs`, or `target` folders.
- **Frontend Cleanup:**
  Exclude `node_modules` from version control.

---

## 6. Using LMS

1. Open the frontend in your browser.
2. Login with librarian credentials or create a new account if registration is enabled.
3. Navigate the dashboard to manage:

   - **Books** – Add, update, delete, and view books.
   - **Members** – Register, update, or delete library members.
   - **Loans** – Issue, update, and track borrowed books.
   - **Fines** – Record and track unpaid fines.

> All actions automatically update the backend database.

---

## 7. Troubleshooting

- **Port Conflicts:** Ensure no other service is running on ports 8081 (backend) or 5173 (frontend).
- **CORS Issues:** Verify the frontend points to the correct backend URL in `api.js`.
- **Database Connection Errors:** Confirm credentials and database are correctly configured in `application.properties`.

---

## 8. Additional Notes

- Always pull the latest changes from GitHub to avoid version mismatches.
- Use Git branches for development to keep the main branch stable.
- Regularly back up your database before making major changes.
