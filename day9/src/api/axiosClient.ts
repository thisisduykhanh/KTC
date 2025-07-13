import axios from "axios";

const axiosClient = axios.create({
    baseURL: "https://687076817ca4d06b34b6daf9.mockapi.io/api/v1",
    headers: {
        "Content-Type": "application/json",
    },
    timeout: 10000,
});

export default axiosClient;
