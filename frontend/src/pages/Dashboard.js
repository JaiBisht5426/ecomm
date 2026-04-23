import React from "react";

function Dashboard() {

  const callProtectedAPI = async () => {
    const token = localStorage.getItem("token");

    try {
      const response = await fetch("http://localhost:8080/api/users/protected", {
        method: "GET",
        headers: {
          "Authorization": "Bearer " + token
        }
      });

      const data = await response.text();
      alert(data);

    } catch (error) {
      alert("Access Denied ❌");
    }
  };

  return (
    <div>
      <h2>Dashboard</h2>
      <button onClick={callProtectedAPI}>Call Protected API</button>
    </div>
  );
}

export default Dashboard;
