import React, { useState } from "react";

function RegisterPage() {

  const [user, setUser] = useState({
    name: "",
    email: "",
    password: "",
    phone: ""
  });

  const handleChange = (e) => {
    setUser({
      ...user,
      [e.target.name]: e.target.value
    });
  };

const handleSubmit = async (e) => {
  e.preventDefault();

  try {
    const response = await fetch("http://localhost:8080/api/users/register", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({
        ...user,
        phone: user.phone ? Number(user.phone) : null
      })
    });

    const data = await response.text();

    if (!response.ok) {
      alert("Error: " + data);  // 👈 actual error show karega
      return;
    }

    alert(data);

  } catch (error) {
    console.error(error);
    alert("Server not reachable ❌");
  }
};




  return (
    <div style={{ textAlign: "center", marginTop: "50px" }}>
      <h2>Registration Page</h2>

      <form onSubmit={handleSubmit} style={{ display: "inline-block" }}>
        
        <div>
          <input
            type="text"
            name="name"
            placeholder="Enter Name"
            value={user.name}
            onChange={handleChange}
          />
        </div>

        <br />

        <div>
          <input
            type="email"
            name="email"
            placeholder="Enter Email"
            value={user.email}
            onChange={handleChange}
          />
        </div>

        <br />

        <div>
          <input
            type="password"
            name="password"
            placeholder="Enter Password"
            value={user.password}
            onChange={handleChange}
          />
        </div>

        <br />

        <div>
          <input
            type="text"
            name="phone"
            placeholder="Enter Phone Number"
            value={user.phone}
            onChange={handleChange}
          />
        </div>

        <br />

        <button type="submit">Register</button>

      </form>
    </div>
  );
}

export default RegisterPage;
