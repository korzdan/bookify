import React, {useEffect, useState} from 'react';
import axios from "axios";
import BookCard from "../../components/BookCard";
import {getToken} from "../../utils/JwtToken";

const BooksPage = () => {

    const [books, setBooks] = useState([]);

    useEffect(() => {
        axios.get("http://localhost:8081/book", {
            headers: {
                'Authorization': 'Bearer ' + getToken()
            }
        })
            .then(response => {
                setBooks(response.data);
            });
    }, []);

    return (
        <div>
            <section className="text-gray-600 body-font">
                <div className="container px-5 py-12 mx-auto my-0">
                    <div className="flex flex-wrap -m-4">
                        {
                            books.map((book, idx) => (
                                <BookCard key={idx} book={book}/>
                            ))
                        }
                    </div>
                </div>
            </section>
        </div>
    );
};

export default BooksPage;
