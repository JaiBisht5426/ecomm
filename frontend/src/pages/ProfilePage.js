import React, { useEffect, useState } from "react";
import "./Profile.css";

function ProfilePage() {
    const token = localStorage.getItem("token");

    const [profile, setProfile] = useState({
        name: "",
        phone: ""
    });

    const [passwordData, setPasswordData] = useState({
        oldPassword: "",
        newPassword: ""
    })

    const fetchProfile = async () => {
        const res = await fetch(
            "http://localhost:8080/api/users/profile",
            {
                headers:
                {
                    Authorization: "Bearer " + token
                }
            }
        );

        const data = await res.json();

        setProfile({
            name: data.name || "",
            phone: data.phone || ""
        });
    };

    useEffect(() => {
        fetchProfile();
    }, []);

    const handleChange = (e) => {
        setProfile({
            ...profile,
            [e.target.name]: e.target.value
        });
    };

    const handleUpdate = async (e) => {

        e.preventDefault();

        try {
            const res = await fetch(
                "http://localhost:8080/api/users/update-profile",
                {
                    method: "PUT",

                    headers: {
                        "Content-Type": "application/json",
                        Authorization: "Bearer " + token
                    },

                    body: JSON.stringify(profile)
                }
            );
            const msg = await res.text();
            alert(msg);
        }
        catch (err) {
            console.error(err);

            alert("Update failed ");
        }
    };

    const handlePasswordChange = (e) => {
        setPasswordData({
            ...passwordData,
            [e.target.name]: e.target.value
        });
    };
    const updatePassword = async (e) => {
        e.preventDefault();

        try {
            const res = await fetch(
                "http://localhost:8080/api/users/change-password",
                {
                    method: "PUT",

                    headers: {
                        "Content-Type": "application/json",
                        Authorization: "Bearer " + token
                    },

                    body: JSON.stringify(passwordData)
                });

            const msg = await res.text();
            alert(msg);
        }
        catch (err) {
            console.error(err);
            alert("Password Update Failed");
        }
    };
    return (
        <div className="profile-container">

            {/* PROFILE CARD */}
            <div className="profile-card">

                <h2 className="profile-title">
                    My Profile 👤
                </h2>

                <form
                    className="profile-form"
                    onSubmit={handleUpdate}
                >

                    <input
                        type="text"
                        name="name"
                        placeholder="Enter Name"
                        value={profile.name}
                        onChange={handleChange}
                    />

                    <input
                        type="text"
                        name="phone"
                        placeholder="Enter Phone"
                        value={profile.phone}
                        onChange={handleChange}
                    />

                    <button
                        className="profile-btn"
                        type="submit"
                    >
                        Update Profile
                    </button>

                </form>

            </div>

            {/* PASSWORD CARD */}
            <div className="profile-card">

                <h2 className="profile-subtitle">
                    Change Password 🔒
                </h2>

                <form
                    className="profile-form"
                    onSubmit={updatePassword}
                >

                    <input
                        type="password"
                        name="oldPassword"
                        placeholder="Old Password"
                        onChange={handlePasswordChange}
                    />

                    <input
                        type="password"
                        name="newPassword"
                        placeholder="New Password"
                        onChange={handlePasswordChange}
                    />

                    <button
                        className="profile-btn password-btn"
                        type="submit"
                    >
                        Update Password
                    </button>

                </form>

            </div>

        </div>
    );
}

export default ProfilePage;