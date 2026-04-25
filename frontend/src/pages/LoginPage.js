import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

function LoginPage() {

  const navigate = useNavigate();

  const [user, setUser] = useState({
    email: "",
    password: ""
  });

  const handleChange = (e) => {
    setUser({
      ...user,
      [e.target.name]: e.target.value
    });
  };

  const handleLogin = async (e) => {
    e.preventDefault();

    try {
      const response = await fetch("http://localhost:8080/api/users/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(user)
      });

      // ❗ Check response
      if (!response.ok) {
        throw new Error("Invalid credentials");
      }

      const token = await response.text();

      // 🔐 Save token
      localStorage.setItem("token", token);

      alert("Login Successful ✅");

      // 🔥 Redirect to dashboard
      navigate("/dashboard");

    } catch (error) {
      console.error(error);
      alert("Login Failed ❌");
    }
  };

  return (
    <div style={{ textAlign: "center" }}>
      <h2>Login</h2>

      <form onSubmit={handleLogin}>
        <input
          name="email"
          placeholder="Email"
          onChange={handleChange}
          required
        />
        <br /><br />

        <input
          name="password"
          type="password"
          placeholder="Password"
          onChange={handleChange}
          required
        />
        <br /><br />

        <button type="submit">Login</button>
      </form>
    </div>
  );
}

export default LoginPage;
