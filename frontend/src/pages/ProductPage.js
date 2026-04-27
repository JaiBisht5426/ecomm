import React, { useEffect, useState } from "react";

function ProductPage() {

  const [products, setProducts] = useState([]);

  const [newProduct, setNewProduct] = useState({
    name: "",
    category:"",
    description: "",
    price: "",
    quantity: "",
    imageUrl: ""
  });

  const token = localStorage.getItem("token");

  // ✅ Get all products
  const fetchProducts = async () => {
    try {
      const res = await fetch("http://localhost:8080/api/products", {
        headers: {
          "Authorization": "Bearer " + token
        }
      });

      const data = await res.json();
      setProducts(data);

    } catch (err) {
      console.error(err);
    }
  };

  useEffect(() => {
    fetchProducts();
  }, []);

  // ✅ Handle input change
  const handleChange = (e) => {
    setNewProduct({
      ...newProduct,
      [e.target.name]: e.target.value
    });
  };

  // ✅ Add product
  const handleAddProduct = async (e) => {
    e.preventDefault();

    try {
      const res = await fetch("http://localhost:8080/api/products", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          "Authorization": "Bearer " + token
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

  return (
    <div style={{ textAlign: "center" }}>
      <h2>Products</h2>

      {/* Add Product Form */}
      <form onSubmit={handleAddProduct}>
        <input name="name" placeholder="Name" onChange={handleChange} /><br />
        <input name="category" placeholder="Category" onChange={handleChange} /><br />
        <input name="description" placeholder="Description" onChange={handleChange} /><br />
        <input name="price" placeholder="Price" onChange={handleChange} /><br />
        <input name="quantity" placeholder="Quantity" onChange={handleChange} /><br />
        <input name="imageUrl" placeholder="Image URL" onChange={handleChange} /><br />
        <button type="submit">Add Product</button>
      </form>

      <hr />

      {/* Product List */}
      {products.map((p) => (
        <div key={p.id} style={{ border: "1px solid black", margin: "10px", padding: "10px" }}>
          <h3>{p.name}</h3>
          <p>{p.category}</p>
          <p>{p.description}</p>
          <p>₹ {p.price}</p>
          <p>Qty: {p.quantity}</p>
        </div>
      ))}
    </div>
  );
}

export default ProductPage;
