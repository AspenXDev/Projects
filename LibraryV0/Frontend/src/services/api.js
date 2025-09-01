// path: Frontend/src/services/api.js
import axios from "axios";

export const api = axios.create({
  baseURL: "http://localhost:8081",
});

// Attach JWT only for protected endpoints
api.interceptors.request.use((config) => {
  const publicPaths = ["/books"]; // backend permitAll paths
  const isPublic = publicPaths.some((p) => config.url?.startsWith(p));
  if (!isPublic) {
    const token = localStorage.getItem("token");
    if (token) {
      config.headers["Authorization"] = `Bearer ${token}`;
    }
  }
  return config;
});
