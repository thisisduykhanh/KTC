import axiosClient from "../api/axiosClient";
import type { Product } from "../types/product";

export const getAllProducts = async (): Promise<Product[]> => {
    const response = await axiosClient.get<Product[]>("/products");
    return response.data;
}


export const getProductById = async (id: string): Promise<Product> => {
    const response = await axiosClient.get<Product>(`/products/${id}`);
    return response.data;
}

export const createProduct = async (product: Omit<Product, 'id'>): Promise<Product> => {
    const response = await axiosClient.post<Product>("/products", product);
    return response.data;
}


export const updateProduct = async (id: string, product: Omit<Product, 'id'>): Promise<Product> => {
    const response = await axiosClient.put<Product>(`/products/${id}`, product);
    return response.data;
}

export const deleteProduct = async (id: string): Promise<void> => {
    await axiosClient.delete(`/products/${id}`);
}   