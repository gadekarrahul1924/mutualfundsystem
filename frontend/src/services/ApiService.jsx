import axios from "axios";

const BASE_URL = "http://localhost:8081";

const apiClient = axios.create({
  baseURL: BASE_URL,
});

export const setAuthHeader = (username, password) => {
  const token = btoa(`${username}:${password}`);
  apiClient.defaults.headers.common["Authorization"] = `Basic ${token}`;
};

const generateIdempotencyKey = () => {
  return crypto.randomUUID();
};

export const getPortfolio = async () => {
  return apiClient.get("/portfolio");
};

export const buyFund = async (payload) => {
  const key = generateIdempotencyKey();

  return apiClient.post("/api/transactions/buy", payload, {
    headers: {
      "Idempotency-Key": key,
    },
  });
};

export const sellFund = async (payload) => {
  const key = generateIdempotencyKey();

  return apiClient.post("/api/transactions/sell", payload, {
    headers: {
      "Idempotency-Key": key,
    },
  });
};

export default apiClient;