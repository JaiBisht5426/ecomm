import React, { useState } from "react";

function LoginPage() {

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

      const token = await response.text();

      // 🔐 Save token
      localStorage.setItem("token", token);

      alert("Login Successful ✅");

    } catch (error) {
      alert("Login Failed ❌");
    }
  };

  return (
    <div>
      <h2>Login</h2>

      <form onSubmit={handleLogin}>
        <input name="email" placeholder="Email" onChange={handleChange} />
        <br />
        <input name="password" placeholder="Password" onChange={handleChange} />
        <br />
        <button type="submit">Login</button>
      </form>
    </div>
  );
}

export default LoginPage;
