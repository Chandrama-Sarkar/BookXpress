import React from 'react';
import { Link } from 'react-router-dom';
import { useState, useEffect } from 'react';
import axios from 'axios';
import authHeader from '../authHeader';
import { useParams } from 'react-router-dom';
const Product = () => {

	const [books, setBooks] = useState([]);
	const { userId } = useParams();
	const [bookId, setBookId] = useState('');
	const [quantity, setQuantity] = useState('');

	useEffect(() => {
		loadBooks();
		setQuantity(1);

	}, []);

	const loadBooks = async () => {
		const result = await axios.get("http://localhost:8091/books/all", { headers: authHeader() });
		setBooks(result.data);
	}

	const addToCartHandler = async (bookId) => {

		setBookId(bookId);
		const cartInput = { bookId, quantity, userId };
		console.log(cartInput);
		console.log(bookId);
		console.log(userId);
		try {
			const result = await axios.post("http://localhost:8095/cartitems/add", cartInput, { headers: authHeader() });
			console.log(result.data);
			alert("Book Added To Cart Successfully!!");
		}
		catch (err) {
			alert("Each Book Can Be Added To Cart Only Once!");
			console.log(err);
		}

	}



	return (
		books.map((book, index) => (
			<div class="product">

				<div
					className="image-container"
					style={{
						backgroundImage:
							"url('https://static-cse.canva.com/blob/921464/ComfortingRomanceBookCover.jpg')",
					}}
				>
					<img
						className="cover-photo"
						src="https://static-cse.canva.com/blob/921464/ComfortingRomanceBookCover.jpg"
						alt=""
					/>
				</div>



				<div class="book-desc">
					<div className="book-name">{book.bookName}</div>
					<div className="book-author">By {book.author}</div>
					<div className="book-categories">
						<Link to="" className="category">
							{book.category}
						</Link>
					</div>
				</div>
				<div className="right">
					<div className="flex">
						<div className="price">
							<i className="bi-currency-rupee"></i>{book.price}
						</div>
					</div>
					<button className="add-to-cart" key={book} onClick={() => { addToCartHandler(book.bookId) }}>
						<i className="bi-bag"></i>&nbsp;&nbsp;Add to Cart
					</button>
				</div>

			</div>
		)
		)
	);
};

export default Product;
