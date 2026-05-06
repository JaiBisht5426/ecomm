import React, { useEffect, useState } from "react";
import "./Product.css";
import { useNavigate } from "react-router-dom";
// import Navbar from "../components/Navbar";


function ViewProductPage() {

  const navigate = useNavigate();
  const [products, setProducts] = useState([]);
  const [search, setSearch] = useState("");

  const [category, setCategory] = useState("");
  //   const [price, setPrice] = useState("");
  const [minPrice, setMinPrice] = useState("");
  const [maxPrice, setMaxPrice] = useState("");
  const [sortOrder, setSortOrder] = useState("");

  const token = localStorage.getItem("token");

  // ✅ Fetch products
  const fetchProducts = async () => {
    try {
      const res = await fetch("http://localhost:8080/api/products/viewproducts", {
        headers: {
          Authorization: "Bearer " + token
        }
      });

      const data = await res.json();
      setProducts(data);
    }

    catch (err) {
      console.error("Error:", err);
    }
  };

  useEffect(() => {
    fetchProducts();
  }, []);

  const filteredProducts = products.filter(p =>
    p.name.toLowerCase().includes(search.toLowerCase()) &&
    (category === "" || p.category === category) &&
    (minPrice === "" || p.price >= minPrice) &&
    (maxPrice === "" || p.price <= maxPrice)
  );

  const sortedProducts = [...filteredProducts].sort((a, b) => {
    if (sortOrder === "low") {
      return a.price - b.price;
    } else if (sortOrder === "high") {
      return b.price - a.price;
    } else {
      return 0;
    }
  });

  const handleLogout = () => {
    localStorage.removeItem("token");
    alert("Logged out ✅");
    navigate("/login");
  };

  return (
    <div className="page">

      {/* 🔝 HEADER */}
      <div className="header">
        <h2>🛍️ Products</h2>

        <div className="header-actions">
          <input
            type="text"
            placeholder="Search products..."
            value={search}
            onChange={(e) => setSearch(e.target.value)}
          />

          <button onClick={() => navigate("/cart")}>
            🛒 Cart
          </button>

          <button onClick={handleLogout}>
            Logout
          </button>
        </div>
      </div>

      <div className="main">

        {/* 🧱 LEFT FILTER SIDEBAR */}
        <div className="sidebar">

          <h3>Filters</h3>

          <label>Category</label>
          <select onChange={(e) => setCategory(e.target.value)}>
            <option value="">All</option>
            <option value="Electronics">Electronics</option>
            <option value="Clothing">Clothing</option>
            <option value="Books">Books</option>
          </select>

          <label>Sort</label>
          <select onChange={(e) => setSortOrder(e.target.value)}>
            <option value="">None</option>
            <option value="low">Price Low → High</option>
            <option value="high">Price High → Low</option>
          </select>

          <label>Min Price</label>
          <input type="number" onChange={(e) => setMinPrice(e.target.value)} />

          <label>Max Price</label>
          <input type="number" onChange={(e) => setMaxPrice(e.target.value)} />

        </div>

        {/* 🛒 PRODUCT GRID */}
        <div className="products">

          {
            sortedProducts.map((p) => (
              <div
                key={p.id}
                className="card"
                onClick={() => navigate(`/viewproducts/${p.id}`)}
              >

                <img src={p.imageUrl} alt="" />

                <h3>{p.name}</h3>

                <p className="desc">{p.description}</p>

                <h4>₹ {p.price}</h4>

                <p className="stock">
                  {p.quantity > 0 ? "In Stock" : "Out of Stock"}
                </p>

                <button
                  onClick={(e) => {
                    e.stopPropagation(); // 🔥 IMPORTANT
                    navigate("/cart");
                  }}
                >
                  Go to Cart 🛒
                </button>

              </div>
            ))
          }

        </div>

      </div>
    </div>
  );
}

export default ViewProductPage;
