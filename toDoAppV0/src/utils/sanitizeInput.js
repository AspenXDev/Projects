// File: src/utils/sanitizeInput.js

// Remove potentially dangerous HTML characters (< > " ' &)
// from the input string globally (/g) to help prevent XSS.

export function sanitizeInput(str) {
  return str.replace(/[<>"'&]/g, "");
}
