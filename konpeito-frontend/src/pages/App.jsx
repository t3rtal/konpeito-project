import { BrowserRouter, Routes, Route } from "react-router-dom";
import Home from "./Home.jsx";
import About from "./About.jsx";
import ApplicationDetails from "./ApplicationDetails.jsx";
import "../styles/App.css";

export default function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/application/:id" element={<ApplicationDetails />} />
        <Route path="/about" element={<About />} />
      </Routes>
    </BrowserRouter>
  );
}
