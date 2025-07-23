import React, { useState } from "react";
import "../styles/TodoForm.css";
import { sanitizeInput } from "../utils/sanitizeInput";

export function TodoForm({ addTodo }) {
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const [dueDate, setDueDate] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();
    if (!title.trim()) return;
    addTodo({
      title: sanitizeInput(title),
      description: sanitizeInput(description),
      dueDate,
    });
    setTitle("");
    setDescription("");
    setDueDate("");
  };

  return (
    <form className="todo-form" onSubmit={handleSubmit}>
      <input
        className="todo-form__input"
        type="text"
        value={title}
        onChange={(e) => setTitle(e.target.value)}
        placeholder="Title"
      />
      <input
        className="todo-form__input"
        type="text"
        value={description}
        onChange={(e) => setDescription(e.target.value)}
        placeholder="Description"
      />
      <input
        className="todo-form__input"
        type="date"
        value={dueDate}
        onChange={(e) => setDueDate(e.target.value)}
      />
      <button className="todo-form__button" type="submit">
        Add
      </button>
    </form>
  );
}
