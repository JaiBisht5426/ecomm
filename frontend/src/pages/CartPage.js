import React, { useEffect, useState } from "react";
import "./Cart.css";
import { useNavigate } from "react-router-dom";

function CartPage() {

  const [cart, setCart] = useState([]);

  const token = localStorage.getItem("token");

  const navigate = useNavigate();

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

    await fetch(
      `http://localhost:8080/api/cart/update/${id}?quantity=${newQty}`,
      {
        method: "PUT",
        headers: {
          Authorization: "Bearer " + token
        }
      }
    );

    fetchCart();
  };

  const deleteItem = async (id) => {

    await fetch(
      `http://localhost:8080/api/cart/delete/${id}`,
      {
        method: "DELETE",
        headers: {
          Authorization: "Bearer " + token
        }
      }
    );

    fetchCart();
  };

  // 🔥 TOTAL
  const total = cart.reduce(
    (sum, item) =>
      sum + item.product.price * item.quantity,
    0
  );

  // 🔥 PAYMENT FUNCTION
  const handlePayment = async () => {

    try {

      const res = await fetch(
        "http://localhost:8080/api/payment/create-order",
        {
          method: "POST",
          headers: {
            Authorization: "Bearer " + token
          }
        }
      );

      const order = await res.json();

      const options = {

        key: "rzp_test_So76F3CZHFymUy",

        amount: order.amount,

        currency: order.currency,

        name: "My Ecommerce",

        description: "Order Payment",

        order_id: order.id,

        handler: async function (response) {

          alert("Payment Successful ✅");

          // 🔥 checkout after payment
          await fetch(
            "http://localhost:8080/api/orders/checkout",
            {
              method: "POST",
              headers: {
                Authorization: "Bearer " + token
              }
            }
          );

          alert("Order Placed ✅");

          window.location.reload();
        }
      };

      const razor = new window.Razorpay(options);

      razor.open();

    } catch (err) {

      console.error(err);

      alert("Something went wrong ❌");
    }
  };

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
              <img
                src={item.product.imageUrl}
                alt=""
              />

              {/* INFO */}
              <div className="cart-info">

                <h3>{item.product.name}</h3>

                <p className="price">
                  ₹ {item.product.price}
                </p>

                <div className="qty-box">

                  <button
                    onClick={() =>
                      updateQty(
                        item.id,
                        item.quantity - 1
                      )
                    }
                  >
                    ➖
                  </button>

                  <span>{item.quantity}</span>

                  <button
                    onClick={() =>
                      updateQty(
                        item.id,
                        item.quantity + 1
                      )
                    }
                  >
                    ➕
                  </button>

                </div>

                <p className="item-total">
                  Total: ₹
                  {item.product.price * item.quantity}
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

        {/* 🔥 PAYMENT BUTTON */}
        <button
          className="checkout-btn"
          onClick={handlePayment}
        >
          Proceed to Checkout 💳
        </button>

      </div>

    </div>
  );
}

export default CartPage;