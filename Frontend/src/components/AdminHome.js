import React, { useEffect, useState } from 'react'
import axios from 'axios';
import AdminNavbar from './AdminNavbar';
import { Link } from 'react-router-dom';
import authHeader from './authHeader';
import { useParams } from 'react-router-dom';
import Login from './Login';
export default function AdminHome() {

    const [books, setBooks] = useState([]);

    useEffect(() => {
        loadBooks();
    }, []);

    const loadBooks = async () => {
        const result = await axios.get("http://localhost:8091/books/all", { headers: authHeader() });
        setBooks(result.data);
    }

    const deleteBook = async (bookId) => {
        await axios.delete(`http://localhost:8091/books/${bookId}`, { headers: authHeader() });
        loadBooks();
        alert("Book Deleted Successfully!!");
    };

    return (
        <>
            <AdminNavbar />
            <div className='container'>
                <div className='py4'>
                    <table className="table border shadow">
                        <thead>
                            <tr>
                                <th scope="col">Book Id</th>
                                <th scope="col">Book Name</th>
                                <th scope="col">Author</th>
                                <th scope="col">Category</th>
                                <th scope="col">Price</th>
                                <th scope="col">Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                books.map((book, index) => (
                                    <tr>
                                        <th scope="row" key={index}>{book.bookId}</th>
                                        <td>{book.bookName}</td>
                                        <td>{book.author}</td>
                                        <td>{book.category}</td>
                                        <td>{book.price}</td>
                                        <td>
                                            {/* <button className='btn btn-primary mx-2'>View</button> */}
                                            <Link className="btn btn-primary mx-2" to={`/editbook/${book.bookId}`}>Edit</Link>
                                            <button className='btn btn-danger mx-2' onClick={() => deleteBook(book.bookId)}>Delete</button>
                                        </td>
                                    </tr>
                                ))
                            }



                        </tbody>
                    </table>
                </div>

            </div>
        </>
    )
}
