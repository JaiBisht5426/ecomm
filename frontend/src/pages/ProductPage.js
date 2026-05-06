import React, { useEffect, useState } from "react";
import "./Product.css";
import { useNavigate } from "react-router-dom";

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

  const [editingProduct, setEditingProduct] = useState(null);

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

  // ✅ Handle input change
  const handleChange = (e) => {
    setNewProduct({
      ...newProduct,
      [e.target.name]: e.target.value
    });
  };

  // ✅ Submit (Add / Update)
  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      if (editingProduct) {
        // ✏️ UPDATE
        await fetch(`http://localhost:8080/api/products/${editingProduct.id}`, {
          method: "PUT",
          headers: {
            "Content-Type": "application/json",
            Authorization: "Bearer " + token
          },
          body: JSON.stringify(newProduct)
        });

        alert("Product Updated ✏️");
        setEditingProduct(null);

      } else {
        // ➕ ADD
        await fetch("http://localhost:8080/api/products", {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
            Authorization: "Bearer " + token
          },
          body: JSON.stringify(newProduct)
        });

        alert("Product Added ✅");
      }

      // Reset form
      setNewProduct({
        name: "",
        description: "",
        price: "",
        quantity: "",
        category: "",
        imageUrl: ""
      });

      fetchProducts();

    } catch (err) {
      alert("Error ❌");
    }
  };

  // ✅ Delete
  const handleDelete = async (id) => {

    const confirmDelete = window.confirm("Are you sure you want to delete?");
    if (!confirmDelete) return;

    await fetch(`http://localhost:8080/api/products/${id}`, {
      method: "DELETE",
      headers: {
        Authorization: "Bearer " + token
      }
    });

    alert("Product Deleted ❌");
    fetchProducts();
  };

  // ✅ Edit
  const handleEdit = (product) => {
    setEditingProduct(product);
    setNewProduct({
      name: product.name,
      description: product.description,
      price: product.price,
      quantity: product.quantity,
      category: product.category,
      imageUrl: product.imageUrl
    });
  };

  // ✅ Logout
  const handleLogout = () => {
    localStorage.removeItem("token");
    alert("Logged out ✅");
    navigate("/login");
  };

  return (
    <div className="admin-page">

      {/* HEADER */}
      <div className="admin-header">
        <h2>🛍️ Admin Panel</h2>
        <button onClick={handleLogout}>Logout</button>
      </div>

      <div className="admin-main">

        {/* LEFT - FORM */}
        <div className="admin-form">

          <h3>{editingProduct ? "Edit Product ✏️" : "Add Product ➕"}</h3>

          <form onSubmit={handleSubmit}>

            <input
              name="name"
              placeholder="Product Name"
              value={newProduct.name}
              onChange={handleChange}
              required
            />

            <textarea
              name="description"
              placeholder="Description"
              value={newProduct.description}
              onChange={handleChange}
              required
            />

            <input
              name="price"
              type="number"
              placeholder="Price"
              value={newProduct.price}
              onChange={handleChange}
              required
            />

            <input
              name="quantity"
              type="number"
              placeholder="Quantity"
              value={newProduct.quantity}
              onChange={handleChange}
              required
            />

            <input
              name="category"
              placeholder="Category"
              value={newProduct.category}
              onChange={handleChange}
              required
            />

            <input
              name="imageUrl"
              placeholder="Image URL"
              value={newProduct.imageUrl}
              onChange={handleChange}
              required
            />

            <button type="submit">
              {editingProduct ? "Update Product" : "Add Product"}
            </button>

          </form>
        </div>

        {/* RIGHT - PRODUCTS */}
        <div className="admin-products">

          <h3>All Products</h3>

          <div className="product-grid">

            {
              products.map((p) => (
                <div key={p.id} className="admin-card">

                  <img src={p.imageUrl} alt="" />

                  <h4>{p.name}</h4>

                  <p className="desc">{p.description}</p>

                  <p className="price">₹ {p.price}</p>

                  <p className="qty">Stock: {p.quantity}</p>

                  <span className="category">{p.category}</span>

                  {/* ACTION BUTTONS */}
                  <div className="actions">

                    <button
                      className="edit-btn"
                      onClick={() => handleEdit(p)}
                    >
                      Edit ✏️
                    </button>

                    <button
                      className="delete-btn"
                      onClick={() => handleDelete(p.id)}
                    >
                      Delete ❌
                    </button>

                  </div>

                </div>
              ))
            }

          </div>

        </div>

      </div>
    </div>
  );
}

export default ProductPage;