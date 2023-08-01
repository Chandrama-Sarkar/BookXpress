import { Routes, Route } from "react-router-dom";
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import Navbar from "./components/Navbar";
import Store from "./components/Store";
import Login from "./components/Login";
import Signup from "./components/Signup";
import Cart from "./components/Cart";
import AdminHome from "./components/AdminHome";
import AddBooks from "./components/AddBooks";
import EditBooks from "./components/EditBooks";

function App() {
  return (
    <div className="App">



      <Routes>
        <Route path="/userdashboard/:userId" element={<Store />} />
        <Route path="/" element={<Login />} />
        <Route path="/signup" element={<Signup />} />
        <Route path="/cart/:userId" element={<Cart />} />
        <Route path="/adminhome/:userId" element={<AdminHome />} />
        <Route path="/addbook" element={<AddBooks />} />
        <Route path="/editbook/:bookId" element={<EditBooks />} />

      </Routes>
    </div>
  );
}

export default App;
