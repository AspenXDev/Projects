import React, { useEffect, useState } from "react";
import { AuthProvider, useAuth } from "../contexts/AuthContext.jsx";
import { LoanService } from "../services/LoanService";

export function Loans() {
  const [loans, setLoans] = useState([]);

  useEffect(() => {
    LoanService.getAllLoans()
      .then((data) => setLoans(data))
      .catch((err) => console.error(err));
  }, []);

  return (
    <div>
      <h2>Loans</h2>
      {loans.map((loan) => (
        <div key={loan.loan_id}>
          <p>Book: {loan.book.title}</p>
          <p>Due Date: {loan.due_date}</p>
          <p>Status: {loan.status}</p>
        </div>
      ))}
    </div>
  );
}
