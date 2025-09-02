import React from "react";
import { RegisterForm } from "./RegisterForm.jsx";

export function RegisterPage() {
  return (
    <div style={{ maxWidth: 480, margin: "50px auto", padding: 20 }}>
      <h2>Register</h2>
      <RegisterForm />
    </div>
  );
}
