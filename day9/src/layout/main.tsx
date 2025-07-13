import React from "react";
import { Outlet } from "react-router-dom";

const Main = () => {
    return (
        <div>
            <nav className="bg-gray-800 p-4 mb-5">
                <ul className="flex space-x-4">
                    <li>
                        <a
                            href="/todo"
                            className="text-white hover:bg-gray-700 px-3 py-2 rounded-md"
                        >
                            Todo List
                        </a>
                    </li>
                    <li>
                        <a
                            href="/products"
                            className="text-white hover:bg-gray-700 px-3 py-2 rounded-md"
                        >
                            Products
                        </a>
                    </li>
                </ul>
            </nav>

            <Outlet />
        </div>
    );
};

export default Main;
