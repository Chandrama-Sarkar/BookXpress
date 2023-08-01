import React, { useEffect } from 'react'
import { Link } from 'react-router-dom'
import CartProduct from './Cart/CartProduct'
import { useParams } from 'react-router-dom'
import authHeader from './authHeader'
import { useState } from 'react'
import axios from 'axios'
const Cart = () => {
    const { userId } = useParams();
    console.log("FROM cart.js " + userId);

    const [totalPrice, setTotalPrice] = useState('');

    useEffect(() => {
        getTotalBill(userId);
    }, [])
    const getTotalBill = async (userId) => {
        const result = await axios.get("http://localhost:8095/cartitems/gettotalamount/" + userId, { headers: authHeader() });
        console.log(result.data);
        setTotalPrice(result.data);


    }
    function orderHandler() {
        alert("Your order of Rs." + totalPrice + " has been successfully placed!!");

    }


    return (
        <section id="cart-page">
            <fieldset class="shopping-cart">
                <legend class="cart-legend">Your Cart</legend>
                <div class="products">
                    <CartProduct getTotalBill={getTotalBill(userId)} />
                </div>

                <hr class="total-divider" />

                <div class="cart-totals">
                    <div>Total Bill: </div>
                    <div>
                        <i class="bi-currency-rupee">{totalPrice}</i>
                    </div>
                </div>
            </fieldset>

            <div class="cart-actions">
                <Link to={`/userdashboard/${userId}`}>
                    <button class="back">
                        <i class="bi-chevron-double-left"></i> Back to store
                    </button>
                </Link>
                <button class="buy" onClick={orderHandler}>Place Order</button>
            </div>
        </section>
    )

}


export default Cart