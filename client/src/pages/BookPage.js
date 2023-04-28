import React, {useEffect, useState} from 'react';
import axios from "axios";
import {getToken} from "../utils/JwtToken";
import {useParams} from "react-router";

const BookPage = () => {

    const [book, setBook] = useState(null);
    const {id} = useParams();
    const url = "http://localhost:8081/book/" + id

    useEffect( () => {
        axios.get(url, {
            headers: {
                'Authorization': 'Bearer ' + getToken()
            }
        })
            .then(response => {
                setBook(response.data);
            });
    }, []);

    if (!book) return null;

    return (
        <section className="text-gray-600 body-font overflow-hidden">
            <div className="container  py-12 mx-auto">
                <div className="lg:w-4/5 ml-auto flex flex-wrap">
                    <img alt="ecommerce" className="lg:w-1/3 w-50 lg:h-auto h-64 object-cover object-center rounded"
                         src = {"http://localhost:8081/book/img/" + book.id + ".jpg"}/>
                    <div className="lg:w-1/2 w-full lg:pl-10 lg:py-6 mt-6 lg:mt-0">
                        <h2 className="text-sm title-font text-gray-500 tracking-widest">{book.genre.name}</h2>
                        <h1 className="text-gray-900 text-3xl title-font font-medium mb-1">{book.title}</h1>
                        <h1 className="text-gray-900 text-2xl title-font mb-1">{book.author.fullName}</h1>
                        <div className="flex mb-4">
                            {book.description}
                        </div>
                        <p className="leading-relaxed"></p>
                        <div className="flex mt-6 items-center pb-5 border-b-2 border-gray-100 mb-5"/>
                        <div className="flex">
                            <span className="title-font font-medium text-2xl text-gray-900">{book.price} BYN</span>
                            <button
                                className="flex ml-auto text-white bg-indigo-500 border-0 py-2 px-6 focus:outline-none hover:bg-indigo-600 rounded">Добавить
                                в корзину
                            </button>
                            <button
                                className="rounded-full w-10 h-10 bg-gray-200 p-0 border-0 inline-flex items-center justify-center text-gray-500 ml-4">
                                <svg fill="currentColor" strokeLinecap="round" strokeLinejoin="round"
                                     strokeWidth="2" className="w-5 h-5" viewBox="0 0 24 24">
                                    <path
                                        d="M20.84 4.61a5.5 5.5 0 00-7.78 0L12 5.67l-1.06-1.06a5.5 5.5 0 00-7.78 7.78l1.06 1.06L12 21.23l7.78-7.78 1.06-1.06a5.5 5.5 0 000-7.78z"></path>
                                </svg>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    );
};

export default BookPage;
