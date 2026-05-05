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
    try
    {
    const res = await fetch("http://localhost:8080/api/products/viewproducts", {
      headers: {
        Authorization: "Bearer " + token
      }
    });

    const data = await res.json();
    setProducts(data);
    }

    catch(err)
    {
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

  const sortedProducts = [...filteredProducts].sort((a, b) => 
    {
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
    <div className="container">

      <h2>Products</h2>

      {/* 🔍 Search */}
      <input
        type="text"
        placeholder="Search product..."
        value={search}
        onChange={(e) => setSearch(e.target.value)}
        className="search-box"
      />

      <div className="filters">
        <select onChange={(e) => setCategory(e.target.value)}>
            <option value="">All Categories</option>
            <option value="Electronics">Electronics</option>
            <option value="Clothing">Clothing</option>
            <option value="Books">Books</option>
        </select>

        <select onChange={(e) => setSortOrder(e.target.value)}>
            <option value="">Sort By</option>
            <option value="low">Price: Low -{">"} High</option>
            <option value="high">Price: High -{">"} Low</option>
        </select>

                {/* Price */}
        <input
          type="number"
          placeholder="Min Price"
          onChange={(e) => setMinPrice(e.target.value)}
        />

        <input
          type="number"
          placeholder="Max Price"
          onChange={(e) => setMaxPrice(e.target.value)}
        />
      </div>
      <button onClick={handleLogout}>
        Logout
      </button>

      <div className="product-grid">

        {
          sortedProducts.map((p) => (
          <div key={p.id} className="product-card" onClick={() => navigate(`/viewproducts/${p.id}`)} style={{cursor:"pointer"}}>

            <img src={p.imageUrl} alt={p.name} />

            <h3>{p.name}</h3>
            <p>{p.description}</p>

            <div className="price">₹ {p.price}</div>
            <div className="qty">Qty: {p.quantity}</div>
            <div className="category">{p.category}</div>
            <button onClick={() => navigate("/cart")}>Go to Cart 🛒</button>
          </div>
        ))}
       </div>

      </div>
  );
}

export default ViewProductPage;
