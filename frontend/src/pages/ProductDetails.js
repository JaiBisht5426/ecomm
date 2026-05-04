import React, { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";

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
      .then(res => res.json())
      .then(data => setProduct(data));
  }, [id]);

  const handleAddToCart = async () => {
    const res = await fetch("http://localhost:8080/api/cart", {
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

    const msg = await res.text();
    alert(msg);
  };

  if (!product) return <h2>Loading...</h2>;

  return (
    <div>
      <button onClick={() => navigate("/viewproducts")}>⬅ Back</button>

      <h1>{product.name}</h1>
      <img src={product.imageUrl} alt="" width="200" />
      <p>{product.description}</p>
      <h3>₹ {product.price}</h3>

      <input
        type="number"
        value={qty}
        min="1"
        onChange={(e) => setQty(e.target.value)}
      />

      <button onClick={handleAddToCart}>Add to Cart 🛒</button>
      <button onClick={() => navigate("/cart")}>Go to Cart 🛒</button>
    </div>
  );
}

export default ProductDetails;
