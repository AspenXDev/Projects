import React from "react";
import "../styles/TodoFilter.css";

export function TodoFilter({ filter, setFilter }) {
  const filters = ["All", "Active", "Completed"];

  return (
    <div className="todo-filter">
      {filters.map((f) => (
        <button
          key={f}
          className={`todo-filter__button ${filter === f ? "active" : ""}`}
          onClick={() => setFilter(f)}
        >
          {f}
        </button>
      ))}
    </div>
  );
}
