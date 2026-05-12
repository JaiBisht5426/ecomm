import React, { useEffect, useState } from "react";
import "./Cart.css";

function CartPage() {

  const [cart, setCart] = useState([]);

  // 🔥 ADDRESS
  const [address, setAddress] = useState({
    fullName: "",
    phone: "",
    city: "",
    state: "",
    pincode: "",
    addressLine: ""
  });

  // 🔥 PAYMENT METHOD
  const [paymentMethod, setPaymentMethod] = useState("COD");

  const token = localStorage.getItem("token");

  // ✅ FETCH CART
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

  // ✅ UPDATE QUANTITY
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

  // ✅ DELETE ITEM
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

  // ✅ HANDLE ADDRESS INPUT
  const handleChange = (e) => {

    setAddress({
      ...address,
      [e.target.name]: e.target.value
    });
  };

  // ✅ TOTAL
  const total = cart.reduce(
    (sum, item) =>
      sum + item.product.price * item.quantity,
    0
  );

  // ✅ PLACE ORDER / PAYMENT
  const placeOrder = async () => {

    // 🔥 VALIDATION
    if (
      !address.fullName ||
      !address.phone ||
      !address.city ||
      !address.state ||
      !address.pincode ||
      !address.addressLine
    ) {
      alert("Please fill all address fields ❌");
      return;
    }

    // 🔥 COD FLOW
    if (paymentMethod === "COD") {

      await fetch(
        "http://localhost:8080/api/orders/checkout",
        {
          method: "POST",
          headers: {
            Authorization: "Bearer " + token,
            "Content-Type": "application/json"
          },
          body: JSON.stringify({
            address,
            paymentMethod
          })
        }
      );

      alert("Order Placed Successfully ✅");

      window.location.reload();
    }

    // 🔥 ONLINE PAYMENT FLOW
    else {

      try {

        // 🔥 CREATE RAZORPAY ORDER
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

        // 🔥 RAZORPAY OPTIONS
        const options = {

          key: "YOUR KEY",

          amount: order.amount,

          currency: order.currency,

          name: "My Ecommerce",

          description: "Order Payment",

          order_id: order.id,

          prefill: {
            name: address.fullName,
            contact: address.phone
          },

          theme: {
            color: "#3399cc"
          },

          handler: async function (response) {

            // 🔥 AFTER SUCCESS PAYMENT
            await fetch(
              "http://localhost:8080/api/orders/checkout",
              {
                method: "POST",
                headers: {
                  Authorization: "Bearer " + token,
                  "Content-Type": "application/json"
                },
                body: JSON.stringify({
                  address,
                  paymentMethod
                })
              }
            );

            alert("Payment Successful ✅");

            window.location.reload();
          }
        };

        // 🔥 OPEN RAZORPAY
        const razor = new window.Razorpay(options);

        razor.open();

      } catch (err) {

        console.error(err);

        alert("Payment Failed ❌");
      }
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

                {/* QUANTITY */}
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

                {/* REMOVE */}
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

        {/* ADDRESS */}
        <h3>Delivery Address 📍</h3>

        <input
          type="text"
          name="fullName"
          placeholder="Full Name"
          onChange={handleChange}
        />

        <input
          type="text"
          name="phone"
          placeholder="Phone Number"
          onChange={handleChange}
        />

        <input
          type="text"
          name="city"
          placeholder="City"
          onChange={handleChange}
        />

        <input
          type="text"
          name="state"
          placeholder="State"
          onChange={handleChange}
        />

        <input
          type="text"
          name="pincode"
          placeholder="Pincode"
          onChange={handleChange}
        />

        <textarea
          name="addressLine"
          placeholder="Full Address"
          onChange={handleChange}
        />

        {/* PAYMENT METHOD */}
        <h3>Payment Method 💳</h3>

        <select
          value={paymentMethod}
          onChange={(e) =>
            setPaymentMethod(e.target.value)
          }
        >
          <option value="COD">
            Cash On Delivery
          </option>

          <option value="ONLINE">
            Online Payment
          </option>
        </select>

        {/* SUMMARY */}
        <h3>Order Summary</h3>

        <p>Total Items: {cart.length}</p>

        <h2>₹ {total}</h2>

        {/* BUTTON */}
        <button
          className="checkout-btn"
          onClick={placeOrder}
        >
          Place Order 🚀
        </button>

      </div>

    </div>
  );
}

export default CartPage;