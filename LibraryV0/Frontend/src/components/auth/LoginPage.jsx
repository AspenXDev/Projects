// path: Frontend/src/components/auth/LoginPage.jsx
import React from "react";
import { LoginForm } from "./LoginForm.jsx";

export function LoginPage() {
  return (
    <div style={{ maxWidth: 480, margin: "50px auto", padding: 20 }}>
      <h2>Login</h2>
      <LoginForm />
    </div>
  );
}
