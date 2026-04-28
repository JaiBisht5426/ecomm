import { Navigate } from "react-router-dom";

function ProtectedRoute({ children, roleRequired }) {

  const token = localStorage.getItem("token");

  if (!token) {
    return <Navigate to="/login" />;
  }

  const payload = JSON.parse(atob(token.split(".")[1]));
  const role = payload.role;

  // 🔒 Role check
  if (roleRequired && role !== roleRequired) {
    return <Navigate to="/dashboard" />;
  }

  return children;
}

export default ProtectedRoute;
