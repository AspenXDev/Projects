// File: src/components/TodoItem.jsx
import React, { useState } from "react";
import "../styles/TodoItem.css";

function sanitizeInput(str) {
  return str.replace(/[<>"'&]/g, "");
}

function TodoItem({ todo, updateTodo, deleteTodo }) {
  const [isEditing, setIsEditing] = useState(false);
  const [title, setTitle] = useState(todo.title);
  const [description, setDescription] = useState(todo.description);

  const handleToggle = () =>
    updateTodo(todo.id, { completed: !todo.completed });

  const handleSave = () => {
    updateTodo(todo.id, {
      title: sanitizeInput(title),
      description: sanitizeInput(description),
    });
    setIsEditing(false);
  };

  return (
    <li className={`todo-item ${todo.completed ? "completed" : ""}`}>
      <input type="checkbox" checked={todo.completed} onChange={handleToggle} />
      {isEditing ? (
        <>
          <input
            value={title}
            onChange={(e) => setTitle(e.target.value)}
            className="todo-item__input"
          />
          <input
            value={description}
            onChange={(e) => setDescription(e.target.value)}
            className="todo-item__input"
          />
          <button onClick={handleSave}>Save</button>
        </>
      ) : (
        <>
          <span className="todo-item__title">{todo.title}</span>
          <span className="todo-item__desc">{todo.description}</span>
          <span className="todo-item__due">{todo.dueDate}</span>
          <button onClick={() => setIsEditing(true)}>Edit</button>
        </>
      )}
      <button onClick={() => deleteTodo(todo.id)}>Delete</button>
    </li>
  );
}

export default TodoItem;
