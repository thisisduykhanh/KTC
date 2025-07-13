import { BrowserRouter, Route, Routes } from "react-router-dom";
import ProductList from "../pages/ProductList";
import TodoList from "../components/TodoList";
import Layout from "../layout/main";

const AppRouters = () => {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Layout />}>
                    <Route index element={<TodoList />} />
                    <Route path="todo" element={<TodoList />} />
                    <Route path="products" element={<ProductList />} />
                </Route>
            </Routes>
        </BrowserRouter>
    );
};

export default AppRouters;
