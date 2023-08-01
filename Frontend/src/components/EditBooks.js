import React, { useEffect, useState } from 'react'
import axios from 'axios';
import { Link, useNavigate, useParams } from "react-router-dom";
import authHeader from './authHeader';
export default function EditBooks() {

    const { userId } = useParams();
    let navigate = useNavigate();
    const [errorMessage, setErrorMessage] = useState('');
    const { bookId } = useParams();
    const [book, setBook] = useState({
        bookName: "",
        author: "",
        category: "",
        price: ""
    });

    const onInputChange = (e) => {
        setBook({ ...book, [e.target.name]: e.target.value });

    };
    useEffect(() => {
        loadBook();
    }, []);


    const { bookName, author, category, price } = book;


    const loadBook = async () => {
        const result = await axios.get(`http://localhost:8091/books/getbookbyid/${bookId}`, { headers: authHeader() });
        setBook(result.data);
    };




    const onSubmit = async (e) => {
        e.preventDefault();

        if (bookName.trim() === '') {
            setErrorMessage('Book Name cannot be blank!');
        }
        else if (author.trim() === '') {
            setErrorMessage('Author cannot be blank!');
        }
        else if (category.trim() === '') {
            setErrorMessage('Category cannot be blank!');
        }
        else if (price === '0' || price === '') {
            setErrorMessage('Enter valid price!');
        }
        else {
            try {
                await axios.put(`http://localhost:8091/books/${bookId}`, book, { headers: authHeader() });
                alert("Book detail updated successfully!!");
                navigate("/adminhome/" + userId);
            }
            catch (err) {
                alert("Book is already added!");
            }

        }


    }


    const cancelHandler = (e) => {
        e.preventDefault();
        navigate("/adminhome/" + userId);
    }


    return (

        <div className="container">
            <div className='row'>
                <div className='col-md-6 offset-md-3 border rounded p-4 mt-2 shadow '>
                    <h2 className="text-center m-4">Edit This Book</h2>
                    <form onSubmit={(e) => onSubmit(e)}>
                        <div className="mb-3">
                            <label htmlFor="Bookname" className="form-label">Book Name</label>
                            <input
                                type={"text"}
                                className='form-control'
                                placeholder="Enter Book Name"
                                name="bookName"
                                value={bookName}
                                onChange={(e) => onInputChange(e)}
                            />

                        </div>

                        <div className="mb-3">
                            <label htmlFor="Author" className="form-label">Author</label>
                            <input
                                type={"text"}
                                className='form-control'
                                placeholder="Enter Author"
                                name="author"
                                value={author}
                                onChange={(e) => onInputChange(e)}
                            />

                        </div>


                        <div className="mb-3">
                            <label htmlFor="Category" className="form-label">Category</label>
                            <input
                                type={"text"}
                                className='form-control'
                                placeholder="Enter Category"
                                name="category"
                                value={category}
                                onChange={(e) => onInputChange(e)}
                            />

                        </div>


                        <div className="mb-3">
                            <label htmlFor="Price" className="form-label">Price</label>
                            <input
                                type={"text"}
                                className='form-control'
                                placeholder="Enter Price"
                                name="price"
                                value={price}
                                onChange={(e) => onInputChange(e)}
                            />

                        </div>
                        {errorMessage && <p style={{ color: "red" }}>{errorMessage}</p>}
                        <button type="submit" className='btn btn-outline-primary'>Submit</button>
                        <button type="submit" className='btn btn-outline-danger mx-2' onClick={cancelHandler}>Cancel</button>
                    </form>


                </div>



            </div>
        </div>
    )
}
