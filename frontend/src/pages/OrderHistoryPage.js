import React, { useEffect, useState } from "react";

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
    <div style={{ padding: "20px" }}>

      <h2>My Orders 📦</h2>

      {
        orders.map(order => (

          <div
            key={order.id}
            style={{
              border: "1px solid #ddd",
              padding: "20px",
              marginBottom: "20px",
              borderRadius: "10px"
            }}
          >

            <h3>Order #{order.id}</h3>

            <p>Status: {order.status}</p>

            <p>Total: ₹ {order.totalAmount}</p>

            <p>Payment: {order.paymentMethod}</p>

            <p>Date: {order.orderDate}</p>

            <h4>Items:</h4>

            {
              order.items.map((item, index) => (

                <div key={index}>

                  <p>
                    {item.productName}
                    × {item.quantity}
                  </p>

                </div>
              ))
            }

          </div>
        ))
      }

    </div>
  );
}

export default OrderHistoryPage;