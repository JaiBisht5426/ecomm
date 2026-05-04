import React, { useEffect, useState } from "react";

function CartPage() {
    const [cart, setCart] = useState([]);

    const token = localStorage.getItem("token");

    useEffect(() => {
        fetch("http://localhost:8080/api/cart", {
            headers: {
                Authorization: "Bearer " + token
            }
        })
            .then(res => res.json())
            .then(data => setCart(data));
    }, []);

    const total = cart.reduce(
        (sum, item) => sum + item.product.price * item.quantity,
        0
    );

    return (
        <div>
            <h2>Your Cart 🛒</h2>

            {cart.length === 0 ? (
                <h3>Cart is empty 🛒</h3>
            ) : (
                cart.map(item => (
                    <div key={item.id} style={{ borderBottom: "1px solid gray" }}>
                        <h3>{item.product.name}</h3>
                        <p>Price: ₹ {item.product.price}</p>
                        <p>Quantity: {item.quantity}</p>
                        <p>Total: ₹ {item.product.price * item.quantity}</p>
                    </div>
                ))
            )}

        </div>
    );
}

export default CartPage;
