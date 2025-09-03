// path: Frontend/src/services/api.js
import axios from "axios";

export const api = axios.create({
  baseURL: "http://localhost:8081",
});

// Interceptor: attach JWT only for protected endpoints
api.interceptors.request.use((config) => {
  const publicPaths = ["/books"]; // Backend permitAll paths
  const isPublic = publicPaths.some((p) => config.url?.startsWith(p));

  if (!isPublic) {
    const token = localStorage.getItem("token"); // 🔑 Retrieve token
    if (token) {
      config.headers["Authorization"] = `Bearer ${token}`;
    }
  }

  return config;
});
