const handlePayment = async () => {

  try {

    // 🔥 Create Razorpay Order from backend
    const res = await fetch(
      "http://localhost:8080/api/payment/create-order",
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        }
      }
    );

    const order = await res.json();

    console.log(order);

    // 🔥 Razorpay options
    const options = {

      key: "rzp_test_So76F3CZHFymUy",

      amount: order.amount,

      currency: order.currency,

      name: "My Ecommerce",

      description: "Order Payment",

      image: "https://cdn-icons-png.flaticon.com/512/3081/3081559.png",

      order_id: order.id,

      theme: {
        color: "#3399cc"
      },

      // ✅ Payment Success
      handler: async function (response) {

        console.log("Payment Success:", response);

        alert("Payment Successful ✅");

        // 🔥 Call checkout API
        const checkoutRes = await fetch(
          "http://localhost:8080/api/orders/checkout",
          {
            method: "POST",

            headers: {
              Authorization: "Bearer " + token
            }
          }
        );

        const msg = await checkoutRes.text();

        alert(msg);

        // ✅ Redirect to orders page
        navigate("/orders");
      },

      // ✅ Prefill User Data
      prefill: {
        name: "Jaiprakash",
        email: "jaibisht5426@gmail.com",
        contact: "9999999999"
      },

      notes: {
        address: "Ecommerce App"
      }
    };

    // 🔥 Open Razorpay Popup
    const razorpayObject = new window.Razorpay(options);

    razorpayObject.on("payment.failed", function (response) {

      console.log(response);

      alert("Payment Failed ❌");
    });

    razorpayObject.open();

  } catch (err) {

    console.error(err);

    alert("Something went wrong ❌");
  }
};