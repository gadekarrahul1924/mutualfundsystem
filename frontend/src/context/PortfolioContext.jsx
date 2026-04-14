import React, { createContext, useState } from "react";
import { getPortfolio } from "../services/ApiService";

export const PortfolioContext = createContext();

export const PortfolioProvider = ({ children }) => {
  const [portfolio, setPortfolio] = useState([]);

  const fetchPortfolio = async () => {
    try {
      const res = await getPortfolio();
      setPortfolio(res.data);
    } catch (err) {
      console.error(err);
    }
  };

  return (
    <PortfolioContext.Provider value={{ portfolio, fetchPortfolio }}>
      {children}
    </PortfolioContext.Provider>
  );
};