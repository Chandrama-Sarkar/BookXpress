import React from 'react'
import { Link } from 'react-router-dom'
import Quantity from './Quantity';
import { useParams } from 'react-router-dom';
import { useEffect } from 'react';
import axios from 'axios';
import { useState } from 'react';
import authHeader from '../authHeader';


const CartProduct = () => {

    const userId = useParams();
    const [books, setBooks] = useState([]);
    useEffect(() => {
        loadBooks();
    }, []);
    //console.log(userId);

    const loadBooks = async () => {
        const result = await axios.get("http://localhost:8095/cartitems/getallItemsofuser/" + userId.userId, { headers: authHeader() });
        setBooks(result.data);
        console.log(result.data);

    }

    const deleteItemHandler = async (cartresponseId) => {
        const result = await axios.delete("http://localhost:8095/cartitems/" + cartresponseId, { headers: authHeader() });
        console.log(result.data);
        alert("Item removed from Cart!");
        loadBooks();
        window.location.reload();
    }
    return (
        books.map((book, index) => (
            <div class="product">
                <div class="left">
                    <img class="cover-photo" src="https://img.freepik.com/free-psd/book-hardcover-mockup-three-views_125540-226.jpg?w=2000" width="100" alt="" />
                    <div class="book-desc">
                        <div class="book-name">
                            {book.bookName}
                        </div>
                        <div class="book-author">
                            {book.author}
                        </div>
                        <div class="book-categories">
                            Category: <Link to="" class="category">
                                {book.category}
                            </Link>
                        </div>
                    </div>
                </div>
                <div class="right">
                    <div class="flex">
                        <div class="price">
                            <i class="bi-currency-rupee">{book.price}</i>

                        </div>
                        {/* <Quantity /> */}

                    </div>
                    <button class="remove-product" onClick={() => deleteItemHandler(book.cartresponseId)}>
                        <i class="bi-trash3"></i>
                        Remove
                    </button>
                </div>
            </div>
        )
        )
    )
}

export default CartProduct