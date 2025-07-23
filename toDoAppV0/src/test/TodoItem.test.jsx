// src/tests/TodoItem.test.jsx
import { render, screen } from "@testing-library/react";
import { describe, it, expect } from "vitest";
import { TodoItem } from "../components/TodoItem";

describe("TodoItem", () => {
  const todo = { id: 1, text: "Buy milk", completed: false };

  it("renders the todo text", () => {
    render(<TodoItem todo={todo} onToggle={() => {}} onDelete={() => {}} />);
    expect(screen.getByTestId("todo-text").textContent).toBe("Buy milk");
  });
});
