// https://vite.dev/config/
import { defineConfig } from "vite";
import react from "@vitejs/plugin-react";

export default defineConfig({
  plugins: [react()],
  test: {
    globals: true, // make test utils global
    environment: "jsdom", // virtual DOM
    setupFiles: "./src/test/setupTests.js", // path to test setup file to be created
  },
});
