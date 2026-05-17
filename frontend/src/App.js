import logo from './logo.svg';
import './App.css';
import RegisterPage from "./pages/RegisterPage";
import LoginPage from './pages/LoginPage';
import Dashboard from "./pages/Dashboard";
import ProductPage from "./pages/ProductPage";
import ProtectedRoute from './pages/ProtectedRoute';
import ViewProductPage from './pages/ViewProductPage';
import ProductDetails from "./pages/ProductDetails";
import CartPage from "./pages/CartPage";
import OrderHistoryPage from './pages/OrderHistoryPage';
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import ResetPasswordPage from "./pages/ResetPasswordPage";


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
        <Route path="/viewproducts/:id" element={<ProductDetails />} />
        <Route path="/cart" element={<ProtectedRoute><CartPage /></ProtectedRoute>}/>
        <Route path="/orders" element={<OrderHistoryPage/>}/>
        <Route path="/reset-password" element={<ResetPasswordPage />}/>
      </Routes>
    </Router>
  );
}

export default App;
