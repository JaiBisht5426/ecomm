import React, { useEffect, useState } from "react";
import "./Cart.css";

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

  const updateQty = async (id, newQty) => {
    if (newQty < 1) return;

    await fetch(`http://localhost:8080/api/cart/update/${id}?quantity=${newQty}`, {
      method: "PUT",
      headers: {
        Authorization: "Bearer " + token
      }
    });

    fetchCart();
  };

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
    <div className="cart-container">

      {/* LEFT SIDE */}
      <div className="cart-items">

        <h2>Shopping Cart 🛒</h2>

        {cart.length === 0 ? (
          <h3>Your cart is empty</h3>
        ) : (
          cart.map(item => (
            <div key={item.id} className="cart-card">

              {/* IMAGE */}
              <img src={item.product.imageUrl} alt="" />

              {/* INFO */}
              <div className="cart-info">
                <h3>{item.product.name}</h3>
                <p className="price">₹ {item.product.price}</p>

                <div className="qty-box">
                  <button onClick={() => updateQty(item.id, item.quantity - 1)}>➖</button>
                  <span>{item.quantity}</span>
                  <button onClick={() => updateQty(item.id, item.quantity + 1)}>➕</button>
                </div>

                <p className="item-total">
                  Total: ₹ {item.product.price * item.quantity}
                </p>

                <button
                  className="remove-btn"
                  onClick={() => deleteItem(item.id)}
                >
                  Remove ❌
                </button>
              </div>

            </div>
          ))
        )}

      </div>

      {/* RIGHT SIDE */}
      <div className="cart-summary">

        <h3>Order Summary</h3>

        <p>Total Items: {cart.length}</p>

        <h2>₹ {total}</h2>

        <button className="checkout-btn">
          Proceed to Checkout
        </button>

      </div>

    </div>
  );
}

export default CartPage;