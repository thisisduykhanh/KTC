import React, { useEffect, useState } from "react";
import type { Product } from "../types/product";
import {
    deleteProduct,
    getAllProducts,
    updateProduct,
    createProduct,
} from "../services/ProductService";
import Modal from "../components/modal";

const ProductList: React.FC = () => {
    const [products, setProducts] = useState<Product[]>([]);
    const [loading, setLoading] = useState<boolean>(true);
    const [error, setError] = useState<string | null>(null);
    const [modalType, setModalType] = useState<"view" | "edit" | "add" | null>(
        null
    );
    const [selectedProduct, setSelectedProduct] = useState<Product | null>(
        null
    );
    const [formData, setFormData] = useState<Partial<Product>>({});
    const [formErrors, setFormErrors] = useState<
        Partial<Record<keyof Product, string>>
    >({});

    const fetchProducts = async () => {
        setLoading(true);
        setError(null);
        try {
            const data = await getAllProducts();
            data.sort((a, b) => (b.id || 0) - (a.id || 0));

            setProducts(data);
        } catch (err) {
            setError("Failed to fetch products");
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        fetchProducts();
    }, []);

    const handleDelete = async (id: string) => {
        setLoading(true);
        try {
            if (
                !window.confirm("Are you sure you want to delete this product?")
            ) {
                setLoading(false);
                return;
            }
            await deleteProduct(id);
            setProducts((prev) =>
                prev.filter((product) => String(product.id) !== String(id))
            );
        } catch (err) {
            setError("Failed to delete product");
        } finally {
            setLoading(false);
        }
    };

    const handleView = (product: Product) => {
        setSelectedProduct(product);
        setModalType("view");
    };

    const handleEdit = (product: Product) => {
        setSelectedProduct(product);
        setFormData({
            name: product.name,
            description: product.description,
            price: product.price,
            imgUrl: product.imgUrl,
        });
        setModalType("edit");
    };

    const handleAdd = () => {
        setFormData({});
        setModalType("add");
    };

    const handleModalClose = () => {
        setModalType(null);
        setSelectedProduct(null);
        setFormData({});
        setFormErrors({});
    };

    const validateForm = (): boolean => {
        const errors: Partial<Record<keyof Product, string>> = {};
        if (!formData.name) errors.name = "Name is required";
        if (!formData.description)
            errors.description = "Description is required";
        if (!formData.imgUrl) errors.imgUrl = "Image URL is required";
        if (formData.price == null || formData.price < 0)
            errors.price = "Price must be a positive number";

        setFormErrors(errors);
        return Object.keys(errors).length === 0;
    };

    const handleFormChange = (
        e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>
    ) => {
        const { name, value } = e.target;
        setFormData((prev) => ({
            ...prev,
            [name]: name === "price" ? parseFloat(value) || 0 : value,
        }));
        setFormErrors((prev) => ({ ...prev, [name]: undefined }));
    };

    const handleFormSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        if (!validateForm()) return;

        setLoading(true);
        try {
            if (modalType === "edit" && selectedProduct) {
                await updateProduct(String(selectedProduct.id), {
                    name: formData.name as string,
                    description: formData.description as string,
                    price: formData.price as number,
                    imgUrl: formData.imgUrl as string,
                });
                setProducts((prev) =>
                    prev.map((p) =>
                        p.id === selectedProduct.id ? { ...p, ...formData } : p
                    )
                );

                handleModalClose();
            } else if (modalType === "add") {
                const newProduct = await createProduct(
                    formData as Omit<Product, "id">
                );

                setProducts((prev) => [
                    {
                        ...newProduct,
                        id: Number(newProduct.id),
                    } as Product,
                    ...prev,
                ]);
            }
            handleModalClose();
        } catch (err) {
            setError(
                modalType === "edit"
                    ? "Failed to update product"
                    : "Failed to add product"
            );
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className="container mx-auto p-4">
            {loading && (
                <div className="text-center text-gray-600">Loading...</div>
            )}
            {error && <div className="text-center text-red-500">{error}</div>}

            <div className="mb-4">
                <button
                    onClick={handleAdd}
                    className="bg-blue-600 px-4 py-2 rounded-lg hover:bg-blue-700 transition duration-200"
                >
                    Add Product
                </button>
            </div>
            <ul className="space-y-4">
                {products.map((product) => (
                    <li
                        key={product.id}
                        className="bg-white rounded-lg shadow-md p-4 flex items-center justify-between hover:shadow-lg transition duration-200"
                    >
                        <div
                            className="flex items-center cursor-pointer"
                            onClick={() => handleView(product)}
                        >
                            <img
                                src={product.imgUrl}
                                alt={product.name}
                                className="w-20 h-20 object-cover rounded-lg mr-4"
                            />
                            <div className="flex-1 flex flex-col items-start">
                                <h3 className="text-lg font-semibold text-gray-800">
                                    {product.name}
                                </h3>
                                <p className="text-gray-600 text-sm">
                                    {product.description}
                                </p>

                                <p className="text-blue-600 font-bold">
                                    ${product.price}
                                </p>
                            </div>
                        </div>
                        <div className="space-x-2">
                            <button
                                onClick={() => handleEdit(product)}
                                className="bg-blue-500 text-blue-600 px-3 py-1 rounded-lg hover:bg-blue-700 transition duration-200"
                            >
                                Edit
                            </button>
                            <button
                                onClick={() => handleDelete(String(product.id))}
                                className="bg-red-500 text-red-600 px-3 py-1 rounded-lg hover:bg-red-700 transition duration-200"
                            >
                                Delete
                            </button>
                        </div>
                    </li>
                ))}
            </ul>

            <Modal
                isOpen={!!modalType}
                onClose={handleModalClose}
                title={
                    modalType === "view"
                        ? selectedProduct?.name || "Product Details"
                        : modalType === "edit"
                        ? "Edit Product"
                        : "Add Product"
                }
            >
                {modalType === "view" && selectedProduct && (
                    <div className="space-y-4">
                        <img
                            src={selectedProduct.imgUrl}
                            alt={selectedProduct.name}
                            className="w-full h-64 object-cover rounded-lg"
                        />
                        <p className="text-lg font-bold text-blue-600">
                            Price: ${selectedProduct.price}
                        </p>
                        <p className="text-gray-600">
                            {selectedProduct.description}
                        </p>
                        <button
                            onClick={handleModalClose}
                            className="bg-gray-300 text-gray-800 px-4 py-2 rounded-lg hover:bg-gray-400 transition duration-200 w-full"
                        >
                            Close
                        </button>
                    </div>
                )}

                {(modalType === "edit" || modalType === "add") && (
                    <form onSubmit={handleFormSubmit} className="space-y-4">
                        <div>
                            <label className="block text-sm font-medium text-gray-700">
                                Name
                            </label>
                            <input
                                type="text"
                                name="name"
                                value={formData.name || ""}
                                onChange={handleFormChange}
                                className="w-full p-2 border rounded-lg focus:ring-2 focus:ring-blue-500"
                                required
                            />
                            {formErrors.name && (
                                <p className="text-red-500 text-sm">
                                    {formErrors.name}
                                </p>
                            )}
                        </div>
                        <div>
                            <label className="block text-sm font-medium text-gray-700">
                                Description
                            </label>
                            <textarea
                                name="description"
                                value={formData.description || ""}
                                onChange={handleFormChange}
                                className="w-full p-2 border rounded-lg focus:ring-2 focus:ring-blue-500"
                                required
                            />
                            {formErrors.description && (
                                <p className="text-red-500 text-sm">
                                    {formErrors.description}
                                </p>
                            )}
                        </div>
                        <div>
                            <label className="block text-sm font-medium text-gray-700">
                                Price
                            </label>
                            <input
                                type="number"
                                name="price"
                                value={formData.price ?? ""}
                                onChange={handleFormChange}
                                className="w-full p-2 border rounded-lg focus:ring-2 focus:ring-blue-500"
                                required
                                min="0"
                                step="0.01"
                            />
                            {formErrors.price && (
                                <p className="text-red-500 text-sm">
                                    {formErrors.price}
                                </p>
                            )}
                        </div>
                        <div>
                            <label className="block text-sm font-medium text-gray-700">
                                Image URL
                            </label>
                            <input
                                type="text"
                                name="imgUrl"
                                value={formData.imgUrl || ""}
                                onChange={handleFormChange}
                                className="w-full p-2 border rounded-lg focus:ring-2 focus:ring-blue-500"
                                required
                            />
                            {formErrors.imgUrl && (
                                <p className="text-red-500 text-sm">
                                    {formErrors.imgUrl}
                                </p>
                            )}
                        </div>
                        <div className="flex justify-end space-x-2">
                            <button
                                type="button"
                                onClick={handleModalClose}
                                className="bg-gray-300 text-gray-800 px-4 py-2 rounded-lg hover:bg-gray-400 transition duration-200"
                            >
                                Cancel
                            </button>
                            <button
                                type="submit"
                                className="bg-blue-600 text-blue-600 px-4 py-2 rounded-lg hover:bg-blue-700 transition duration-200"
                            >
                                {modalType === "edit" ? "Update" : "Add"}
                            </button>
                        </div>
                    </form>
                )}
            </Modal>
        </div>
    );
};

export default ProductList;
