import { Link } from "react-router-dom";
import "./Navbar.css";

function Navbar() {
  return (
    <div className="navbar">
      <div className="home">
        <Link to="/">Konpeito</Link>
      </div>
      <div className="explore">
        <Link to="/about">About</Link>
      </div>
    </div>
  );
}

export default Navbar;
