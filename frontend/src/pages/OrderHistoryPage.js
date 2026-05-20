import React, { useEffect, useState } from "react";
import "./OrderHistory.css";

function OrderHistoryPage() {

  const [orders, setOrders] = useState([]);

  const token = localStorage.getItem("token");

  useEffect(() => {

    fetch("http://localhost:8080/api/orders/my-orders", {
      headers: {
        Authorization: "Bearer " + token
      }
    })
      .then(res => res.json())
      .then(data => setOrders(data));

  }, []);

  return (
    <div className="orders-page">

      <div className="orders-header">
        <h1>My Orders 📦</h1>
        <p>Track all your recent purchases</p>
      </div>

      {
        orders.length === 0 ? (

          <div className="empty-orders">
            <h2>No Orders Yet 😔</h2>
            <p>Looks like you haven't purchased anything.</p>
          </div>

        ) : (

          orders.map(order => (

            <div
              key={order.id}
              className="order-card"
            >

              {/* TOP */}
              <div className="order-top">

                <div>
                  <h3>Order #{order.id}</h3>

                  <p className="order-date">
                    {new Date(order.orderDate)
                      .toLocaleString()}
                  </p>
                </div>

                <div className="status-box">
                  {order.status}
                </div>

              </div>

              {/* ADDRESS */}
              <div className="address-section">

                <h4>Delivery Address 🚚</h4>

                <p>{order.fullName}</p>

                <p>{order.phone}</p>

                <p>
                  {order.addressLine},
                  {" "}
                  {order.city},
                  {" "}
                  {order.state}
                </p>

                {/* <p>{order.pincode}</p> */}

              </div>

              {/* ITEMS */}
              <div className="items-section">

                <h4>Items Ordered 🛍️</h4>

                {
                  order.items.map((item, index) => (

                    <div
                      key={index}
                      className="item-card"
                    >

                      <img
                        src={item.imageUrl}
                        alt=""
                      />

                      <div className="item-info">

                        <h3>{item.productName}</h3>

                        <p>
                          Quantity:
                          {" "}
                          {item.quantity}
                        </p>

                      </div>

                      <div className="item-price">
                        ₹ {item.price}
                      </div>

                    </div>
                  ))
                }

              </div>

              {/* BOTTOM */}
              <div className="order-bottom">

                <div>
                  <p>
                    Payment Method:
                    {" "}
                    <strong>
                      {order.paymentMethod}
                    </strong>
                  </p>
                </div>

                <div className="total-price">
                  ₹ {order.totalAmount}
                </div>

              </div>

            </div>
          ))
        )
      }

    </div>
  );
}

export default OrderHistoryPage;