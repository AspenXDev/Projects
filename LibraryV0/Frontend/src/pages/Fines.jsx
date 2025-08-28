import React, { useEffect, useState } from "react";
import { AuthProvider, useAuth } from "../contexts/AuthContext.jsx";
import { FineService } from "../services/FineService";

export function Fines() {
  const [fines, setFines] = useState([]);

  useEffect(() => {
    FineService.getAllFines()
      .then((data) => setFines(data))
      .catch((err) => console.error(err));
  }, []);

  return (
    <div>
      <h2>Fines</h2>
      {fines.map((fine) => (
        <div key={fine.fine_id}>
          <p>Loan ID: {fine.loan_id}</p>
          <p>Amount: ${fine.amount}</p>
          <p>Paid: {fine.paid ? "Yes" : "No"}</p>
        </div>
      ))}
    </div>
  );
}
