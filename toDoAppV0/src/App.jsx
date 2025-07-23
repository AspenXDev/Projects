import React, { useState } from "react";
import "./styles/App.css";
import { TodoHeader } from "./components/TodoHeader";
import { TodoForm } from "./components/TodoForm";
import { TodoList } from "./components/TodoList";
import { TodoFilter } from "./components/TodoFilter";
import { TodoStats } from "./components/TodoStats";
import { sanitizeInput } from "./utils/sanitizeInput";

function App() {
  const [todos, setTodos] = useState([]);
  const [filter, setFilter] = useState("All");

  const getTodos = () => {
    if (filter === "All") return todos;
    if (filter === "Active") return todos.filter((t) => !t.completed);
    if (filter === "Completed") return todos.filter((t) => t.completed);
    return todos;
  };

  const addTodo = ({ title, description, dueDate }) => {
    const id = Date.now();
    const newTodo = {
      id,
      title: sanitizeInput(title),
      description: sanitizeInput(description),
      dueDate,
      completed: false,
    };
    setTodos((prev) => [...prev, newTodo]);
  };

  const updateTodo = (id, updates) => {
    setTodos((prev) =>
      prev.map((todo) => (todo.id === id ? { ...todo, ...updates } : todo))
    );
  };

  const deleteTodo = (id) => {
    setTodos((prev) => prev.filter((todo) => todo.id !== id));
  };

  return (
    <div className="app">
      <TodoHeader />
      <TodoForm addTodo={addTodo} />
      <TodoFilter filter={filter} setFilter={setFilter} />
      <TodoList
        todos={getTodos()}
        updateTodo={updateTodo}
        deleteTodo={deleteTodo}
      />
      <TodoStats todos={todos} />
    </div>
  );
}

export default App;
