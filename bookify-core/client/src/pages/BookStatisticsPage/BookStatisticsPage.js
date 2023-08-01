import React, {useEffect, useState} from 'react';
import {useNavigate} from "react-router";
import axios from "axios";
import {getToken} from "../../utils/JwtToken";

const BookStatisticsPage = () => {

    const navigate = useNavigate();
    const [books, setBooks] = useState([]);

    useEffect(() => {
        axios.get("http://localhost:8081/book", {
            headers: {
                'Authorization': 'Bearer ' + getToken()
            }
        }).then(response => {
            setBooks(response.data);
        });
    }, []);

    const navigateToBookPage = (bookId) => {
        navigate("/books/" + bookId);
    }

    if (!books) return null;

    return (
        <div>
            <div className="flex justify-center">
                <h2 className="text-xl font-medium text-gray-900 mb-5">
                    История заказов
                </h2>
            </div>
            <div className="relative overflow-x-auto shadow-md sm:rounded-lg mx-20 mt-5">
                <table className="w-full text-sm text-left text-gray-500 dark:text-gray-400">
                    <thead className="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
                    <tr>
                        <th scope="col" className="px-6 py-3">Номер книги</th>
                        <th scope="col" className="px-6 py-3">Название</th>
                        <th scope="col" className="px-6 py-3">Автор</th>
                        <th scope="col" className="px-6 py-3">Издатель</th>
                        <th scope="col" className="px-6 py-3">Жанр</th>
                        <th scope="col" className="px-6 py-3">Цена</th>
                        <th scope="col" className="px-6 py-3">Количество заказов</th>
                        <th scope="col" className="px-6 py-3">Выручка</th>
                        <th scope="col" className="px-6 py-3">На складе, шт.</th>
                    </tr>
                    </thead>
                    <tbody>
                    {books.map((book) => (
                        <tr className="bg-white border-b dark:bg-gray-800 dark:border-gray-700">
                            <td className="px-6 py-4">{book.id}</td>
                            <th scope="row"
                                className="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white hover:cursor-pointer"
                                onClick={() => navigateToBookPage(book.id)}>
                                {book.title}</th>
                            <td className="px-6 py-4">{book.genre.name}</td>
                            <td className="px-6 py-4">{book.author}</td>
                            <td className="px-6 py-4">{book.publisher}</td>
                            <td className="px-6 py-4">{book.price.toFixed(1)} BYN</td>
                            <td className="px-6 py-4">{book.orderNum}</td>
                            <td className="px-6 py-4">{(book.orderNum * book.price).toFixed(1)} BYN</td>
                            <td className="px-6 py-4">{book.storageNum}</td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
};

export default BookStatisticsPage;
