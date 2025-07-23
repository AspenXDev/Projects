// File: src/components/TodoList.jsx
import React from "react";
import TodoItem from "./TodoItem";
import "../styles/TodoList.css";

export function TodoList({ todos, updateTodo, deleteTodo }) {
  if (todos.length === 0) {
    return <p className="todo-list__empty">No todos found.</p>;
  }

  return (
    <ul className="todo-list">
      {todos.map((todo) => (
        <TodoItem
          key={todo.id}
          todo={todo}
          updateTodo={updateTodo}
          deleteTodo={deleteTodo}
        />
      ))}
    </ul>
  );
}
