// src/services/api.js
import axios from "axios";

const BASE_URL = "http://localhost:8081";

export const api = axios.create({
  baseURL: BASE_URL,
});

// Attach JWT only for protected endpoints
api.interceptors.request.use((config) => {
  const publicPaths = ["/books"]; // <-- match your backend's permitAll path
  const isPublic = publicPaths.some((p) => config.url?.startsWith(p));
  if (!isPublic) {
    const token = localStorage.getItem("token");
    if (token) {
      config.headers["Authorization"] = `Bearer ${token}`;
    }
  }
  return config;
});
