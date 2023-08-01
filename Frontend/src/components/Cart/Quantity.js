import React from 'react'
import { useState } from 'react';
import authHeader from '../authHeader';
import { useParams } from 'react-router-dom';
import { useEffect } from 'react';
import axios from 'axios';
const Quantity = () => {

  const userId = useParams();
  const [books, setBooks] = useState([]);
  useEffect(() => {
    loadBooks();
  }, []);
  console.log(userId);

  const loadBooks = async () => {
    const result = await axios.get("http://localhost:8095/cartitems/getallItemsofuser/" + userId.userId, { headers: authHeader() });
    setBooks(result.data);
    console.log(result.data.quantity);
    console.log(result.data.bookId);
  }

  return (
    books.map((book, index) => (
      <div class="quantity-container">
        <button className="minus" >
          <i className="bi-dash-square-fill"></i>
        </button>
        <div className="quantity">
          {book.quantity}
        </div>
        <button className="plus" >
          <i className="bi-plus-square-fill"></i>
        </button>
      </div>
    )
    )
  )
}

export default Quantity