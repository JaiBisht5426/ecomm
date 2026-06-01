import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./Login.css"; // 👈 same CSS reuse

function RegisterPage() {

  const [user, setUser] = useState({
    name: "",
    email: "",
    password: "",
    phone: ""
  });

  const navigate = useNavigate();

  const handleChange = (e) => {
    setUser({
      ...user,
      [e.target.name]: e.target.value
    });
  };

  const handleRegister = async (e) => {

    e.preventDefault();

    try {

      const response = await fetch("http://localhost:8080/api/users/register", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(user)
      });

      const data = await response.json();

      if (!response.ok) {

        alert(
          data.name ||
          data.email ||
          data.password ||
          data.phone ||
          "Validation Failed ❌"
        );

        return;
      }

      alert(data.message);

      navigate("/login");

    } catch (error) {

      console.log(error);
      // alert("Registration Failed ❌");

    }
  };

  return (
    <div className="login-container">

      <div className="login-card">
        <h2>Register</h2>

        <form onSubmit={handleRegister}>

          <input
            type="text"
            name="name"
            placeholder="Enter Name"
            onChange={handleChange}
            required
          />

          <input
            type="email"
            name="email"
            placeholder="Enter Email"
            onChange={handleChange}
            required
          />

          <input
            type="password"
            name="password"
            placeholder="Enter Password"
            onChange={handleChange}
            required
          />

          <input
            type="text"
            name="phone"
            placeholder="Enter Phone"
            onChange={handleChange}
            required
          />

          <button type="submit">Register</button>
        </form>

        <p>
          Already have an account?
          <span onClick={() => navigate("/login")}> Login</span>
        </p>

      </div>

    </div>
  );
}

export default RegisterPage;
