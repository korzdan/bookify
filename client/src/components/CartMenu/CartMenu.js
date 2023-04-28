import React from 'react';
import {calcTotalPrice} from "../../utils/PriceCalculator";
import "./CartMenu.css"
import CartItem from "../CartItem/CartItem";

const CartMenu = ({items, onClick}) => {
    return (
        <div className="cart-menu">
            <div className="cart-menu__books-list">
                {
                    items.length > 0 ? items.map(book => <CartItem book={book}/>) : 'Корзина пуста'
                }
            </div>
            {
                items.length > 0 ?
                    <div className="cart-menu__arrange">
                        <div className="cart-menu__total-price">
                            <span>Итого: </span>
                            <span>{calcTotalPrice(items)} BYN</span>
                        </div>
                        <button className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-3 rounded-full mt-2"
                                onClick={onClick}>
                            Оформить заказ
                        </button>
                    </div>
                    : null
            }
        </div>
    );
};

export default CartMenu;
