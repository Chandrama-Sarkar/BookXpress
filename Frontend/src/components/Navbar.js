import React from 'react'
import { Link, useLocation } from 'react-router-dom'
import { useState } from 'react';
import authHeader from './authHeader';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const Navbar = (props) => {
    const [cartSize, setCartSize] = useState(0);
    let navigate = useNavigate();

    // const location = useLocation();
    // const userId = location.state.userId;
    console.log(props.userId.userId);

    return (
        <nav id="navbar">
            <div className="navbar-container">
                <div className="navbar-left">
                    <h1><Link to="">BookXpress</Link></h1>
                </div>
                <div className="navbar-middle">
                    <Link to="">Shop</Link>
                    <Link to="">Stories</Link>
                    <Link to="">Blog</Link>
                    <Link to="">About Us</Link>
                    <Link to="">Contact Us</Link>
                </div>
                <div className="navbar-right">
                    <Link to="/">
                        <i className="bi-people" style={{ fontSize: '20px' }}></i>
                        <span>Logout</span>
                    </Link>
                    <Link to={`/cart/${props.userId.userId}`}>
                        <i className="bi-cart" style={{ fontSize: '18px' }}></i>
                    </Link>
                </div>
            </div>
        </nav>

    )
}

export default Navbar