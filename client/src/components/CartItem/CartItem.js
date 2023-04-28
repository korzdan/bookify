import React from 'react';
import './CartItem.css';
import {deleteItemFromCart, setItemInCart} from "../../redux/Cart/reducer";
import {useDispatch} from "react-redux";

const CartItem = ({book, quantity}) => {

    const dispatch = useDispatch();

    const addToCart = (e) => {
        e.stopPropagation();
        dispatch(setItemInCart(book))
    }

    const removeItemFromCart = (e) => {
        e.stopPropagation();
        dispatch(deleteItemFromCart(book))
    }

    const getPrettyPrice = () => {
        return parseFloat(
            (book.price * quantity)
            .toString()
            .substring(0, 6))
    }

    return (
        <div className="cart-item">
            <span>{book.title} - {quantity} шт.</span>
            <div className="cart-item__price">
                <span>{getPrettyPrice()} BYN</span>
                <div className="cart-item__btns">
                    <div onClick={addToCart} className="cart-item__plus-btn hover:cursor-pointer">+</div>
                    <div onClick={removeItemFromCart} className="cart-item__minus-btn hover:cursor-pointer">-</div>
                </div>
            </div>

        </div>
    );
};

export default CartItem;
