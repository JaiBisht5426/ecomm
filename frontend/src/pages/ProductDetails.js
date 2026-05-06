import React, { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import "./ProductDetails.css";

function ProductDetails() {

  const { id } = useParams();
  const navigate = useNavigate();

  const [product, setProduct] = useState(null);
  const [qty, setQty] = useState(1);
  const [selectedImage, setSelectedImage] = useState("");

  const token = localStorage.getItem("token");

  useEffect(() => {
    fetch(`http://localhost:8080/api/products/${id}`, {
      headers: {
        Authorization: "Bearer " + token
      }
    })
      .then(res => res.json())
      .then(data => {
        setProduct(data);
        setSelectedImage(data.imageUrl); // default image
      });
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

  // fake multiple images (later backend se bhi aa sakta hai)
  const images = [
    product.imageUrl,
    product.imageUrl,
    product.imageUrl,
    product.imageUrl
  ];

  return (
    <div className="details-container">

      <button onClick={() => navigate("/viewproducts")}>⬅ Back</button>

      <div className="details-grid">

        {/* LEFT - IMAGE GALLERY */}
        <div className="gallery">

          <div className="thumbnails">
            {images.map((img, index) => (
              <img
                key={index}
                src={img}
                alt=""
                onClick={() => setSelectedImage(img)}
              />
            ))}
          </div>

          <div className="main-image">
            <img src={selectedImage} alt="" />
          </div>

        </div>

        {/* MIDDLE - INFO */}
        <div className="info">

          <h1>{product.name}</h1>

          <p className="desc">{product.description}</p>

          <h2 className="price">₹ {product.price}</h2>

          <p><b>Category:</b> {product.category}</p>
          <p><b>Stock:</b> {product.quantity}</p>

          <h3>About this item</h3>
          <ul>
            <li>High quality material</li>
            <li>Durable & long lasting</li>
            <li>Fast delivery available</li>
            <li>Easy return policy</li>
          </ul>

        </div>

        {/* RIGHT - BUY BOX */}
        <div className="buy-box">

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
          <br />
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