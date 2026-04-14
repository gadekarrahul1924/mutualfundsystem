import React from "react";
import Dashboard from "./pages/Dashboard";
import Login from "./pages/Login";
import { useAuth } from "./hooks/useAuth";

const App = () => {
  const { user } = useAuth();

  return user ? <Dashboard /> : <Login />;
};

export default App;