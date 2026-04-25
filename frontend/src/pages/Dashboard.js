import React, { useEffect } from "react";
import { useNavigate } from "react-router-dom";

function Dashboard() {

  const navigate = useNavigate();

  // ✅ Check login on load
  useEffect(() => {
    const token = localStorage.getItem("token");

    if (!token) {
      alert("Please login first ❌");
      navigate("/login");
    }
  }, [navigate]);

  // ✅ Protected API call
  const callProtectedAPI = async () => {
    const token = localStorage.getItem("token");

    try {
      const response = await fetch("http://localhost:8080/api/users/protected", {
        method: "GET",
        headers: {
          "Authorization": "Bearer " + token
        }
      });

      // ❗ Check response
      if (!response.ok) {
        throw new Error("Unauthorized");
      }

      const data = await response.text();
      alert(data);

    } catch (error) {
      console.error(error);
      alert("Access Denied ❌");
    }
  };

  // ✅ Logout function
  const handleLogout = () => {
    localStorage.removeItem("token");
    alert("Logged out ✅");
    navigate("/login");
  };

  return (
    <div style={{ textAlign: "center" }}>
      <h2>Dashboard</h2>

      <button onClick={callProtectedAPI}>
        Call Protected API
      </button>

      <br /><br />

      <button onClick={handleLogout}>
        Logout
      </button>
    </div>
  );
}

export default Dashboard;
