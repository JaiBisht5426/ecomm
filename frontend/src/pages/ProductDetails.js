import React, { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import "./ProductDetails.css";

function ProductDetails() {

  const { id } = useParams();
  const navigate = useNavigate();

  const [product, setProduct] = useState(null);
  const [qty, setQty] = useState(1);

  const token = localStorage.getItem("token");

  useEffect(() => {
    fetch(`http://localhost:8080/api/products/${id}`, {
      headers: {
        Authorization: "Bearer " + token
      }
    })
      .then(res => {
        if (!res.ok) throw new Error("Not found");
        return res.json();
      })
      .then(data => setProduct(data))
      .catch(() => navigate("/viewproducts"));
  }, [id]);

  const handleAddToCart = async () => {
    await fetch("http://localhost:8080/api/cart", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: "Bearer " + token
      },
      body: JSON.stringify({
        product: { id: product.id },
        quantity: Number(qty)
      })
    });

    alert("Added to cart ✅");
  };

  if (!product) return <h2>Loading...</h2>;

  return (
    <div className="details-container">

      <button className="back-btn" onClick={() => navigate("/viewproducts")}>
        ⬅ Back
      </button>

      <div className="details-grid">

        {/* LEFT - IMAGE */}
        <div className="image-section">
          <img src={product.imageUrl} alt={product.name} />
        </div>

        {/* MIDDLE - INFO */}
        <div className="info-section">
          <h1>{product.name}</h1>

          <p className="desc">{product.description}</p>

          <h2 className="price">₹ {product.price}</h2>

          <p><b>Category:</b> {product.category}</p>
          <p><b>Stock:</b> {product.quantity}</p>

          <ul className="about">
            <li>High quality product</li>
            <li>Fast delivery available</li>
            <li>Cash on delivery supported</li>
            <li>Easy return policy</li>
          </ul>
        </div>

        {/* RIGHT - BUY BOX */}
        <div className="buy-section">

          <h2>₹ {product.price}</h2>

          <p className="stock">
            {product.quantity > 0 ? "In Stock ✅" : "Out of Stock ❌"}
          </p>

          <input
            type="number"
            min="1"
            value={qty}
            onChange={(e) => setQty(e.target.value)}
          />

          <button className="cart-btn" onClick={handleAddToCart}>
            Add to Cart 🛒
          </button>

          <button className="cart-btn" onClick={() => navigate("/cart")}>Go to Cart 🛒</button>
          <button className="buy-btn">
            Buy Now ⚡
          </button>

        </div>

      </div>
    </div>
  );
}

export default ProductDetails;