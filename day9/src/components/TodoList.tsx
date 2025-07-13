import { useState, useEffect } from "react";
import axios from "axios";
import type { Todo } from "../types/todo";

function TodoList() {
    const [todos, setTodos] = useState<Todo[]>([]);
    const [newTodo, setNewTodo] = useState<string>("");

    const fetchTodos = async () => {
        try {
            const response = await axios.get(
                "https://jsonplaceholder.typicode.com/todos?_limit=10"
            );
            setTodos(response.data);
        } catch (error) {
            console.error("Error fetching todos:", error);
            alert("Failed to fetch todos");
        }
    };

    useEffect(() => {
        fetchTodos();
    }, []);

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        if (!newTodo.trim()) {
            alert("Please enter a todo task");
            return;
        }

        try {
            const response = await axios.post(
                "https://jsonplaceholder.typicode.com/todos",
                {
                    title: newTodo,
                    completed: false,
                }
            );
            setTodos([response.data, ...todos]);
            setNewTodo("");
            alert("Todo added successfully!");
        } catch (error) {
            console.error("Error adding todo:", error);
            alert("Failed to add todo");
        }
    };

    return (
        <div className="min-h-screen bg-gradient-to-br from-blue-100 via-purple-100 to-pink-100 flex items-center justify-center">
            <div className="bg-white shadow-2xl rounded-2xl p-8 w-full max-w-md">
                <h1 className="text-3xl font-extrabold text-purple-700 mb-6 text-center">
                    Todo List
                </h1>
                <form onSubmit={handleSubmit} className="flex mb-6 gap-2">
                    <input
                        type="text"
                        value={newTodo}
                        onChange={(e) => setNewTodo(e.target.value)}
                        placeholder="Enter todo task"
                        className="flex-1 border border-purple-300 rounded-lg p-3 focus:outline-none focus:ring-2 focus:ring-purple-400 transition"
                    />
                    <button
                        type="submit"
                        className="bg-gradient-to-r from-purple-500 to-pink-500 text-white px-5 py-3 rounded-lg font-semibold shadow hover:scale-105 transition"
                    >
                        Add
                    </button>
                </form>
                <ul className="space-y-3">
                    {todos.map((todo) => (
                        <li
                            key={todo.id}
                            className="flex items-center bg-purple-50 rounded-lg px-4 py-3 shadow hover:bg-purple-100 transition"
                        >
                            <span className="flex-1 text-gray-800">
                                {todo.title}
                            </span>
                            {todo.completed ? (
                                <span className="ml-2 text-green-500 font-bold text-xs bg-green-100 px-2 py-1 rounded">
                                    Done
                                </span>
                            ) : (
                                <span className="ml-2 text-yellow-500 font-bold text-xs bg-yellow-100 px-2 py-1 rounded">
                                    Pending
                                </span>
                            )}
                        </li>
                    ))}
                </ul>
            </div>
        </div>
    );
}

export default TodoList;
