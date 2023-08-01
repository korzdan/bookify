import React, {useState} from 'react';
import axios from "axios";
import {useNavigate} from "react-router";
import {useDispatch, useSelector} from "react-redux";
import {clearCart} from "../../redux/Cart/reducer";
import {getToken} from "../../utils/JwtToken";

const OrderDetails = () => {

    const navigate = useNavigate();
    const [address, setAddress] = useState('');
    const [comment, setComment] = useState('');
    const books = useSelector(state => state.cart.itemsInCart)
    const totalPrice = useSelector(state => state.cart.totalPrice)
    const dispatch = useDispatch();

    const handleOrder = () => {
        axios.post("http://localhost:8081/order", {
            address: address,
            bookIds: getBookIdsFromCart(),
            totalPrice: totalPrice,
            comment: comment
        }, {
            headers: {
                'Authorization': 'Bearer ' + getToken()
            }
        }).then(response => {
            navigate("/books")
        });
    }

    const clearItemsFromCart = (e) => {
        e.stopPropagation();
        dispatch(clearCart())
    }

    const getBookIdsFromCart = () => {
        let bookIds = [];
        for (let i = 0; i < books.length; i++) {
            bookIds = bookIds.concat(Array(books[i].count).fill(books[i].id));
        }
        return bookIds;
    }

    return (
        <form className="flex flex-wrap gap-3 w-2/5 p-5 mx-auto mb-16">
            <h1 className="title-font font-medium text-xl text-gray-900 mx-auto">
                Форма оформления заказа
            </h1>
            <p className="title-font font-thin text-lg mb-2 text-gray-400 mx-auto">
                Заказ будет сформирован из тех книг, что находятся в корзине.
            </p>

            <label className="relative w-full flex flex-col">
                <span className="font-bold mb-3">Адрес доставки</span>
                <input className="rounded-md peer pl-12 pr-2 py-2 border-2 border-gray-200 placeholder-gray-300"
                       type="text"
                       name="address"
                       placeholder="пр. Держинского, 95"
                       value={address}
                       onChange={e => setAddress(e.target.value)}/>
                <svg xmlns="http://www.w3.org/2000/svg"
                     className="absolute bottom-0 left-0 -mb-0.5 transform translate-x-1/2 -translate-y-1/2 text-black peer-placeholder-shown:text-gray-300 h-6 w-6"
                     fill="none" viewBox="0 0 24 24" stroke="currentColor">
                    <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2"
                          d="M18.121,9.88l-7.832-7.836c-0.155-0.158-0.428-0.155-0.584,0L1.842,9.913c-0.262,0.263-0.073,0.705,0.292,0.705h2.069v7.042c0,0.227,0.187,0.414,0.414,0.414h3.725c0.228,0,0.414-0.188,0.414-0.414v-3.313h2.483v3.313c0,0.227,0.187,0.414,0.413,0.414h3.726c0.229,0,0.414-0.188,0.414-0.414v-7.042h2.068h0.004C18.331,10.617,18.389,10.146,18.121,9.88 M14.963,17.245h-2.896v-3.313c0-0.229-0.186-0.415-0.414-0.415H8.342c-0.228,0-0.414,0.187-0.414,0.415v3.313H5.032v-6.628h9.931V17.245z M3.133,9.79l6.864-6.868l6.867,6.868H3.133z"/>
                </svg>
            </label>

            <label className="relative w-full flex flex-col">
                <span className="font-bold mb-3">Комментарий к заказу</span>
                <textarea className="rounded-md peer pl-2 pr-2 py-2 border-2 border-gray-200 placeholder-gray-300"
                       name="comment"
                       value={comment}
                       onChange={e => setComment(e.target.value)}/>
            </label>

            <input type="button"
                   className="flex w-full justify-center rounded-md bg-gray-800 px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-gray-900 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600"
                   onClick={(e) => {
                       handleOrder();
                       clearItemsFromCart(e);
                   }}
                   value="Заказать"/>
        </form>
    );
};

export default OrderDetails;
