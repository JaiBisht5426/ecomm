// import React from "react";
// import { useNavigate } from "react-router-dom";
// import "./Navbar.css";

// function Navbar() {

//   const navigate = useNavigate();

//   // 👤 Get email from token (simple decode)
//   const token = localStorage.getItem("token");

//   let userEmail = "";

//   if (token) {
//     try {
//       const payload = JSON.parse(atob(token.split(".")[1]));
//       userEmail = payload.sub; // 👈 email stored in JWT
//     } catch (e) {
//       userEmail = "User";
//     }
//   }

//   // 🔓 Logout
//   const handleLogout = () => {
//     localStorage.removeItem("token");
//     navigate("/login");
//   };

//   return (
//     <div className="navbar">

//       <h2 onClick={() => navigate("/products")}>
//         🛍️ E-Commerce
//       </h2>

//       <div className="nav-right">
//         <span>{userEmail}</span>
//         <button onClick={handleLogout}>Logout</button>
//       </div>

//     </div>
//   );
// }

// export default Navbar;
