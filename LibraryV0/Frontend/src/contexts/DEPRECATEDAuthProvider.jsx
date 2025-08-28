// // PATH: src/contexts/AuthProvider.jsx
// import React, { useState, useEffect } from "react";
// import { AuthContext } from "./AuthContext.jsx";
// import * as AuthService from "../services/AuthService.js";

// /**
//  * Provides: { user, login(username,password) -> role, logout() }
//  * user: { token, role, username, user_id }
//  */
// export function AuthProvider({ children }) {
//   const [user, setUser] = useState(() => {
//     const saved = localStorage.getItem("user");
//     return saved ? JSON.parse(saved) : null;
//   });

//   useEffect(() => {
//     // If token exists but user missing, try to hydrate basic state
//     const token = localStorage.getItem("token");
//     const role = localStorage.getItem("role");
//     if (token && !user) {
//       setUser({
//         token,
//         role,
//         username: null,
//         user_id: null,
//       });
//     }
//     // eslint-disable-next-line react-hooks/exhaustive-deps
//   }, []);

//   async function login(username, password) {
//     const data = await AuthService.login(username, password);
//     // expect data: { token, role, ...maybe user ...}
//     const newUser = {
//       token: data.token,
//       role: data.role?.toLowerCase?.() ?? "members",
//       username,
//       user_id: data.user_id ?? null,
//     };
//     setUser(newUser);
//     localStorage.setItem("user", JSON.stringify(newUser));
//     localStorage.setItem("token", data.token);
//     localStorage.setItem("role", newUser.role);
//     return newUser.role;
//   }

//   function logout() {
//     setUser(null);
//     localStorage.removeItem("user");
//     localStorage.removeItem("token");
//     localStorage.removeItem("role");
//   }

//   return (
//     <AuthContext.Provider value={{ user, login, logout }}>
//       {children}
//     </AuthContext.Provider>
//   );
// }
