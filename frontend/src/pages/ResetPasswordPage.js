import React, { useState } from "react";
import { useSearchParams, useNavigate } from "react-router-dom";

function ResetPasswordPage() {

  const [searchParams] = useSearchParams();

  const token = searchParams.get("token");

  const navigate = useNavigate();

  const [passwords, setPasswords] = useState({
    newPassword: "",
    confirmPassword: ""
  });

  const handleChange = (e) => {

    setPasswords({
      ...passwords,
      [e.target.name]: e.target.value
    });
  };

  const handleResetPassword = async (e) => {

    e.preventDefault();

    // Password match check
    if (
      passwords.newPassword !==
      passwords.confirmPassword
    ) {
      alert("Passwords do not match ❌");
      return;
    }

    try {

      const response = await fetch(
        "http://localhost:8080/api/auth/reset-password",
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json"
          },
          body: JSON.stringify({
            token: token,
            newPassword: passwords.newPassword
          })
        }
      );

      const data = await response.json();

      if (!response.ok) {
        alert(
          data.newPassword ||
          "Validation Failed ❌"
        );

        return;
      }

      alert("Password reset successful ✅");

      navigate("/login");

    } catch (error) {

      alert("Invalid or expired token ❌");
    }
  };

  return (

    <div className="login-container">

      <div className="login-card">

        <h2>Reset Password</h2>

        <form onSubmit={handleResetPassword}>

          <input
            type="password"
            name="newPassword"
            placeholder="Enter New Password"
            onChange={handleChange}
            required
          />

          <input
            type="password"
            name="confirmPassword"
            placeholder="Confirm Password"
            onChange={handleChange}
            required
          />

          <button type="submit">
            Reset Password
          </button>

        </form>

      </div>

    </div>
  );
}

export default ResetPasswordPage;