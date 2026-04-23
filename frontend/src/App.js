import logo from './logo.svg';
import './App.css';
import RegisterPage from "./pages/RegisterPage";
import LoginPage from './pages/LoginPage';
import Dashboard from "./pages/Dashboard";


function App() {
  return (

    <div style={{ textAlign: "center" }}>
      <h1>E-Commerce App</h1>

      <RegisterPage />
      <hr />

      <LoginPage />
      <hr />

      <Dashboard />
    </div>
  );
}

export default App;
