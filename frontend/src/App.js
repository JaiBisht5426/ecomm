import logo from './logo.svg';
import './App.css';
import RegisterPage from "./pages/RegisterPage";
import LoginPage from './pages/LoginPage';
import Dashboard from "./pages/Dashboard";
import ProductPage from "./pages/ProductPage";
import ProtectedRoute from './pages/ProtectedRoute';
import ViewProductPage from './pages/ViewProductPage';
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";


function App() {
   return (
    <Router>
      {/* <h1 style={{ textAlign: "center" }}>E-Commerce App</h1> */}

      <Routes>
        <Route path="/register" element={<RegisterPage />} />
        <Route path="/login" element={<LoginPage />} />
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/products" element={<ProtectedRoute roleRequired="ADMIN"><ProductPage /></ProtectedRoute>}/>
        <Route path="/viewproducts" element={<ViewProductPage />} />


      </Routes>
    </Router>
  );
}

export default App;
