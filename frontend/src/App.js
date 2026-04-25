import logo from './logo.svg';
import './App.css';
import RegisterPage from "./pages/RegisterPage";
import LoginPage from './pages/LoginPage';
import Dashboard from "./pages/Dashboard";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";


function App() {
   return (
    <Router>
      <h1 style={{ textAlign: "center" }}>E-Commerce App</h1>

      <Routes>
        <Route path="/register" element={<RegisterPage />} />
        <Route path="/login" element={<LoginPage />} />
        <Route path="/dashboard" element={<Dashboard />} />
      </Routes>
    </Router>
  );
}

export default App;
