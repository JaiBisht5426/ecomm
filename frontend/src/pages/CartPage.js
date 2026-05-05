import React, { useEffect, useState } from "react";

function CartPage() {

  const [cart, setCart] = useState([]);
  const token = localStorage.getItem("token");

  const fetchCart = () => {
    fetch("http://localhost:8080/api/cart", {
      headers: {
        Authorization: "Bearer " + token
      }
    })
      .then(res => res.json())
      .then(data => setCart(data));
  };

  useEffect(() => {
    fetchCart();
  }, []);

  // ➕ Increase / ➖ Decrease
  const updateQty = async (id, newQty) => {
    if (newQty < 1) return;

    await fetch(`http://localhost:8080/api/cart/update/${id}?quantity=${newQty}`, {
      method: "PUT",
      headers: {
        Authorization: "Bearer " + token
      }
    });

    fetchCart(); // refresh
  };

  // ❌ Remove item
  const deleteItem = async (id) => {
    await fetch(`http://localhost:8080/api/cart/delete/${id}`, {
      method: "DELETE",
      headers: {
        Authorization: "Bearer " + token
      }
    });

    fetchCart();
  };

  const total = cart.reduce(
    (sum, item) => sum + item.product.price * item.quantity,
    0
  );

  return (
    <div style={{ padding: "20px" }}>
      <h2>Your Cart 🛒</h2>

      {cart.length === 0 ? (
        <h3>Cart is empty</h3>
      ) : (
        cart.map(item => (
          <div key={item.id} style={{
            border: "1px solid gray",
            padding: "10px",
            marginBottom: "10px"
          }}>

            <h3>{item.product.name}</h3>
            <p>₹ {item.product.price}</p>

            {/* 🔥 Quantity Controls */}
            <div>
              <button onClick={() => updateQty(item.id, item.quantity - 1)}>➖</button>
              <span style={{ margin: "0 10px" }}>{item.quantity}</span>
              <button onClick={() => updateQty(item.id, item.quantity + 1)}>➕</button>
            </div>

            <p>Total: ₹ {item.product.price * item.quantity}</p>

            {/* ❌ Remove */}
            <button onClick={() => deleteItem(item.id)}>
              Remove ❌
            </button>

          </div>
        ))
      )}

      <h2>Total Amount: ₹ {total}</h2>
    </div>
  );
}

export default CartPage;