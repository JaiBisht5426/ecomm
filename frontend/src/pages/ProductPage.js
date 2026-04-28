import React, { useEffect, useState } from "react";
import "./Product.css";
import { useNavigate } from "react-router-dom";
// import Navbar from "../components/Navbar";


function ProductPage() {

  const navigate = useNavigate();
  const [products, setProducts] = useState([]);
  const [newProduct, setNewProduct] = useState({
    name: "",
    description: "",
    price: "",
    quantity: "",
    category: "",
    imageUrl: ""
  });

  const token = localStorage.getItem("token");

  // ✅ Fetch products
  const fetchProducts = async () => {
    const res = await fetch("http://localhost:8080/api/products", {
      headers: {
        Authorization: "Bearer " + token
      }
    });

    const data = await res.json();
    setProducts(data);
  };

  useEffect(() => {
    fetchProducts();
  }, []);

  // ✅ Handle input
  const handleChange = (e) => {
    setNewProduct({
      ...newProduct,
      [e.target.name]: e.target.value
    });
  };

  // ✅ Add product
  const handleSubmit = async (e) => {
    e.preventDefault();

    try{
    const res = await fetch("http://localhost:8080/api/products", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: "Bearer " + token
      },
      body: JSON.stringify(newProduct)
    });

    const msg = await res.text();
      alert(msg);

      fetchProducts(); // refresh list

    } catch (err) {
      alert("Error ❌");
    }
  };

  const handleLogout = () => {
    localStorage.removeItem("token");
    alert("Logged out ✅");
    navigate("/login");
  };
//     setNewProduct({
//       name: "",
//       description: "",
//       price: "",
//       quantity: "",
//       category: "",
//       imageUrl: ""
//     });

//     fetchProducts();
//   };

  return (
    <div className="product-container">

      <h1>🛍️ Products</h1>

      {/* FORM */}
      <div className="form-card">
        <h3>Add Product</h3>

        <form onSubmit={handleSubmit}>
          <input name="name" placeholder="Name" value={newProduct.name} onChange={handleChange} />
          <input name="description" placeholder="Description" value={newProduct.description} onChange={handleChange} />
          <input name="price" placeholder="Price" value={newProduct.price} onChange={handleChange} />
          <input name="quantity" placeholder="Quantity" value={newProduct.quantity} onChange={handleChange} />
          <input name="category" placeholder="Category" value={newProduct.category} onChange={handleChange} />
          <input name="imageUrl" placeholder="Image URL" value={newProduct.imageUrl} onChange={handleChange} />

          <button type="submit">Add Product</button>
        </form>
      </div>

      <button onClick={handleLogout}>
        Logout
      </button>

      {/* PRODUCT GRID */}
      <div className="product-grid">

        {products.map((p) => (
          <div key={p.id} className="product-card">

            <img src={p.imageUrl} alt={p.name} />

            <h3>{p.name}</h3>
            <p>{p.description}</p>

            <div className="price">₹ {p.price}</div>
            <div className="qty">Qty: {p.quantity}</div>
            <div className="category">{p.category}</div>

          </div>
        ))}

      </div>

    </div>
  );
}

export default ProductPage;
