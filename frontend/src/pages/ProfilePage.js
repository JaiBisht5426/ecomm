import React, { useEffect, useState } from "react";

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

        try{
            const res = await fetch(
                "http://localhost:8080/api/users/change-password",
            {
                method: "PUT",

                headers:{
                    "Content-Type": "application/json",
                    Authorization: "Bearer " + token
                },

                body: JSON.stringify(passwordData)
            });

            const msg = await res.text();
            alert(msg);
        }
        catch(err)
        {
            console.error(err);
            alert("Password Update Failed");
        }
    };
    return (
        <div className="profile-container">

            <h2>My Profile 👤</h2>

            <form onSubmit={handleUpdate}>

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

                <button type="submit">
                    Update Profile
                </button>

            </form>
            <h2>Change Password </h2>

            <form onSubmit={updatePassword}>
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

                <button type="submit">
                    Update Password
                </button>

            </form>



        </div>
    );
}

export default ProfilePage;