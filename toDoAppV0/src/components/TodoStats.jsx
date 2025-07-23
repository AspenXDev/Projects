// File: src/components/TodoStats.jsx

import React from "react";
import "../styles/TodoStats.css";

export function TodoStats({ todos }) {
  const total = todos.length;
  const completed = todos.filter((t) => t.completed).length;

  return (
    <div className="todo-stats">
      <p>Total Tasks: {total}</p>
      <p>Completed: {completed}</p>
    </div>
  );
}
