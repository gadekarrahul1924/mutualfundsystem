import React, { createContext, useState } from "react";
import { setAuthHeader } from "../services/ApiService";

export const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(null);

  const login = (username, password) => {
    setAuthHeader(username, password);
    setUser({ username });
  };

  const logout = () => {
    setUser(null);
    delete window.localStorage.auth;
  };

  return (
    <AuthContext.Provider value={{ user, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};