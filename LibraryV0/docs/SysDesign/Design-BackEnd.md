### DB -> Backend Entity Classes

- **`Role`** -> `roles` on DB schema
- **`User`** -> `users` on DB schema
- **`Member`** -> `members` on DB schema
- **`Librarian`** -> `librarians` on DB schema
- **`Book`** -> `books` on DB schema
- **`Reservation`** -> `reservations` on DB schema
- **`Loan`** -> `loans` on DB schema
- **`Fine`** -> `fines` on DB schema

- **`ReservationStatus` and **`ReservationStatusConverter`\*\*\*\*
- Where values contain spaces ('On Hold'), JPA can’t map directly with @Enumerated. The converter bridges Java enum OnHold to/from DB value 'On Hold'.
