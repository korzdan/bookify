import React from 'react';
import {useDispatch} from "react-redux";
import {setItemInCart} from "../redux/Cart/reducer";
import {Link} from "react-router-dom";

const BookCard = ({book}) => {

    const dispatch = useDispatch();

    const handleClick = (e) => {
        e.stopPropagation();
        dispatch(setItemInCart(book))
    }

    return (
        <div className="lg:w-1/4 md:w-1/2 p-4 w-full">
            <Link to={"/books/" + book.id}>
                <a className="block relative h-48 rounded overflow-hidden">
                    <img alt="ecommerce" className="object-contain object-center w-full h-full block"
                         src={"http://localhost:8081/book/img/" + book.id + ".jpg"}/>
                </a>
            </Link>
            <div className="mt-4 flex flex-col items-center justify-center">
                <h3 className="text-gray-500 text-xs tracking-widest title-font mb-1">{book.genre.name}</h3>
                <h2 className="text-gray-900 title-font text-lg font-medium items-center">{book.title}</h2>
                <h2 className="text-gray-900 text-lg font-normal">{book.author.fullName}</h2>
                <p className="mt-1 font-semibold">{book.price} BYN</p>
                <button className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-3 rounded-full mt-2"
                        onClick={handleClick}>
                    Добавить в корзину
                </button>
            </div>
        </div>
    );
};

export default BookCard;
